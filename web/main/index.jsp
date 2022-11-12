<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lab #6</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <p>
            <a href="<%= request.getContextPath() %>/main/Registration.jsp">Registrations Page</a>
        </p>
    
        <p>
            <a href="<%= request.getContextPath() %>/main/Attendee.jsp">Attendees Page</a>
        </p>
    
        <p>
            <a href="<%= request.getContextPath() %>/main/Session.jsp">Sessions Page</a>
        </p>
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/main/logout.jsp', '_self', false);" />
        </p>
    </body>
</html>