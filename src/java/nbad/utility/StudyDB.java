package nbad.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import nbad.business.QuestionReport;
import nbad.business.Study;
import nbad.business.User;
import nbad.constants.CommonConstants;

public class StudyDB {

    public static Study getStudy(String studyCode, String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE Studycode = ? ";
        if (email != null) {
            query = query + " and creatorEmail = ? ";
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            if (email != null) {
                ps.setString(2, email);
            }
            rs = ps.executeQuery();
            Study study = null;
            if (rs.next()) {
                study = createStudyObject(rs);
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Study> getStudiesByStatus(String status) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE Status = ? ";
        System.out.println("status:" + status);
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, CommonConstants.START);
            rs = ps.executeQuery();
            ArrayList<Study> studyList = new ArrayList<Study>();
            while (rs.next()) {
                studyList.add(createStudyObject(rs));
            }
            return studyList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Study> getStudies() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Study> studyList = null;
            while (rs.next()) {
                studyList.add(createStudyObject(rs));
            }
            return studyList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Study> getStudies(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Study "
                + "WHERE creatorEmail = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            ArrayList<Study> studyList = new ArrayList<Study>();
            while (rs.next()) {
                studyList.add(createStudyObject(rs));
            }
            return studyList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<QuestionReport> getReportedQuestions(String email, boolean selAll) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT R.Email, R.StudyCode, S.Question, R.Status, R.ReportDate  FROM ReportedQuestion R, Study S "
                + " WHERE R.StudyCode=S.StudyCode and R.Email = ? ";
        if (selAll && email == null) {
            query = "SELECT R.Email, R.StudyCode, S.Question, R.Status, R.ReportDate  FROM ReportedQuestion R, Study S  Where R.StudyCode=S.StudyCode and R.Status = ?";
        }
        try {

            ps = connection.prepareStatement(query);
            if (!selAll && email != null) {
                ps.setString(1, email);

            } else {
                ps.setString(1, CommonConstants.STATUS_PENDING);

            }

            rs = ps.executeQuery();
            ArrayList<QuestionReport> qsReportList = new ArrayList<QuestionReport>();
            while (rs.next()) {
                QuestionReport qr = new QuestionReport();
                qr.setEmail(rs.getString(1));
                qr.setStudyCode(rs.getInt(2));
                qr.setReportQuestion(rs.getString(3));
                qr.setReportStatus(rs.getString(4));
                qr.setReportDate(rs.getDate(5).toString());
                qsReportList.add(qr);
            }

            return qsReportList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String addReport(String reporterEmail, int studyCode, String question) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int isReportAdded = 0;

        ResultSet rs = null;

        String query = "SELECT * FROM ReportedQuestion "
                + "WHERE Email = ?  and StudyCode=?";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, reporterEmail);
            ps.setInt(2, studyCode);
            if (ps.executeQuery().next()) {
                return "This Question is already reported.";
            } else {
//                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
//                String date = formatter.format(Calendar.getInstance().getTime()).toString();
//                System.out.println("date:" + date);
//                Date myDate = formatter.parse(date);
//                System.out.println("myDate:" + myDate);
                java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                System.out.println("sqlDate:" + sqlDate);
                query = "Insert into ReportedQuestion(Email,StudyCode,Status,ReportDate, Question) "
                        + " values(?, ?, ?, ?, ?) ";
                ps = connection.prepareStatement(query);
                ps.setString(1, reporterEmail);
                ps.setInt(2, studyCode);
                ps.setString(3, CommonConstants.STATUS_PENDING);
                ps.setDate(4, sqlDate);
                ps.setString(5, question);
                isReportAdded = ps.executeUpdate();

                if (isReportAdded >= 1) {
                    return "Question Reported.";
                } else {
                    return "SQL-Error when reporting the question.";
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
            return "Error when reporting the question.";
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String updateQuestionReport(int studyCode, String status) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int isReportAdded = 0;

        String query = "";

        try {
            query = "Update ReportedQuestion SET Status = ? "
                    + " WHERE StudyCode = ? ";
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, studyCode);
            isReportAdded = ps.executeUpdate();

            if (isReportAdded > 0) {
                return "Question " + status + ".";
            } else {
                return "SQL-Error when updating the status.";
            }

        } catch (SQLException e) {
            System.out.println(e);
            return "SQL-Error when updating the status.";
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String addStudy(Study study, int numOfAns, File file) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        ArrayList ansList = study.getChoiceOfAns();
        try {
            FileInputStream fis = new FileInputStream(file);
//'image',
            String query = "insert into Study(StudyName,Question,creatorEmail,ReqstdParticipants,NumOfAns,"
                    + " Ans1,Ans2,Ans3,Description,AnswerType,imgUrl, image, Status) "
                    + " values(?, ?, ?, ?,?, ?, ?, ?,?, ?, ?,?,?) ";
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getStudyName());
            ps.setString(2, study.getQuestion());
            ps.setString(3, study.getEmailOfCreator());
            // ps.setBlob(3, null);
            ps.setInt(4, study.getReqParticipants());
            ps.setInt(5, numOfAns);
            ps.setString(6, (String) ansList.get(0));
            ps.setString(7, (String) ansList.get(1));
            ps.setString(8, (String) ansList.get(2));
            ps.setString(9, study.getDescription());
            ps.setString(10, "Text");
            ps.setString(11, study.getImageURL());
            ps.setBinaryStream(12, fis, (int) file.length());
            ps.setString(13, CommonConstants.STOP);
            //ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "New Study addedd successfully.";
            } else {
                return "Unable to add new study.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "Duplicate entry: Study name already exists.";
            } else {
                return e.getMessage();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return "Error when adding new study.";
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String addAnswer(String studyCode, String question, User user, String ans) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {

            String queryToAddAns = "insert into Participation(StudyCode, EmailOfParticipant,question,Response) "
                    + " values(?,?,?,?)";
            ps = connection.prepareStatement(queryToAddAns);
            ps.setInt(1, Integer.parseInt(studyCode));
            ps.setString(2, user.getEmail());
            ps.setString(3, question);
            ps.setString(4, ans);
            int count = ps.executeUpdate();
            System.out.println("count--" + count);
            if (!(count > 0)) {
                return "You have already participated in this study.";
            } else {
                String queryToSelectTotalNumParticp = "select TotalNumOfParticipants from User where Email= (select creatorEmail from Study where Studycode=?)";
                ps = connection.prepareStatement(queryToSelectTotalNumParticp);
                ps.setInt(1, Integer.parseInt(studyCode));
                ResultSet rs1 = ps.executeQuery();
                int totalParticipants = 0;
                if (rs1.next()) {
                    totalParticipants = rs1.getInt(1);

                }
                rs1.close();

                String queryToUpdateNumOfParticipant = "update User set  TotalNumOfParticipants=? where Email=(select creatorEmail from Study where Studycode=?)";
                ps = connection.prepareStatement(queryToUpdateNumOfParticipant);
                ps.setInt(1, (totalParticipants + 1));
                ps.setInt(2, Integer.parseInt(studyCode));
                ps.executeUpdate();

                String queryParticipationCount = "select ParticipationCount from User where Email= ?";
                ps = connection.prepareStatement(queryParticipationCount);
                ps.setString(1,user.getEmail());
                ResultSet rs2 = ps.executeQuery();
                int participationCount = 0;
                if (rs2.next()) {
                    participationCount = rs2.getInt(1);

                }
                rs2.close();

                String queryToUpdateParticipationCount = "update User set ParticipationCount=? where Email=?";
                ps = connection.prepareStatement(queryToUpdateParticipationCount);
                ps.setInt(1,(participationCount+1));
                ps.setString(2, user.getEmail());
                ps.executeUpdate();

                String selNumOfPartStudy = "select NumOfParticipants from Study where Studycode=?";
                ps = connection.prepareStatement(selNumOfPartStudy);
                ps.setInt(1, Integer.parseInt(studyCode));
                ResultSet rs = ps.executeQuery();
                int studyParticipants = 0;
                if (rs.next()) {
                    studyParticipants = rs.getInt(1);
                }
                rs.close();

                String updateStudyTable = "update Study set NumOfParticipants=? where Studycode=?";
                ps = connection.prepareStatement(updateStudyTable);
                ps.setInt(1, studyParticipants + 1);
                ps.setInt(2, Integer.parseInt(studyCode));
                ps.executeUpdate();

                
                return "";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "You have already participated in this study.";
            } else {
                return e.getMessage();
            }

        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int updateStudyStatus(String status, String studyCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "update Study set Status=? where Studycode=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(studyCode));
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return 0;
    }

    public static String updateStudyRecord(Study study, File file, int numOfAns) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        ArrayList ansList = study.getChoiceOfAns();
        try {

//'image',
            String query = "update Study SET StudyName=?,Question=?,ReqstdParticipants=?,NumOfAns=?,"
                    + "Ans1=?,Ans2=?,Ans3=?,Description=?";
            if (file != null) {
                query += ",imgUrl=?, image=? ";
            }

            query += " WHERE Studycode=?";
            int colIndx = 1;
            ps = connection.prepareStatement(query);
            ps.setString(colIndx++, study.getStudyName());
            ps.setString(colIndx++, study.getQuestion());
            ps.setInt(colIndx++, study.getReqParticipants());
            ps.setInt(colIndx++, numOfAns);
            ps.setString(colIndx++, (String) ansList.get(0));
            ps.setString(colIndx++, (String) ansList.get(1));
            ps.setString(colIndx++, (String) ansList.get(2));
            ps.setString(colIndx++, study.getDescription());

            if (file != null) {
                ps.setString(colIndx++, study.getImageURL());
                FileInputStream fis = new FileInputStream(file);
                ps.setBinaryStream(colIndx++, fis, (int) file.length());
            }
            ps.setInt(colIndx++, study.getStudyCode());

            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "Study updated successfully.";
            } else {
                return "Unable to add new study.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "Duplicate entry: Study name already exists.";
            } else {
                return e.getMessage();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return "Error when adding new study.";
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public void updateStudy(String sCode, Study study) {

    }

    private static Study createStudyObject(ResultSet rs) throws SQLException {
        Study study = new Study();
        study.setStudyCode(rs.getInt("Studycode"));
        study.setStudyName(rs.getString("StudyName"));
        study.setQuestion(rs.getString("Question"));
        study.setEmailOfCreator(rs.getString("creatorEmail"));
        study.setImageURL(rs.getString("imgUrl"));
        study.setReqParticipants(rs.getInt("ReqstdParticipants"));
        study.setNumOfParticipants(rs.getInt("NumOfParticipants"));
        study.setChoiceOfAns(getAnsChoiceList(rs, rs.getInt("NumOfAns")));
        study.setDescription(rs.getString("Description"));
        study.setStatus(rs.getString("Status"));
        study.setAnsType(rs.getString("AnswerType"));
        // InputStream is = rs.getBinaryStream("image");
        // FileOutputStream fos = new FileOutputStream(is);

        return study;
    }

    private static ArrayList getAnsChoiceList(ResultSet rs, int numOfChoice) throws SQLException {
        ArrayList list = new ArrayList();
        for (int i = 1; i <= numOfChoice; i++) {
            list.add(rs.getString("Ans" + i));
        }
        return list;
    }
}
