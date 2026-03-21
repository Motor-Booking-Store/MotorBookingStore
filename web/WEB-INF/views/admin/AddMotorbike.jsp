<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Thêm xe máy</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/addMotorbike.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <h2>Thêm xe máy</h2>

        <!-- Hiển thị lỗi -->
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/AddMotorbike" 
              method="post" 
              enctype="multipart/form-data">

            <table>

                <tr>
                    <td>Tên xe:</td>
                    <td>
                        <input type="text" name="bikeName" value="${bikeName}">
                    </td>
                </tr>

                <tr>
                    <td>Hãng:</td>
                    <td>
                        <input type="text" name="brand" value="${brand}">
                    </td>
                </tr>

                <tr>
                    <td>Dòng xe:</td>
                    <td>
                        <input type="text" name="model" value="${model}">
                    </td>
                </tr>

                <tr>
                    <td>Biển số:</td>
                    <td>
                        <input type="text" name="licensePlate" value="${licensePlate}">
                    </td>
                </tr>

                <tr>
                    <td>Giá mỗi ngày:</td>
                    <td>
                        <input type="text" name="pricePerDay" value="${pricePerDay}">
                    </td>
                </tr>

                <tr>
                    <td>Địa điểm:</td>
                    <td>
                        <select name="locationId">
                            <c:forEach var="l" items="${locationList}">
                                <option value="${l.locationId}">${l.locationName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Mô tả:</td>
                    <td>
                        <textarea name="description">${description}</textarea>
                    </td>
                </tr>

                <tr>
                    <td>Hình ảnh:</td>
                    <td>
                        <input type="hidden" name="image" value="${motorbike.image}">
                        <label for="image">Tải ảnh lên</label>
                        <input type="file" id="image" name="imageUpload" accept="image/*">
                    </td>
                </tr>

                <tr>
                    <td>Trạng thái:</td>
                    <td>
                        <select name="status">
                            <option value="Available" ${status == 'Available' ? 'selected' : ''}>Có sẵn</option>
                            <option value="Rented" ${status == 'Rented' ? 'selected' : ''}>Đang cho thuê</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit">Thêm xe máy</button>
                    </td>
                </tr>

            </table>

        </form>
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>