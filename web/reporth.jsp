<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<%-- Code to go back to Main page  --%>
<br>
<a href="#" id="back_to_page" onclick="javascript:submitActionForm('actionForm', 'home')">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Recommendation Sent   --%>
<br/><br/><br/><br/>

<section class="table-responsive" >
    <table id="reportTable" >


        <thead>
            <tr>
                <th class="reporth_table">Report Date</th>
                <th class="reporth_table">Report Question</th>
                <th class="reporth_table">Report Status</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="report" items="${reportList}">
                <tr>


                    <td class="reporth_table">${report.reportDate}</td>
                    <td class="reporth_table">${report.reportQuestion}</td>
                    <td class="reporth_table">${report.reportStatus}</td>


                </tr>

            </c:forEach>
<!--            <tr>


                <td class="reporth_table">01/15/2016</td>
                <td class="reporth_table">How much do you...</td>
                <td class="reporth_table">Approved</td>


            </tr>

            <tr >


                <td class="reporth_table">01/18/2016</td>
                <td class="reporth_table">What do you do w...</td>
                <td class="reporth_table">Pending</td>


            </tr>-->
        </tbody>


    </table>
</section>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>