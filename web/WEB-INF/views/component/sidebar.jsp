<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/sidebar.css">

<div class="sidebar">

    <h2 class="sidebar-title">Tìm kiếm xe máy</h2>

    <form action="${pageContext.request.contextPath}/user/MotorbikeList" method="get">

        <!-- Brand -->
        <div class="filter-group">
            <label>Hãng xe</label>
            <select name="brand">
                <option value="">Tất cả</option>
                <c:forEach var="br" items="${brandList}">
                    <option value="${br}" ${brand == br ? 'selected' : ''}>${br}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Price -->
        <div class="filter-group">
            <label>Giá theo ngày</label>
            <select name="priceRange">
                <option value="">Tất cả</option>
                <option value="under150000" ${priceRange == 'under150000' ? 'selected' : ''}>Dưới 150.000</option>
                <option value="150000to200000" ${priceRange == '150000to200000' ? 'selected' : ''}>150.000 - 200.000</option>
                <option value="above200000" ${priceRange == 'above200000' ? 'selected' : ''}>Trên 200.000</option>
            </select>
        </div>

        <!-- Status -->
        <div class="filter-group">
            <label>Trạng thái</label>
            <select name="status">
                <option value="">Tất cả</option>
                <option value="Available" ${status == 'Available' ? 'selected' : ''}>Chưa thuê</option>
                <option value="Rented" ${status == 'Rented' ? 'selected' : ''}>Đã thuê</option>
                <option value="Maintenance" ${status == 'Maintenance' ? 'selected' : ''}>Bảo trì</option>
            </select>
        </div>

        <button class="filter-btn" type="submit">Tìm kiếm</button>

        <a href="${pageContext.request.contextPath}/user/MotorbikeList" class="filter-btn reset-btn">
            Đặt lại
        </a>

    </form>

</div>