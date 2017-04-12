<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<%-- Code to go back to Main page  --%>
<br>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<!--<span style ="margin-left: 30%; color: green " id="errorMessage"><c:out value="${errMsg}" /></span>-->
<%-- Section tag is used to write description  --%>
<section id="QuestionReport_section">
    <h3>${errMsg}</h3>
    <!--<h3>Questions reported..</h3>-->
</section>

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>