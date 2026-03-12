<%-- 
    Document   : SignUp
    Created on : Mar 10, 2026, 12:06:05 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SignUp" method="POST">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="${username}"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="${password}"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="${email}"></td>
                </tr>
                <tr>
                    <td>CCCD/CMT:</td>
                    <td><input type="text" name="citizen_id" value="${citizen_id}"></td>
                </tr>
                <tr>
                    <td>FirstName:</td>
                    <td><input type="text" name="firstname" value="${firstname}"></td>
                </tr>
                <tr>
                    <td>LastName:</td>
                    <td><input type="text" name="lastname" value="${lastname}"></td>
                </tr>
                <tr>
                    <td>PhoneNumber:</td>
                    <td><input type="text" name="phonenumber" value="${phonenumber}"></td>
                </tr>
                <tr>
                    <td>LicenseNumber:</td>
                    <td><input type="text" name="licensenumber" value="${licensenumber}"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" value="${address}"></td>
                </tr>
                <tr>
                    <td>BankNumber:</td>
                    <td><input type="text" name="banknumber" value="${banknumber}"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="ENTER">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
