<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<%@ include file="navigationPanel.jsp" %>
<!--<nav id="menu">
    <ul>
        <li>Coins (<span class="count">2</span>) </li>
        <li>Participants (<span class="count">3</span>) </li>
        <li>Participation (<span class="count">5</span>) </li>
        <li><br></li>
        <li><a href="home.jsp?user=Hello,Kim">Home</a></li>
        <li><a href="participate.jsp?user=Hello,Kim">Participate</a></li>
        <li><a href="studies.jsp?user=Hello,Kim">My Studies</a></li>
        <li><a href="recommend.jsp?user=Hello,Kim">Recommend</a></li>
        <li><a href="contact.jsp?user=Hello,Kim">Contact</a></li>
    </ul>
</nav>-->
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span id="studies">Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/${study.imageURL}" id="question_page_image" alt="${study.imageURL}"/>

    <%--Code to rating the Question --%>
    <form action="StudyController" method="post">
        <input type="hidden" id="studyCodeId" name="<%=CommonConstants.STUDYCODE%>" value="${study.studyCode}">
        <input type="hidden" id="actionParam" name="action" value="<%=CommonConstants.ANSWER%>">
        <input type="hidden" id="questionHf" name="questionHf" value="${study.question}">
        <c:set  var="studyObj" value="study" scope="request"/>
        <div id="question_select">
            <p>${study.question} (${study.description})</p> 
            <c:forEach var="ansChoice" items="${study.choiceOfAns}"  varStatus="loop">
                <div>
                    <input type="radio" id="radButton${loop.count}" name="rateRadio" value="3">
                    <span id="radioLabel" for="radButton${loop.count}">${ansChoice}</span>
                </div>

            </c:forEach>

            <!--        <div>
                        <input type="radio" id="radButton1" name="rateRadio" value="3">
                        <span id="radioLabel" for="radButton1">3</span>
                    </div>
            
                    <div>
                        <input type="radio" id="radButton2" name="rateRadio" value="4"/>
                        <span id="radioLabel" for="radButton2">4</span>
                    </div>
            
                    <div>
                        <input type="radio" id="radButton3" name="rateRadio" value="5" />
                        <span id="radioLabel" for="radButton3">5</span>
                    </div>-->
        </div>
        <%-- Code to submit the Rating  --%>
        <div id="question_submit_div"> 
            
            <button type="submit" id="question_submit">Submit</button>    
    </form>
</div>  
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>