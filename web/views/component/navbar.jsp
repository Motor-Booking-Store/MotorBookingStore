<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="static/navbar.css">

<div class="navbar">

    <div class="nav-logo">
        <img src="images/logo.png" alt="logo">
    </div>

    <div class="nav-menu">
        <a href="Home">Home</a>
        <a href="MotorbikeList">Motorbikes</a>
        <a href="#">Contact</a>
        <a href="#">About</a>
    </div>

    <div class="nav-user">

        <!-- nếu đã login -->
        <c:if test="${sessionScope.user != null}">
            <a class="username">${sessionScope.user.userName}</a>
        </c:if>

        <!-- nếu chưa login -->
        <c:if test="${sessionScope.user == null}">
            <a href="Login" class="login-btn">Login</a>
        </c:if>

    </div>

</div>