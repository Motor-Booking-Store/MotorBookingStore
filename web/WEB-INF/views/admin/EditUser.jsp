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
                        <input type="text" name="username" value="${user.userName}" readonly>
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
                        <input type="text" name="citizen_id" value="${user.citizenId}" readonly>
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
                        <input type="text" name="phonenumber" value="${user.phoneNumber}" readonly>
                    </div>

                    <div class="form-group">
                        <label>License</label>
                        <input type="text" name="licensenumber" value="${user.licenseNumber}" readonly>
                    </div>

                    <div class="form-group full-width">
                        <label>Address</label>
                        <select name="locationId">
                            <c:forEach var="l" items="${locationList}">
                                <option value="${l.locationId}">${l.locationName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group full-width">
                        <label>Bank</label>
                        <input type="text" name="banknumber" value="${user.bankNumber}" readonly>
                    </div>

                    <div class="form-group full-width">
                        <label>Role</label>
                        <select name="roleId">
                            <option value="1" ${user.roleId == 1 ? "selected" : ""}>Admin</option>
                            <option value="3" ${user.roleId == 3 ? "selected" : ""}>Customer</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit"class="btn-submit">Update</button>
                        <a href="${pageContext.request.contextPath}/admin/UserManagement" class="btn-cancel">
                            Cancel
                        </a>
                    </div>
                </form>
            </div>

        </div>  
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>