<%-- 
    Document   : Session
    Created on : Nov 4, 2022, 2:27:52 AM
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
        <title>Session Page</title>
        <script type="text/javascript" src="jquery-3.6.1.min.js"></script>
        <script src="Lab6.js"></script>
    </head>
    
    <body>
        <p>       
        <form>
            <legend>Session Display Form</legend>
                <fieldset>

                    <p>Select a Session: 
                        <%= sessionDAO.getSessionsAsHTML() %>
                    </p>
                    <input type="button" value="Display Session Information" onclick="Lab6.getSessionInfo()">
                    
                </fieldset>
                    
                <p>Session Information Table: </p>
                    
                <div id="output" name="output">
                    <table id="outputtable" name="outputtable"></table>
                </div>            
        </form>
        </p>
            
        <p>
        <form>
            <legend>Update Registration Information</legend>
                <fieldset>
                    <label for=attendeeid> Attendee ID: </label>
                    <input type="text" id="attendeeid" name="attendeeid">
                    <label for=sessionid>Session ID:</label>
                    <input type="text" id="sessionid" name="sessionid">
                        
                    <br>
                    <br>
                        
                    <label for=attendeeid_update>New Attendee ID: </label>
                    <input type="text" id="attendeeid_update" name="attendeeid_update">
                    <label for=sessionid_update>New Session ID: </label>
                    <input type="text" id="sessionid_update" name="sessionid_update">

                    <br>
                        
                    <input type="button" value="Update" onclick="Lab6.updateRegistrationInfo();">
                </fieldset>
        </form>
        </p>
             
        <p>
        <form>
            <legend>Delete Registration Information</legend>
                <fieldset>
                    <label for=attendeeid_delete> Delete Attendee ID: </label>
                    <input type="text" id="attendeeid_delete" name="attendeeid_delete">
                    <label for=sessionid_delete>Delete Session ID:</label>
                    <input type="text" id="sessionid_delete" name="sessionid_delete">
                    <input type="button" value="Delete" onclick="Lab6.deleteRegistrationInfo();">
                </fieldset>
        </form>
        </p>
                    
        <div>
            <a href="index.html">Home Page</a>
        </div>
                    
    </body>
    
</html>
