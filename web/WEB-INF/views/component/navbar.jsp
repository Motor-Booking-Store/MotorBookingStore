<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/navbar.css">

<div class="navbar">

    <div class="nav-logo">
        <img src="${pageContext.request.contextPath}/images/logo/logo.png" alt="logo">
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/Home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/MotorbikeList">Danh Sách Xe Máy</a>
        <a href="${pageContext.request.contextPath}/user/rental-history?userId=${sessionScope.user.userID}">Lịch Sử Thuê Xe</a>
        <a href="${pageContext.request.contextPath}/user/UserDetail">Thông Tin Tài Khoản</a>
    </div>

    <div class="nav-user">

        <!-- Nếu đã đăng nhập -->
        <c:if test="${sessionScope.user != null}">
            <a href="${pageContext.request.contextPath}/Logout" class="logout-btn">Đăng Xuất</a>
            <img src="${pageContext.request.contextPath}${sessionScope.user.avatar}" alt="avatar" class="nav-avatar"/>
        </c:if>

        <!-- Nếu chưa đăng nhập -->
        <c:if test="${sessionScope.user == null}">
            <a href="${pageContext.request.contextPath}/Login" class="login-btn">Đăng Nhập</a>
        </c:if>

    </div>

</div>