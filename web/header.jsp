<%-- 
    Document   : header
    Created on : Sep 19, 2015, 4:50:37 PM
    Author     : Suman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="nbad.constants.CommonConstants" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">

        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/cssStyles.css"> 
        <%-- code to import java script file. --%>
        <script type="text/javascript" src="javascript/mainJavaScript.js"></script> 

    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <form id="actionForm" method="post" action="UserController">
                    <ul  class="left" >
                        <li class="liHover" onclick="javascript:submitActionForm('actionForm', 'home')">Researchers Exchange Participations</li>
                    </ul>
                    <ul class="right">

                        <input type="hidden" id="actionParam" name="action" value="">
                        <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'about')">About Us</a></li>
                        <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'how')">How it Works</a></li>
                            <c:choose>
                                <c:when test="${sessionScope.theUser != null}">
                                <li>Hello, ${sessionScope.theUser.name}</li>
                                <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'logout')">Logout</a></li>
                                </c:when>
                                <c:when test="${sessionScope.theAdmin != null}">
                                <li>${sessionScope.theAdmin.name}</li>
                                <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'logout')">Logout</a></li>
                                </c:when>

                            <c:otherwise>
                                <li><a href="#" onclick="javascript:submitActionForm('actionForm', 'goToLogin')">Login</a></li>
                                </c:otherwise>
                            </c:choose>    



                    </ul>
                </form>
            </nav>



        </div>

