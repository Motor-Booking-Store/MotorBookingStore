<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/sidebar.css">

<div class="sidebar">

    <h2 class="sidebar-title">Filter Motorbikes</h2>

    <form action="filterMotorbike" method="get">

        <!-- Rent Date -->
        <div class="filter-group">
            <label>Rent Date</label>
            <input type="date" name="rentDate">
        </div>

        <!-- Return Date -->
        <div class="filter-group">
            <label>Return Date</label>
            <input type="date" name="returnDate">
        </div>

        <!-- Brand -->
        <div class="filter-group">
            <label>Brand</label>
            <select name="brand">
                <option value="">All</option>
                <option value="Honda">Honda</option>
                <option value="Yamaha">Yamaha</option>
                <option value="Suzuki">Suzuki</option>
            </select>
        </div>

        <!-- Price -->
        <div class="filter-group">
            <label>Price per day</label>
            <select name="price">
                <option value="">All</option>
                <option value="50">Under $50</option>
                <option value="100">Under $100</option>
                <option value="200">Under $200</option>
            </select>
        </div>

        <!-- Status -->
        <div class="filter-group">
            <label>Status</label>
            <select name="status">
                <option value="">All</option>
                <option value="Available">Available</option>
                <option value="Rented">Rented</option>
            </select>
        </div>

        <button class="filter-btn" type="submit">Apply Filter</button>

    </form>

</div>