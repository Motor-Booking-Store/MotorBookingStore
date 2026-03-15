<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Detail</title>
    </head>
    <body>
        <div>
            <h2>Edit Your Profile</h2>
            <a href="${pageContext.request.contextPath}/user/UserDetail">
                <button type="button">Quay về</button>
            </a>

            <form action="${pageContext.request.contextPath}/user/EditUserDetail" 
                  method="post" 
                  enctype="multipart/form-data">

                <label for="userName">Username</label>
                <input type="text" id="userName" name="userName" value="${user.userName}" required>

                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${user.firstName}">

                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${user.lastName}">

                <label for="phoneNumber">Phone Number</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}">

                <label for="licenseNumber">License Number</label>
                <input type="text" id="licenseNumber" name="licenseNumber" value="${user.licenseNumber}">

                <label for="address">Address</label>
                <input type="text" id="address" name="address" value="${user.address}">

                <!-- Keep old avatar if no new upload -->
                <input type="hidden" name="oldAvatar" value="${user.avatar}">

                <!-- Optional: show current avatar -->
                <c:if test="${not empty user.avatar}">
                    <p>Current Avatar:</p>
                    <img src="${pageContext.request.contextPath}${user.avatar}" alt="Avatar" width="120">
                    <br><br>
                </c:if>

                <label for="avatar">Upload Avatar</label>
                <input type="file" id="avatar" name="avatar" accept="image/*">

                <button type="submit">Save Changes</button>
            </form>
        </div>
    </body>
</html>