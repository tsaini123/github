package nbad.businessLogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nbad.business.User;
import nbad.utility.UserDB;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // get parameters from the request
        String action = req.getParameter("action");
        System.out.println("action param:" + action);
        String url = null;
        String msg = "";
        HttpSession session = req.getSession();

        if (action == null) {
            url = "/home.jsp";
        } else if (action.equalsIgnoreCase("login")) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String buttonClicked = req.getParameter("userButtonType");
            User user = UserDB.getUser(email, password,true);
            if (user == null) {
                msg = "ERROR: Invalid login Id or password.";
                url = "/login.jsp";
            } else if (!user.getType().equalsIgnoreCase(buttonClicked)) {
                if (user.getType().equalsIgnoreCase("Participant")) {
                    msg = "ERROR: Wrong button clicked. To Login as participant click Log in button.";
                } else {
                    msg = "ERROR: Wrong button clicked. To Login as admin click Admin button.";
                }

                url = "/login.jsp";
            } else {
                if (user.getType().equalsIgnoreCase("Participant")) {
                    url = "/main.jsp";
                    session.setAttribute("theUser", user);
                } else {
                    url = "/admin.jsp";
                    session.setAttribute("theAdmin", user);
                }

            }

        }else if (action.equalsIgnoreCase("goToLogin")) {
            url = "/login.jsp";
        }
        else if (action.equalsIgnoreCase("goToSignUp")) {
            url = "/signup.jsp";
        }
        else if (action.equalsIgnoreCase("create")) {
            String email = req.getParameter("email");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String confirm_password = req.getParameter("confirm_password");
            if (!password.equals(confirm_password)) {
                msg = "ERROR: password doesn't match confirm password.";
                url = "/signup.jsp";
            } else {
                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setType("Participant");
                int isNewUser = UserDB.addUser(user, password);
                System.out.println("isnew:" + isNewUser);
                if (isNewUser == 0) {
                    msg = "ERROR: Email id already exists.";
                    url = "/signup.jsp";
                } else {
                    url = "/main.jsp";
                    session.setAttribute("theUser", user);
                }

            }
        } else if (action.equalsIgnoreCase("how")) {

            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else if (session.getAttribute("theAdmin") != null) {
                url = "/main.jsp";
            } else {
                url = "/how.jsp";
            }
        } else if (action.equalsIgnoreCase("about")) {

            if (session.getAttribute("theUser") != null) {
                url = "/aboutl.jsp";
            }    else if (session.getAttribute("theAdmin") != null) {
                url = "/aboutl.jsp";
            } 
            else {
                url = "/about.jsp";
            }
        } else if (action.equalsIgnoreCase("home")) {

            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else if (session.getAttribute("theAdmin") != null) {
                url = "/admin.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if (action.equalsIgnoreCase("main")) {

            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equalsIgnoreCase("logout")) {

            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                session.removeAttribute("theUser");
                session.removeAttribute("theAdmin");
                url = "/home.jsp";

            }
        }

        req.setAttribute("msg", msg);
        if(url == null){
            url = "/home.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
