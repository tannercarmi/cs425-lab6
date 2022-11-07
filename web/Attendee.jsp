<%-- 
    Document   : Attendee
    Created on : Nov 4, 2022, 2:27:10 AM
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
    
    AttendeeDAO attendeeDAO = daoFactory.getAttendeeDAO();
        
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="jquery-3.6.1.min.js"></script>
        <script src="Lab6.js"></script>
        <title>Attendee Page</title>
    </head>
    
    <body>
        <div>
            <form name="attendeeform" id="attendeeform">
                <fieldset>
                    <legend>Attendee Information Update Form</legend>

                    <label for=firstname> First Name: </label>
                    <input type="text" id="firstname" name="firstname"><!-- comment -->

                    <label for=lastname>Last Name: </label>
                    <input type="text" id="lastname" name="lastname"><!-- comment -->

                    <label for=displayname>Display Name: </label>
                    <input type="text" id="displayname" name="displayname"><!-- comment -->

                        <p>Attendee to Update:  
                            <%= attendeeDAO.getAttendeesAsHTML() %>
                        </p>

                    <input type="button" value="Update Attendee Information" onclick="Lab6.updateAttendeeInfo();">
                </fieldset>
            </form>
        </div>
                        
        <div>
            <a href="index.html">Home Page</a>
        </div>
                        
        <div id="output" name="output"></div>
    </body>
    
</html>
