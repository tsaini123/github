<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<%@ include file="navigationPanel.jsp" %>

<%-- Section to display studies and participate in that study--%>
<section class="participate">
    <h3>
         <form action="StudyController" method="post" id="ReportHistory">
        <span id="studies">Studies</span>
        <%-- link to display Report History.--%>
       
            <input type="hidden" id="actionParam" name="action" value="<%=CommonConstants.REPORT%>">
            <span id="reportHistory"><a id="link" href="#" onclick = "javascript:submitStudyControllerForm('ReportHistory', '<%=CommonConstants.REPORT%>', 'StudyController')">Report History</a></span>
        </form>
    </h3>
    <span style ="margin-left: 30%; color: green " id="errorMessage"><c:out value="${errMsg}" /></span>
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
    <table id="studies_table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>		
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>

        <c:forEach var="study" items="${studylist}">

            <tr>
                <%-- First study details --%>
                <td>${study.studyName}</td>
                <td><img src="images/${study.imageURL}" alt="Image"></td>
                <td>${study.question}</td>
            <form action="StudyController" method="post" id="${study.studyCode}">
                <input type="hidden" id="studyCodeId" name="<%=CommonConstants.STUDYCODE%>" value="${study.studyCode}">
                <input type="hidden" id="actionParam" name="action" value="">
                <input type="hidden" id="questionID" name="question" value="${study.question}">
                <td>            

                    <input type="submit" class="participate_button" value="Participate" onclick="javascript:submitStudyControllerForm('${study.studyCode}', '<%=CommonConstants.PARTICIPATE%>', 'StudyController')"/>
                </td>
                <td>
                    <input type="submit" class="participate_button" value="Report" 
                           onclick="javascript:submitStudyControllerForm('${study.studyCode}', '<%=CommonConstants.REPORT%>', 'StudyController')"/>
                </td>  
            </form>                                              

            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
            <td></td> 
            <td></td> 
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
            <td></td> 
            <td></td> 
        </tr>
    </table>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>