<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>


<%-- Code to go back to Main page  --%>
<br/><br/><br/>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<br/>
<%-- Section tag is used to display whether question disapproved or approved.   --%>

<section id="QuestionReport_section">
    <h3>${errMsg}</h3>

</section>

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>