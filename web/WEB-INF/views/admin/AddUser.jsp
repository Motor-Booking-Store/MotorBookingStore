<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm người dùng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/addUser.css">
    </head>

    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>

        <div class="container">
            <div class="form-card">
                <h2>Thêm người dùng mới</h2>

                <!-- HIỂN THỊ LỖI -->
                <c:if test="${not empty error}">
                    <p class="error">${error}</p>
                </c:if>

                <form action="adduser" method="POST" class="form">

                    <div class="form-group">
                        <label>Tên đăng nhập</label>
                        <input type="text" name="username" value="${username}">
                    </div>

                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="password">
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${email}">
                    </div>

                    <div class="form-group">
                        <label>CCCD</label>
                        <input type="text" name="citizen_id" value="${citizen_id}">
                    </div>

                    <div class="form-group">
                        <label>Tên</label>
                        <input type="text" name="firstname" value="${firstname}">
                    </div>

                    <div class="form-group">
                        <label>Họ</label>
                        <input type="text" name="lastname" value="${lastname}">
                    </div>

                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${phonenumber}">
                    </div>

                    <div class="form-group">
                        <label>Bằng lái xe</label>
                        <input type="text" name="licensenumber" value="${licensenumber}">
                    </div>

                    <div class="form-group full-width">
                        <label>Địa chỉ</label>
                        <input type="text" name="address" value="${address}">
                    </div>

                    <div class="form-group full-width">
                        <label>Số tài khoản ngân hàng</label>
                        <input type="text" name="banknumber" value="${banknumber}">
                    </div>

                    <div class="form-group full-width">
                        <label>Vai trò</label>
                        <select name="roleId">
                            <option value="1">Quản trị viên</option>
                            <option value="2">Khách hàng</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Thêm người dùng</button>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>