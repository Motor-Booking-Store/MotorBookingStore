<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Detail</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/edit-user.css">
    </head>
    <body>
        <div>
            <jsp:include page="./component/navbar.jsp"/>
        </div>
        <div class="container">
            <h2>Edit Your Profile</h2>
            <div class="back-btn">
                <a href="${pageContext.request.contextPath}/user/UserDetail">
                    <button type="button">Quay về</button>
                </a>
            </div>
            <form action="${pageContext.request.contextPath}/user/EditUserDetail" 
                  method="post" 
                  enctype="multipart/form-data">

                <div class="form-grid">

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="userName" value="${user.userName}" required>
                    </div>

                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" name="phoneNumber" value="${user.phoneNumber}">
                    </div>

                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstName" value="${user.firstName}">
                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastName" value="${user.lastName}">
                    </div>

                    <div class="form-group">
                        <label>License Number</label>
                        <input type="text" name="licenseNumber" value="${user.licenseNumber}">
                    </div>

                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" value="${user.address}">
                    </div>

                    <!-- Avatar full width -->
                    <input type="hidden" name="oldAvatar" value="${user.avatar}">

                    <div class="form-group full">
                        <c:if test="${not empty user.avatar}">
                            <div class="avatar-preview">
                                <p>Current Avatar:</p>
                                <img src="${pageContext.request.contextPath}${user.avatar}" width="120">
                            </div>
                        </c:if>
                    </div>

                    <div class="form-group full">
                        <label>Upload Avatar</label>
                        <input type="file" name="avatar" accept="image/*">
                    </div>

                    <div class="submit-btn">
                        <button type="submit">Save Changes</button>
                    </div>

                </div>
            </form>
        </div>
        <div>
            <jsp:include page="./component/footer.jsp"/>
        </div>
    </body>
</html>