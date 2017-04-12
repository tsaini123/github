<%-- 
    Document   : main
    Created on : Sep 19, 2015, 9:17:56 PM
    Author     : Suman
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Adding a study</h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<%-- Section to create new study --%>
<section id="newstudy_form">
    <form id="newStudyForm" action="StudyController?action=add" method="post" enctype="multipart/form-data" >
        <input type="hidden" name="action" id="action" value="add">
        <label>Study Name *</label>
        <input type="text" name="study_name" required /><br>
        <label>Question Text *</label>
        <input type="text" name="question_text" required/><br>
        <label>Image *</label>
        <input type="file" id="image" name="image"/>
        <br>
        <br>
        <label># Participants *</label>  
        <input type="text" name="participants" required/><br>
        <%-- code to select the answer text boxes. --%>
        <label># Answers *</label>
        <select required name="ansBoxSelection" id="ansBoxSelection" onchange="javascript:selectionChange(this.value)">
            <option value="1" selected>1</option>   
            <option value="2">2</option>
            <option selected="selected" value="3">3</option>
        </select>
         <br/>
        <label>Answer 1 *</label>  
        <input type="text" name="ans1" id="ans1" required/><br>
        <label>Answer 2 *</label>  
        <input type="text" name="ans2" id="ans2" required/><br>
        <label>Answer 3 *</label>  
        <input type="text" name="ans3" id="ans3" required/><br>
       
        <label>Description *</label>  
        <textarea name="description" required></textarea><br>
        
        <button type="submit"  id="submit_button" >Submit</button>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>