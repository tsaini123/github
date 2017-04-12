



<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<%-- Code to display items in List --%>
<%--<c:if test="${sessionScope.theAdmin != null}">
    <nav id="menu">
        <ul>
            <form id="navForm" method="post">
                <input type="hidden" id="actionParam" name="action" value="">
                <li><a href="#" onclick = "javascript:submitStudyControllerForm('navForm', '<%=CommonConstants.HOME%>', 'UserController')">Home</a></li>
                <li><a href="#" onclick = "javascript:submitStudyControllerForm('navForm', '<%=CommonConstants.REPORTED_QUESTIONS%>', 'StudyController')">Reported Questions</a></li>
            </form>
        </ul>
    </nav>
</c:if>--%>

<%-- Section tag is used to write description  --%>
<section id="how">
    <h3>About us</h3>
    <p>Researchers Exchange Participations is a platform for researchers 
        to exchange participations</p>
    <p>The aim of this platform is to encourage researchers participate in each others
        user studies. Moreover, recruiting serious participants has been always a burden on
        a researcher's shoulder, thus, this platform gives researchers the opportunity
        to do that effectively and in a suitable time.</p>
        <%-- Include tag is used to import footer page --%>
</section>
<%@include file="footer.jsp" %>