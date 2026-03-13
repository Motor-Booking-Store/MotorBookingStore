<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/navbar.css">

<div class="navbar">

    <div class="nav-logo">
        <img src="${pageContext.request.contextPath}/images/logo/logo.png" alt="logo">
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/user/Home">Home</a>
        <a href="${pageContext.request.contextPath}/user/MotorbikeList">Motorbikes</a>
        <a href="#">Contact</a>
        <a href="#">About</a>
    </div>

    <div class="nav-user">

        <!-- nếu đã login -->
        <c:if test="${sessionScope.user != null}">
            <a href="${pageContext.request.contextPath}/user/Logout" class="logout-btn">Logout</a>
            
            <a href="UserDetail" class="user-info">
                <img src="${sessionScope.user.avatar}" alt="avatar" class="nav-avatar"/>
                <span class="username">${sessionScope.user.userName}</span>
            </a>
        </c:if>

        <!-- nếu chưa login -->
        <c:if test="${sessionScope.user == null}">
            <a href="${pageContext.request.contextPath}/user/Login" class="login-btn">Login</a>
        </c:if>

    </div>

</div>