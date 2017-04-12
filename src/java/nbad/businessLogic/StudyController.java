package nbad.businessLogic;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import nbad.business.Study;
import nbad.business.User;
import nbad.constants.CommonConstants;
import nbad.utility.StudyDB;
import com.oreilly.servlet.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import javax.imageio.ImageIO;
import nbad.utility.UserDB;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author pc
 */
@WebServlet(name = "StudyController", urlPatterns = {"/StudyController"})
public class StudyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("action------" + action);

        String url = "";
        String msg = "";

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(CommonConstants.USER_SESSION_KEY);
        User admin = (User) session.getAttribute(CommonConstants.ADMIN_SESSION_KEY);
        if (action == null) {
            if (user != null) {
                url = "/main.jsp";
            } else if (admin != null) {
                url = "/admin.jsp";
            } else {
                url = "/login.jsp";
            }

        } else if (action.equalsIgnoreCase(CommonConstants.PARTICIPATE)) {
            if (user != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                if (studyCode != null) {
                    Study study = StudyDB.getStudy(studyCode, null);
                    System.out.println("study:" + study);
                    req.setAttribute(CommonConstants.STUDY, study);
                    url = "/question.jsp";
                } else {
                    ArrayList listOfOpenStudies = StudyDB.getStudiesByStatus(CommonConstants.STATUS_START);

                    req.setAttribute(CommonConstants.STUDY_LIST, listOfOpenStudies);
                    url = "/participate.jsp";
                }
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.EDIT)) {
            if (user != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                Study study = StudyDB.getStudy(studyCode, user.getEmail());
                req.setAttribute(CommonConstants.STUDY, study);
                if (study == null) {
                    msg = "Not atuhorized to edit the Study, It's created by other user.";
                }
                url = "/editstudy.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.REPORT)) {
            if (user != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                if (studyCode == null) {
                    ArrayList reportList = StudyDB.getReportedQuestions(user.getEmail(), false);
                    req.setAttribute(CommonConstants.REPORT_LIST, reportList);
                    url = "/reporth.jsp";
                } else {
                    String reporterEmail = user.getEmail();
                    String question = req.getParameter("question");

                    msg = StudyDB.addReport(reporterEmail, Integer.parseInt(studyCode), question);
                    url = "/confirmrep.jsp";
                }
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.REPORTED_QUESTIONS)) {
            if (admin != null) {
                ArrayList repQsList = StudyDB.getReportedQuestions(null, true);
                req.setAttribute(CommonConstants.REPORT_LIST, repQsList);
                url = "/reportques.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.STATUS_APPROVED) || action.equalsIgnoreCase(CommonConstants.STATUS_DISAPPROVED)) {
            if (admin != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                msg = StudyDB.updateQuestionReport(Integer.parseInt(studyCode), action);
                url = "/requestc.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.ADD)) {
            if (user != null) {

                String tempPath = req.getServletContext().getRealPath("/") + "temp";
                String imagesPath = tempPath + "/studyImg/";
                File tmpDir = new File(imagesPath);
                tmpDir.mkdirs();

                MultipartRequest mpr = new MultipartRequest(req, tempPath);
                Enumeration files = mpr.getFileNames();
                mpr.getParameter("image");
                String name = (String) files.nextElement();

                File f = mpr.getFile("image");
                System.out.println(f.getAbsolutePath());
                String imgUrl = f.getAbsolutePath();
                Study study = new Study();
                study.setImageURL(f.getName());
                study.setStudyName(mpr.getParameter("study_name"));
                study.setQuestion(mpr.getParameter("question_text"));
                study.setReqParticipants(Integer.parseInt(mpr.getParameter("participants")));

                int numOfAnswers = Integer.parseInt(mpr.getParameter("ansBoxSelection"));
                ArrayList choiceOfans = new ArrayList();
                choiceOfans.add(mpr.getParameter("ans1"));
                int count = 1;
                if (numOfAnswers > 1) {
                    choiceOfans.add(mpr.getParameter("ans2"));
                    count++;
                } else {
                    choiceOfans.add(null);
                }
                if (numOfAnswers > 2) {
                    count++;
                    choiceOfans.add(mpr.getParameter("ans3"));
                } else {
                    choiceOfans.add(null);
                }

                study.setChoiceOfAns(choiceOfans);
                study.setDescription(mpr.getParameter("description"));
                study.setEmailOfCreator(user.getEmail());

                msg = StudyDB.addStudy(study, count, f);
                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                url = "/studies.jsp";

            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.EDIT)) {
            if (user != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                Study study = StudyDB.getStudy(studyCode, null);
                req.setAttribute(CommonConstants.STUDY, study);
                url = "/editstudy.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.UPDATE)) {
            if (user != null) {
                String tempPath = req.getServletContext().getRealPath("/") + "temp";
                String imagesPath = tempPath + "/studyImg/";
                File tmpDir = new File(imagesPath);
                tmpDir.mkdirs();

                MultipartRequest mpr = new MultipartRequest(req, tempPath);
                Enumeration files = mpr.getFileNames();

                String name = (String) files.nextElement();

                File f = mpr.getFile("image");
                // System.out.println(f.getAbsolutePath());
                //  String imgUrl = f.getAbsolutePath();
                Study study = new Study();
                study.setStudyCode(Integer.parseInt(mpr.getParameter(CommonConstants.STUDYCODE)));
                if (f != null) {
                    study.setImageURL(f.getName());
                }
                study.setStudyName(mpr.getParameter("study_name"));
                study.setQuestion(mpr.getParameter("question_text"));
                study.setReqParticipants(Integer.parseInt(mpr.getParameter("participants")));

                int numOfAnswers = Integer.parseInt(mpr.getParameter("ansBoxSelection"));
                ArrayList choiceOfans = new ArrayList();
                choiceOfans.add(mpr.getParameter("ans1"));
                int count = 1;
                if (numOfAnswers > 1) {
                    choiceOfans.add(mpr.getParameter("ans2"));
                    count++;
                } else {
                    choiceOfans.add(null);
                }
                if (numOfAnswers > 2) {
                    count++;
                    choiceOfans.add(mpr.getParameter("ans3"));
                } else {
                    choiceOfans.add(null);
                }

                study.setChoiceOfAns(choiceOfans);
                study.setDescription(mpr.getParameter("description"));
                study.setEmailOfCreator(user.getEmail());

                msg = StudyDB.updateStudyRecord(study, f, count);

                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                url = "/studies.jsp";

            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.ANSWER)) {
            if (user != null) {

                // Study study = (Study) (req.getParameter("studyObj"));
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                String question = req.getParameter("questionHf");
                String ans = req.getParameter("rateRadio");
                msg = StudyDB.addAnswer(studyCode, question, user, ans);

                ArrayList listOfOpenStudies = StudyDB.getStudiesByStatus(CommonConstants.STATUS_START);
                req.setAttribute(CommonConstants.STUDY_LIST, listOfOpenStudies);
                url = "/participate.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.STUDIES)) {
            if (user != null) {
                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                url = "/studies.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase(CommonConstants.START) || action.equalsIgnoreCase(CommonConstants.STOP)) {
            if (user != null) {
                String studyCode = req.getParameter(CommonConstants.STUDYCODE);
                StudyDB.updateStudyStatus(action, studyCode);
                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                url = "/studies.jsp";
            } else {
                url = "/login.jsp";
            }
        }

        if (user != null) {
            User userObj = UserDB.getUser(user.getEmail(), null, false);
            if (userObj != null) {
                session.setAttribute(CommonConstants.USER_SESSION_KEY, userObj);
            }
        }
        System.out.println("msg:" + msg);
        req.setAttribute(CommonConstants.ERR_MSG, msg);
        System.out.println("url" + url);
        if (url == null) {
            if (user != null) {
                url = "/main.jsp";
            } else if (admin != null) {
                url = "/admin.jsp";
            } else {
                url = "/login.jsp";
            }
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    public static void copyFiles(File file/*sourceLocation*/, String targetLocation)
            throws IOException {

        // if (sourceLocation.isDirectory()) {
//            if (!targetLocation.exists()) {
//                targetLocation.mkdir();
//            }
        //   File[] files = sourceLocation.listFiles();
        // for(File file:files){
        InputStream in = new FileInputStream(file);
        File destFile = new File(targetLocation);
        System.out.println(destFile.getAbsolutePath());
        OutputStream out = new FileOutputStream(destFile);

        // Copy the bits from input stream to output stream
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        // }            
        // }
    }

}
