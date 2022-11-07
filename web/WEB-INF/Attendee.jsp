<%-- 
    Document   : Attendee
    Created on : Nov 3, 2022, 3:40:22 PM
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
        <title>Attendee Page</title>
        <script type="text/javascript" src="jquery-3.6.0.min.js"></script>
        <script src="Lab6.js"></script>
    </head>
    <body>
        <div>
            <form name="attendeeform" id="attendeeform">
                <fieldset>
                    <legend>Update Attendee Information</legend>

                    <label for=firstname> First Name </label>
                    <input type="text" id="firstname" name="firstname">

                    <label for=lastname>Last Name</label>
                    <input type="text" id="lastname" name="lastname">

                    <label for=displayname>Display Name</label>
                    <input type="text" id="displayname" name="displayname">

                        <p>Session ID 
                            <%= attendeeDAO.getAttendeeListAsHTML() %>
                        </p>

                    <input type="button" value="UpdateInformation" onclick="Lab6.updateAttendee();">
                </fieldset>
            </form>
        </div>
        <div>
            <a href="index.html">Home</a>
        </div>
        <div id="output" name="output"></div>
    </body>
</html>
