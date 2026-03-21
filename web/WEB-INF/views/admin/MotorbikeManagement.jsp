<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý xe máy</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/listMotorbike.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <h2>Quản lý xe máy</h2>

        <!-- Nút Thêm -->
        <a href="${pageContext.request.contextPath}/admin/AddMotorbike">
            <button>Thêm xe máy</button>
        </a>

        <br><br>

        <table border="1" cellpadding="10">

            <tr>
                <th>ID</th>
                <th>Tên xe</th>
                <th>Hình ảnh</th>
                <th>Hãng</th>
                <th>Dòng xe</th>
                <th>Biển số</th>
                <th>Giá/ngày</th>
                <th>Địa điểm</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>

            <c:forEach var="m" items="${motorbikeList}">
                <tr>
                    <td>${m.bikeId}</td>
                    <td>${m.bikeName}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/${m.image}" width="100"/>
                    </td>
                    <td>${m.brand}</td>
                    <td>${m.model}</td>
                    <td>${m.licensePlate}</td>
                    <td>${m.pricePerDay}</td>
                    <td>${m.locationName}</td>
                    <td>${m.status}</td>

                    <td>
                        <!-- SỬA -->
                        <a href="${pageContext.request.contextPath}/admin/EditMotorbike?id=${m.bikeId}">
                            <button>Sửa</button>
                        </a>

                        <!-- XÓA -->
                        <a href="${pageContext.request.contextPath}/admin/DeleteMotorbike?id=${m.bikeId}"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa xe này không?');">
                            <button>Xóa</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>