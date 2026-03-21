<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Lịch Sử Thuê Xe Của Tôi</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/rental-history.css">
    </head>

    <body>
        <jsp:include page="./component/navbar.jsp"/>
        <div class="layout-wrappe">
            <div class="layout-main">
                <h2>Lịch Sử Thuê Xe Của Tôi</h2>

                <div class="filter-bar">
                    <a href="?status=all&userId=${sessionScope.user.userID}">Tất cả</a>
                    <a href="?status=Pending&userId=${sessionScope.user.userID}">Đang chờ</a>
                    <a href="?status=Approved&userId=${sessionScope.user.userID}">Đã duyệt</a>
                    <a href="?status=Completed&userId=${sessionScope.user.userID}">Hoàn thành</a>
                    <a href="?status=Cancelled&userId=${sessionScope.user.userID}">Đã hủy</a>
                </div>

                <h3>${currentStatus} đơn thuê</h3>

                <table border="1" class="rental-table">

                    <tr>
                        <th>ID Đơn Thuê</th>
                        <th>Xe Máy</th>  
                        <th>Hình Ảnh</th>
                        <th>Ngày Bắt Đầu</th>
                        <th>Ngày Kết Thúc</th>
                        <th>Tổng Tiền</th>
                        <th>Địa Chỉ</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>

                    <c:forEach var="r" items="${pendingList}">
                        <tr>
                            <td>
                                ${r.rentalId}
                            </td>

                            <td>
                                ${r.bikeName}
                            </td>

                            <td>
                                <img src="${r.image}" alt="error"/>
                            </td>

                            <td>${r.startDate}</td>

                            <td>${r.endDate}</td>

                            <td>${r.totalAmount}</td>

                            <td>${r.address}</td>

                            <td>
                                <c:choose>

                                    <c:when test="${r.status == 'Pending'}">
                                        <span style="color:orange;">Đang chờ</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Approved'}">
                                        <span style="color:blue;">Đã duyệt</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Completed'}">
                                        <span style="color:green;">Hoàn thành</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Cancelled'}">
                                        <span style="color:red;">Đã hủy</span>
                                    </c:when>

                                </c:choose>

                            </td>

                            <td>
                                <c:if test="${(r.status == 'Pending' || r.status == 'Approved') && today.time <= r.endDate.time}">

                                    <form action="${pageContext.request.contextPath}/user/CancelRental" method="post">
                                        <input type="hidden" name="rentalId" value="${r.rentalId}" />
                                        <button type="submit" class="cancel-btn" onclick="return confirm('Bạn có chắc muốn hủy đơn thuê này không?')">
                                            Hủy Thuê
                                        </button>
                                    </form>

                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <c:if test="${empty pendingList}">
                    <p>Không tìm thấy lịch sử thuê xe nào.</p>
                </c:if>
            </div>
        </div>
        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>