<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/sidebar.css">

<div class="sidebar">

    <h2 class="sidebar-title">Tìm kiếm xe máy</h2>

    <form action="${pageContext.request.contextPath}/user/MotorbikeList" method="get">

        <!-- Brand -->
        <div class="filter-group">
            <label>Hãng xe</label>
            <select name="brand">
                <option value="all">Tất cả</option>
                <option value="Honda">Honda</option>
                <option value="Yamaha">Yamaha</option>
                <option value="Suzuki">Suzuki</option>
            </select>
        </div>

        <!-- Price -->
        <div class="filter-group">
            <label>Giá theo ngày</label>
            <select name="priceRange">
                <option value="all">Tất cả</option>
                <option value="under10000">Dưới 10000</option>
                <option value="10000to20000">Từ 10000 - 20000</option>
                <option value="above20000">Trên 20000</option>
            </select>
        </div>

        <!-- Status -->
        <div class="filter-group">
            <label>Trạng thái</label>
            <select name="status">
                <option value="all">Tất cả</option>
                <option value="Available">Đã thuê</option>
                <option value="Rented">Chưa thuê</option>
            </select>
        </div>

        <button class="filter-btn" type="submit">Tìm kiếm</button>

    </form>

</div>