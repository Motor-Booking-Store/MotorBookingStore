<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Management</title>
    </head>

    <body>

        <h2>User Management</h2>

        <!-- MESSAGE (nếu có) -->
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <!-- ADD USER -->
        <a href="${pageContext.request.contextPath}/admin/adduser">
            <button>Add New User</button>
        </a>

        <br><br>

        <!-- TABLE -->
        <table border="1" cellpadding="10" cellspacing="0" width="100%">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Citizen ID</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>

                <!-- Nếu list rỗng -->
                <c:if test="${empty accountList}">
                    <tr>
                        <td colspan="7" style="text-align:center;">
                            No users found
                        </td>
                    </tr>
                </c:if>

                <!-- Loop user -->
                <c:forEach var="u" items="${accountList}">
                    <tr>

                        <td>${u.userID}</td>

                        <td>${u.userName}</td>

                        <td>${u.email}</td>

                        <td>${u.citizenId}</td>

                        <td>${u.phoneNumber}</td>

                        <!-- ROLE -->
                        <td>
                            <c:choose>
                                <c:when test="${u.roleId == 1}">
                                    Admin
                                </c:when>
                                <c:otherwise>
                                    Customer
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!-- ACTION -->
                        <td>

                            <!-- EDIT -->
                            <a href="${pageContext.request.contextPath}/admin/EditUser?id=${u.userID}">
                                <button>Edit</button>
                            </a>

                            <!-- DELETE -->
                            <c:if test="${u.roleId != 1}">
                                <a href="${pageContext.request.contextPath}/admin/DeleteUser?id=${u.userID}"
                                   onclick="return confirm('Are you sure to delete this user?');">
                                    <button>Delete</button>
                                </a>
                            </c:if>

                            <!-- nếu là admin thì disable delete -->
                            <c:if test="${u.roleId == 1}">
                                <button disabled>Delete</button>
                            </c:if>

                        </td>

                    </tr>
                </c:forEach>

            </tbody>

        </table>

    </body>
</html>