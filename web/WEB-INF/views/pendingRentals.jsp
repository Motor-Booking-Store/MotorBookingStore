<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Yêu Cầu Thuê Chưa Xử Lý</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/pendingRentals.css">
    </head>

    <body>

        <jsp:include page="admin/component/adminNavbar.jsp" />

        <div class="container">
            <div class="filter-links">
                <a class="filter-link" href="${pageContext.request.contextPath}/admin/pending-rentals?status=all">Tất cả</a> |
                <a class="filter-link" href="${pageContext.request.contextPath}/admin/pending-rentals?status=Pending">Đang chờ</a> |
                <a class="filter-link" href="${pageContext.request.contextPath}/admin/pending-rentals?status=Approved">Đã duyệt</a> |
                <a class="filter-link" href="${pageContext.request.contextPath}/admin/pending-rentals?status=Completed">Hoàn thành</a> |
                <a class="filter-link" href="${pageContext.request.contextPath}/admin/pending-rentals?status=Cancelled">Đã hủy</a>
            </div>

            <h2 class="page-title">Yêu Cầu Thuê: ${currentStatus}</h2>

            <table class="rental-table">
                <thead>
                    <tr>
                        <th>Mã Thuê</th>
                        <th>Khách Hàng</th>
                        <th>Xe Máy</th>
                        <th>Hình Ảnh</th>
                        <th>Ngày Bắt Đầu</th>
                        <th>Ngày Kết Thúc</th>
                        <th>Tổng Tiền</th>
                        <th>Địa Chỉ</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${pendingList}">
                        <tr>
                            <td class="rental-id">${r.rentalId}</td>
                            <td class="customer">${r.firstName} ${r.lastName}</td>
                            <td class="bike-name">${r.bikeName}</td>
                            <td class="bike-image"><img src="${r.image}" alt="${r.bikeName}"/></td>
                            <td class="start-date">${r.startDate}</td>
                            <td class="end-date">${r.endDate}</td>
                            <td class="total-amount">${r.totalAmount}</td>
                            <td class="address">${r.address}</td>
                            <td class="status">${r.status}</td>
                            <td class="actions">
                                <c:choose>
                                    <c:when test="${r.status == 'Pending'}">
                                        <form action="update-rental-status" method="post" class="status-form">
                                            <input type="hidden" name="id" value="${r.rentalId}">
                                            <button type="submit" name="action" value="approve" class="btn approve-btn">Duyệt</button>
                                            <button type="submit" name="action" value="cancel" class="btn cancel-btn">Hủy</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="processed">Đã xử lý</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>