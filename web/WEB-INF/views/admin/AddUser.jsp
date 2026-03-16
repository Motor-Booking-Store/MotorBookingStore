<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add User</title>
    </head>

    <body>

        <h2>Add New User</h2>

        <!-- HIỂN THỊ LỖI -->
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>

        <form action="adduser" method="POST">

            <table border="1" cellpadding="5">

                <tr>
                    <td>Username</td>
                    <td>
                        <input type="text" name="username" value="${username}">
                    </td>
                </tr>

                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td>
                        <input type="text" name="email" value="${email}">
                    </td>
                </tr>

                <tr>
                    <td>Citizen ID</td>
                    <td>
                        <input type="text" name="citizen_id" value="${citizen_id}">
                    </td>
                </tr>

                <tr>
                    <td>First Name</td>
                    <td>
                        <input type="text" name="firstname" value="${firstname}">
                    </td>
                </tr>

                <tr>
                    <td>Last Name</td>
                    <td>
                        <input type="text" name="lastname" value="${lastname}">
                    </td>
                </tr>

                <tr>
                    <td>Phone Number</td>
                    <td>
                        <input type="text" name="phonenumber" value="${phonenumber}">
                    </td>
                </tr>

                <tr>
                    <td>License Number</td>
                    <td>
                        <input type="text" name="licensenumber" value="${licensenumber}">
                    </td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td>
                        <input type="text" name="address" value="${address}">
                    </td>
                </tr>

                <tr>
                    <td>Bank Number</td>
                    <td>
                        <input type="text" name="banknumber" value="${banknumber}">
                    </td>
                </tr>

                <tr>
                    <td>Role</td>
                    <td>
                        <select name="roleId">
                            <option value="1">Admin</option>
                            <option value="3">Customer</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Add User">
                    </td>
                </tr>

            </table>

        </form>

    </body>
</html>