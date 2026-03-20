<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Nhập</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login.css">
    </head>
    <body>
        <jsp:include page="./component/navbar.jsp"/>

        <div class="content-wrapper">
            <form action="${pageContext.request.contextPath}/Login" method="POST">
                <h2>Đăng Nhập</h2>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="text" name="email" value="${email}" required>
                </div>

                <div class="form-group">
                    <label>Mật Khẩu:</label>
                    <input type="password" name="password" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Đăng Nhập">
                </div>

                <div class="error-message">
                    ${error}
                </div>

                <div class="signup-link">
                    <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/SignUp">Đăng Ký</a></p>
                </div>
            </form>
        </div>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>