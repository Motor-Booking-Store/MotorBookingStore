<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add User</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/addUser.css">
    </head>

    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>

        <div class="container">
            <div class="form-card">
                <h2>Add New User</h2>

                <!-- HIỂN THỊ LỖI -->
                <c:if test="${not empty error}">
                    <p class="error">${error}</p>
                </c:if>

                <form action="adduser" method="POST" class="form">

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="username" value="${username}">
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password">
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${email}">
                    </div>

                    <div class="form-group">
                        <label>Citizen ID</label>
                        <input type="text" name="citizen_id" value="${citizen_id}">
                    </div>

                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstname" value="${firstname}">
                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastname" value="${lastname}">
                    </div>

                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" name="phonenumber" value="${phonenumber}">
                    </div>

                    <div class="form-group">
                        <label>License Number</label>
                        <input type="text" name="licensenumber" value="${licensenumber}">
                    </div>

                    <div class="form-group full-width">
                        <label>Address</label>
                        <input type="text" name="address" value="${address}">
                    </div>

                    <div class="form-group full-width">
                        <label>Bank Number</label>
                        <input type="text" name="banknumber" value="${banknumber}">
                    </div>

                    <div class="form-group full-width">
                        <label>Role</label>
                        <select name="roleId">
                            <option value="1">Admin</option>
                            <option value="3">Customer</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Add User</button>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>