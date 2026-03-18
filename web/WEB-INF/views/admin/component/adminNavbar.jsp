<%-- 
    Document   : adminNavbar
    Created on : Mar 18, 2026, 5:26:36 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/adminNavbar.css">
<div class="navbar">
    <div class="navbar-left">
        <img src="${pageContext.request.contextPath}/images/logo/logo.png" alt="logo" class="navbar-logo">
    </div>
    <div class="navbar-center">
        <a href="${pageContext.request.contextPath}/admin/Home" class="nav-link">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/admin/UserManagement" class="nav-link">Quản lý tài khoản</a>
        <a href="${pageContext.request.contextPath}/admin/MotorbikeManagement" class="nav-link">Quản lý xe máy</a>
        <a href="${pageContext.request.contextPath}/admin/pending-rentals" class="nav-link">Quản lý đơn thuê xe</a>
    </div>
    <div class="navbar-right">
        <c:if test="${sessionScope.user != null}">
            <a href="${pageContext.request.contextPath}/Logout" class="nav-action">Logout</a>
        </c:if>
    </div>
</div>

