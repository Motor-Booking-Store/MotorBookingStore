<%-- 
    Document   : UserDetail
    Created on : Mar 13, 2026, 10:34:39 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/userDetail.css">
    </head>
    <body>
        <jsp:include page="./component/navbar.jsp" />
        <div class="profile">
            <!-- Bên trái: Avatar -->
            <div class="profile-left">
                <img src="${userDetail.avatar}" alt="Avatar" class="profile-avatar"/>
                <a href="${pageContext.request.contextPath}/user/EditUserDetail" class="edit-btn">
                    Thay đổi Thông tin
                </a>
            </div>

            <!-- Bên phải: thông tin user -->
            <div class="profile-right">
                <div class="info-row">
                    <span class="label">Username:</span>
                    <span class="value">${userDetail.userName}</span>
                </div>
                <div class="info-row">
                    <span class="label">Email:</span>
                    <span class="value">${userDetail.email}</span>
                </div>
                <div class="info-row">
                    <span class="label">Họ & Tên:</span>
                    <span class="value">${userDetail.lastName} ${userDetail.firstName}</span>
                </div>
                <div class="info-row">
                    <span class="label">Phone:</span>
                    <span class="value">${userDetail.phoneNumber}</span>
                </div>
                <div class="info-row">
                    <span class="label">Địa chỉ:</span>
                    <span class="value">${userDetail.address}</span>
                </div>
                <div class="info-row">
                    <span class="label">Role:</span>
                    <span class="value">${userDetail.roleName}</span>
                </div>
                <div class="info-row">
                    <span class="label">Số bằng lái:</span>
                    <span class="value">${userDetail.licenseNumber}</span>
                </div>
                <div class="info-row">
                    <span class="label">Số tài khoản ngân hàng:</span>
                    <span class="value">${userDetail.bankNumber}</span>
                </div>
            </div>
        </div>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>
