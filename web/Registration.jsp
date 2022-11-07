<%-- 
    Document   : Registration
    Created on : Nov 4, 2022, 2:26:54 AM
    Author     : tanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.jsu.mcis.lab6.dao.*"%>

<%
    DAOFactory daoFactory = null;
    ServletContext context = request.getServletContext();
    if (context.getAttribute("daoFactory") == null) {
        System.err.println("*** Creating new DAOFactory ...");
        daoFactory = new DAOFactory();
        context.setAttribute("daoFactory", daoFactory);
    }
    else {
        daoFactory = (DAOFactory) context.getAttribute("daoFactory");
    }
    
    SessionDAO sessionDAO = daoFactory.getSessionDAO();
        
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    
    <body>
        <div>
            <form action="registrations" method="POST">
                <fieldset>
                    <legend>Session Registration Form</legend>

                    <label for=firstname> First Name: </label>
                    <input type="text" id="firstname" name="firstname">

                    <label for=lastname>Last Name: </label>
                    <input type="text" id="lastname" name="lastname">

                    <label for=displayname>Display Name: </label>
                    <input type="text" id="displayname" name="displayname">

                        <p>Session: 
                            <%= sessionDAO.getSessionsAsHTML() %>
                        </p>

                    <input type="submit" value="Register for Session">
                </fieldset>
            </form>
        </div>
        <div>
            <a href="index.html">Home Page</a>
        </div>
    </body>
    
</html>
