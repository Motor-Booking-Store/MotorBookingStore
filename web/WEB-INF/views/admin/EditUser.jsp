<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chỉnh sửa người dùng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/editUser.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <div class="container">

            <!-- NỘI DUNG CHÍNH -->
            <div class="main-content">
                <h2>Chỉnh sửa người dùng</h2>

                <form action="${pageContext.request.contextPath}/admin/EditUser" method="post" class="form">

                    <input type="hidden" name="userID" value="${user.userID}" />

                    <div class="form-group">
                        <label>Tên đăng nhập</label>
                        <input type="text" name="username" value="${user.userName}" readonly>
                    </div>

                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="text" name="password" value="${user.password}">
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${user.email}">
                    </div>

                    <div class="form-group">
                        <label>CCCD</label>
                        <input type="text" name="citizen_id" value="${user.citizenId}" readonly>
                    </div>

                    <div class="form-group">
                        <label>Tên</label>
                        <input type="text" name="firstname" value="${user.firstName}">
                    </div>

                    <div class="form-group">
                        <label>Họ</label>
                        <input type="text" name="lastname" value="${user.lastName}">
                    </div>

                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${user.phoneNumber}" readonly>
                    </div>

                    <div class="form-group">
                        <label>Bằng lái xe</label>
                        <input type="text" name="licensenumber" value="${user.licenseNumber}" readonly>
                    </div>

                    <div class="form-group full-width">
                        <label>Địa chỉ</label>
                        <input type="text" name="address" value="${user.address}">

                    </div>

                    <div class="form-group full-width">
                        <label>Số tài khoản ngân hàng</label>
                        <input type="text" name="banknumber" value="${user.bankNumber}" readonly>
                    </div>

                    <div class="form-group full-width">
                        <label>Vai trò</label>
                        <select name="roleId">
                            <option value="1" ${user.roleId == 1 ? "selected" : ""}>Quản trị viên</option>
                            <option value="2" ${user.roleId == 2 ? "selected" : ""}>Customer</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Cập nhật</button>
                        <a href="${pageContext.request.contextPath}/admin/UserManagement" class="btn-cancel">
                            Hủy
                        </a>
                    </div>
                </form>
            </div>

        </div>  
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>