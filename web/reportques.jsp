<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<%-- Section to display Reported questions.--%>
<section class="reportquesHdng">
    <h3>
        <span >Reported Questions</span>
    </h3>
</section>

<%-- Code to go back to Main page  --%>
<br/><br/><br/>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<br/>
<%-- Section tag is used to display Recommendation Sent   --%>

<section class="reportques">
    <table id="repQuesTable">
        <%--Column Names --%>

        <c:set var="emptyList" value="${true}"/>
        <c:forEach var="study" items="${reportList}" varStatus="loop" >
            <c:set var="emptyList" value="${false}"/>
            <c:if test="${loop.count == 1}">
                <tr>
                    <th>Question</th>
                    <th>Action</th>		
                </tr>
            </c:if>
            <tr>
            <form action="StudyController" method="post" id="${study.studyCode}">
                <input type="hidden" id="studyCodeId" name="<%=CommonConstants.STUDYCODE%>" value="${study.studyCode}">
                <input type="hidden" id="actionParam" name="action" value="">
                <%-- First study details --%>

                <td>${study.reportQuestion}</td>
                <td>
                    <span class="spanLeft"> 
                        <input type="submit" class="participate_button"
                               value="Approve" onclick="javascript:submitStudyControllerForm('${study.studyCode}', '<%=CommonConstants.STATUS_APPROVED%>', 'StudyController')"/>
                    </span>                
                    <span class="spanRight"> 
                        <input type="submit" class="participate_button"
                               value="Disapprove" onclick="javascript:submitStudyControllerForm('${study.studyCode}', '<%=CommonConstants.STATUS_DISAPPROVED%>', 'StudyController')"/>
                    </span>      
                </td>
            </form>
            </tr>

            <c:if test="${loop.last}">
                <tr>
                    <td></td>
                    <td></td>

                </tr>

            </c:if>
        </c:forEach>
                <c:if test="${emptyList}">
                    <h3>No reported question.</h3>
                </c:if>


    </table>
</section>

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>