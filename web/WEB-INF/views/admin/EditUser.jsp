<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit User</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/editUser.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <div class="container">

            <!-- MAIN CONTENT -->
            <div class="main-content">
                <h2>Edit User</h2>

                <form action="${pageContext.request.contextPath}/admin/EditUser" method="post" class="form">

                    <input type="hidden" name="userID" value="${user.userID}" />

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="username" value="${user.userName}">
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" name="password" value="${user.password}">
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${user.email}">
                    </div>

                    <div class="form-group">
                        <label>Citizen ID</label>
                        <input type="text" name="citizen_id" value="${user.citizenId}">
                    </div>

                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstname" value="${user.firstName}">
                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastname" value="${user.lastName}">
                    </div>

                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="phonenumber" value="${user.phoneNumber}">
                    </div>

                    <div class="form-group">
                        <label>License</label>
                        <input type="text" name="licensenumber" value="${user.licenseNumber}">
                    </div>

                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" value="${user.address}">
                    </div>

                    <div class="form-group">
                        <label>Bank</label>
                        <input type="text" name="banknumber" value="${user.bankNumber}">
                    </div>

                    <div class="form-group">
                        <label>Role</label>
                        <select name="roleId">
                            <option value="1" ${user.roleId == 1 ? "selected" : ""}>Admin</option>
                            <option value="3" ${user.roleId == 3 ? "selected" : ""}>Customer</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit">Update</button>
                        <a href="${pageContext.request.contextPath}/admin/UserManagement">
                            <button type="button">Cancel</button>
                        </a>
                    </div>

                </form>
            </div>

        </div>  
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>