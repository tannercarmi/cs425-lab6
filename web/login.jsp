<%-- 
    Document   : login
    Created on : Nov 7, 2022, 12:02:53 AM
    Author     : tanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    
    <body>
        <form id="loginform" name="loginform" method="POST" action="j_security_check">
            <fieldset>
                <p>
                    <label for="j_username">Username:</label>
                    <input id="j_username" name="j_username" type="text" tabindex=1 />

                    <label for="j_password">Password:</label>
                    <input id="j_password" name="j_password" type="password" tabindex=2 />                    
                </p>

                <p>
                    <input type="submit" value="Log In" tabindex=3 />
                </p>
            </fieldset>
        </form>

        <div id="login_instructions">
            <h2>How to Log In:</h2>
            <p>Enter the Username and Password that you were provided with Example Web Application #1.  See the setup instructions for this application on Canvas.</p>
        </div>

        <%
            String result = request.getParameter("result");
            if (result != null) {
        %>

        <div id="login_error" style="color: red;">
            <p>ERROR: There was a problem processing your login request.  Try reentering your Username and Password.  If it still does not work, check the Tomcat Apache server logs for errors or exceptions.</p>
        </div>

        <%
            }
        %>
    </body>
</html>
