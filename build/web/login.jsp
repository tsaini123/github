<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<%-- Section to input login details --%>
<section id="login_form">
    <%-- Code to create login form--%>
    
    <form id="myloginForm" method="post" action="UserController">
        <span id="errorMessage"><c:out value="${msg}" /></span>
        <br/>
        <input type="hidden" name="action" value="login" />
        <input type="hidden" name="userButtonType" id="userButtonType" value="" />
        <label >Email Address *</label>
        <input type="email" name="email" required /> <br><br>
        <label >Password *</label>
        <input type="password" name="password" required /><br>
        <label>&nbsp;</label>
         <%-- Code to call javascript method to go to Main page  --%>
        <input type="submit" value="Log in" id="login_button" onclick="javascript:submitLoginForm('myloginForm','Participant');">
        
        <%-- Code to call javascript method to go to Admin page  --%>
        <input type="submit" value="Admin" id="login_button" onclick="javascript:submitLoginForm('myloginForm','Admin');">
    </form>

    <%-- Code to go to Sign up for a new account  --%>
    <a href="#" id="sign_up_link" onclick="javascript:submitActionForm('actionForm', 'goToSignUp')">Sign up for a new account</a>

</section>
    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>