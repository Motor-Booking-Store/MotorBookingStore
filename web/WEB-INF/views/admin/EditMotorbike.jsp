<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Chỉnh sửa xe máy</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/editMotorbike.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <h2>Chỉnh sửa xe máy</h2>

        <!-- LỖI -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/EditMotorbike"
              method="post"
              enctype="multipart/form-data">

            <!-- ID + ẢNH CŨ -->
            <input type="hidden" name="bikeId" value="${bike.bikeId}">
            <input type="hidden" name="oldImage" value="${bike.image}">

            <table>

                <tr>
                    <td>Tên xe:</td>
                    <td>
                        <input type="text" name="bikeName" value="${bike.bikeName}" required>
                    </td>
                </tr>

                <tr>
                    <td>Hãng:</td>
                    <td>
                        <input type="text" name="brand" value="${bike.brand}" required>
                    </td>
                </tr>

                <tr>
                    <td>Dòng xe:</td>
                    <td>
                        <input type="text" name="model" value="${bike.model}">
                    </td>
                </tr>

                <tr>
                    <td>Biển số:</td>
                    <td>
                        <input type="text" name="licensePlate" value="${bike.licensePlate}" required>
                    </td>
                </tr>

                <tr>
                    <td>Giá mỗi ngày:</td>
                    <td>
                        <input type="number" step="0.01" name="pricePerDay" value="${bike.pricePerDay}" required>
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
                        <textarea name="description" rows="4">${bike.description}</textarea>
                    </td>
                </tr>

                <!-- ẢNH -->
                <tr>
                    <td>Ảnh hiện tại:</td>
                    <td>
                        <c:if test="${not empty bike.image}">
                            <img src="${pageContext.request.contextPath}${bike.image}" width="150">
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td>Tải ảnh mới:</td>
                    <td>
                        <input type="file" name="imageUpload" accept="image/*">
                    </td>
                </tr>

                <!-- TRẠNG THÁI -->
                <tr>
                    <td>Trạng thái:</td>
                    <td>
                        <select name="status">
                            <option value="Available" ${bike.status eq 'Available' ? 'selected' : ''}>
                                Có sẵn
                            </option>
                            <option value="Rented" ${bike.status eq 'Rented' ? 'selected' : ''}>
                                Đang cho thuê
                            </option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit">Cập nhật xe</button>
                    </td>
                </tr>

            </table>

        </form>
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>