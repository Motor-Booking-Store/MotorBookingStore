<%-- 
    Document   : Login
    Created on : Mar 10, 2026, 5:46:16 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="static/login.css">
    </head>
    <body>
        <form action="Login" method="POST">
            <table>
                <h2>Login</h2>
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type="text" name="email" value="${email}" required>
                    </td>
                </tr>

                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="password" name="password" required>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="Login">
                    </td>
                </tr>

                <tr>
                    <td colspan="2" style="color:red;">
                        ${error}
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>
