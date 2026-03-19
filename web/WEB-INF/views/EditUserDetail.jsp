<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh Sửa Thông Tin Người Dùng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/edit-user.css">
    </head>
    <body>
        <div>
            <jsp:include page="./component/navbar.jsp"/>
        </div>
        <div class="container">
            <h2>Chỉnh Sửa Thông Tin Cá Nhân</h2>
            <div class="back-btn">
                <a href="${pageContext.request.contextPath}/user/UserDetail">
                    <button type="button">Quay Về</button>
                </a>
            </div>
            <form action="${pageContext.request.contextPath}/user/EditUserDetail" 
                  method="post" 
                  enctype="multipart/form-data">

                <div class="form-grid">

                    <div class="form-group">
                        <label>Tên Đăng Nhập</label>
                        <input type="text" name="userName" value="${user.userName}" required>
                    </div>

                    <div class="form-group">
                        <label>Số Điện Thoại</label>
                        <input type="text" name="phoneNumber" value="${user.phoneNumber}">
                    </div>

                    <div class="form-group">
                        <label>Tên</label>
                        <input type="text" name="firstName" value="${user.firstName}">
                    </div>

                    <div class="form-group">
                        <label>Họ</label>
                        <input type="text" name="lastName" value="${user.lastName}">
                    </div>

                    <div class="form-group">
                        <label>Bằng Lái Xe</label>
                        <input type="text" name="licenseNumber" value="${user.licenseNumber}">
                    </div>

                    <div class="form-group">
                        <label>Địa Chỉ</label>
                        <input type="text" name="address" value="${user.address}">
                    </div>

                    <!-- Avatar full width -->
                    <input type="hidden" name="oldAvatar" value="${user.avatar}">

                    <div class="form-group full">
                        <c:if test="${not empty user.avatar}">
                            <div class="avatar-preview">
                                <p>Avatar Hiện Tại:</p>
                                <img src="${pageContext.request.contextPath}${user.avatar}" width="120">
                            </div>
                        </c:if>
                    </div>

                    <div class="form-group full">
                        <label>Tải Lên Ảnh Đại Diện</label>
                        <input type="file" name="avatar" accept="image/*">
                    </div>

                    <div class="submit-btn">
                        <button type="submit">Lưu Thay Đổi</button>
                    </div>

                </div>
            </form>
        </div>
        <div>
            <jsp:include page="./component/footer.jsp"/>
        </div>
    </body>
</html>