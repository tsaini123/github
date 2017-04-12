<%@page import="nbad.constants.CommonConstants" %>
<%-- Condition to display navigation menu if user is Participant. --%>
<c:if test="${sessionScope.theUser != null}">
    <form id="actionForm" method="post" action="UserController">
        <input type="hidden" id="actionParam" name="action" value="">
        <nav id="menu">
            <ul>
                <li>Coins (<span class="count">${theUser.numCoins}</span>) </li>
                <li>Participants (<span class="count">${theUser.numOfTotalParticipants}</span>) </li>
                <li>Participation (<span class="count">${theUser.numParticipation}</span>) </li>
                <li><br></li>
                <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'main')">Home</a></li>
                <li><a href="#" onclick="javascript:submitStudyControllerForm('actionForm', '<%=CommonConstants.PARTICIPATE%>', 'StudyController')">Participate</a></li>
                <li><a href="#" onclick="javascript:submitStudyControllerForm('actionForm', '<%=CommonConstants.STUDIES%>', 'StudyController')">My Studies</a></li>
                <li><a href="recommend.jsp">Recommend</a></li>
                <li><a href="contact.jsp">Contact</a></li>
            </ul>

        </nav>
    </form>
</c:if>
                
<%-- Condition to display navigation menu if user is admin. --%>
<c:if test="${sessionScope.theAdmin != null}">
    <nav id="menu">
        <ul>
            <form id="navForm" method="post">
                <input type="hidden" id="actionParam" name="action" value="">
                <li><a href="#" onclick = "javascript:submitStudyControllerForm('navForm', '<%=CommonConstants.HOME%>', 'UserController')">Home</a></li>
                <li><a href="#" onclick = "javascript:submitStudyControllerForm('navForm', '<%=CommonConstants.REPORTED_QUESTIONS%>', 'StudyController')">Reported Questions</a></li>
            </form>
        </ul>
    </nav>
</c:if>                  