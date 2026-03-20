<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Ký</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/signup.css">
    </head>
    <body>
        <jsp:include page="./component/navbar.jsp"/>

        <div class="content-wrapper">
            <form action="${pageContext.request.contextPath}/user/SignUp" method="POST" class="signup-form">
                <h2>Đăng Ký</h2>

                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>

                <!-- Row 1 -->
                <div class="form-row">
                    <div class="form-group">
                        <label>Tên Đăng Nhập</label>
                        <input type="text" name="username" value="${username}" required>
                    </div>
                    <div class="form-group">
                        <label>Mật Khẩu</label>
                        <input type="password" name="password" required>
                    </div>
                </div>

                <!-- Row 2 -->
                <div class="form-row">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${email}" required>
                    </div>
                    <div class="form-group">
                        <label>CCCD/CMT</label>
                        <input type="text" name="citizen_id" value="${citizen_id}" required>
                    </div>
                </div>

                <!-- Row 3 -->
                <div class="form-row">
                    <div class="form-group">
                        <label>Tên</label>
                        <input type="text" name="firstname" value="${firstname}" required>
                    </div>
                    <div class="form-group">
                        <label>Họ</label>
                        <input type="text" name="lastname" value="${lastname}" required>
                    </div>
                </div>

                <!-- Row 4 -->
                <div class="form-row">
                    <div class="form-group">
                        <label>Số Điện Thoại</label>
                        <input type="text" name="phonenumber" value="${phonenumber}" required>
                    </div>
                    <div class="form-group">
                        <label>Bằng Lái Xe</label>
                        <input type="text" name="licensenumber" value="${licensenumber}" required>
                    </div>
                </div>

                <!-- Row 5: full width -->
                <div class="form-group">
                    <label>Địa Chỉ</label>
                    <input type="text" name="address" value="${address}" required>
                </div>
                <div class="form-group">
                    <label>Số Tài Khoản Ngân Hàng</label>
                    <input type="text" name="banknumber" value="${banknumber}" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Đăng Ký">
                </div>

                <div class="signup-link">
                    <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/Login">Đăng Nhập</a></p>
                </div>
            </form>
        </div>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>