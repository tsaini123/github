<%-- 
    Document   : main
    Created on : Sep 19, 2015, 9:17:56 PM
    Author     : Suman
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section id="newstudy_form">
    <form action="StudyController?action=update" method="post" enctype="multipart/form-data">
        <input type="hidden" id="studyCodeId" name="<%=CommonConstants.STUDYCODE%>" value="${study.studyCode}">
        <input type="hidden" id="actionParam" name="action" value="<%=CommonConstants.UPDATE%>">
        <label>Study Name *</label>
        <input type="text" name="study_name" value="${study.studyName}" required /><br>
        <label>Question Text *</label>
        <input type="text" name="question_text" value="${study.question}" required/><br>
        <%-- Img tag is used to import image --%>
        <label>Image *</label>
        
        <img src="images/${study.imageURL}" id="edit_study_image" alt="Edit"/>
        <button type="button" onclick="document.getElementById('image').click(); return false;">Browse</button>
        <input type="file" id="image" name="image" style="visibility: hidden;"/>
        <br>
        <br>
        <label># Participants *</label>  
        <input type="text" name="participants" value="${study.reqParticipants}" required/><br>

        <%-- code to select the answer text boxes. --%>
        <label># Answers *</label>
        <select required name="ansBoxSelection" id="ansBoxSelection" onchange="javascript:selectionChange(this.value)">
            <c:forEach var="ansList" varStatus="loop" items="${study.choiceOfAns}">                
                <option value="${loop.count}" <c:if test="${loop.last}">selected</c:if>>${loop.count}</option>
                <c:if test="${loop.count == 1 && loop.last }">
                    <option value="2">2</option>
                    <option  value="3">3</option>
                </c:if>

                <c:if test="${loop.count == 2 && loop.last }">
                    <option  value="3">3</option>
                </c:if>   


            </c:forEach>

            <!--            <option value="1" selected>1</option>   
                        <option value="2">2</option>
                        <option selected="selected" value="3">3</option>-->
        </select>
        <br/>
        <c:forEach var="ansList" varStatus="loop" items="${study.choiceOfAns}"> 
            <label>Answer ${loop.count} *</label>  
            <input type="text" name="ans${loop.count}" id="ans${loop.count}" value="${ansList}" required/><br>

            <c:if test="${loop.count == 1 && loop.last }">
                <label>Answer 2 *</label>  
                <input type="text" name="ans2" id="ans2" required disabled/><br>
                <label>Answer 3 *</label>  
                <input type="text" name="ans3" id="ans3" required disabled/><br>
            </c:if>

            <c:if test="${loop.count == 2 && loop.last }">
                <label>Answer 3 *</label>  
                <input type="text" name="ans3" id="ans3" required disabled/><br>
            </c:if>  

        </c:forEach>
        <!--        <label>Answer 1 *</label>  
                <input type="text" name="ans1" id="ans1" required/><br>
                <label>Answer 2 *</label>  
                <input type="text" name="ans2" id="ans2" required/><br>
                <label>Answer 3 *</label>  
                <input type="text" name="ans3" id="ans3" required/><br>-->

        <label>Description *</label>  
        <textarea name="description" required>${study.description}</textarea><br>
        <button type="submit"  id="submit_button">Update</button>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>