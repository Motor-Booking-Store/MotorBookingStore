<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login.css">
    </head>
    <body>
        <jsp:include page="./component/navbar.jsp"/>

        <div class="content-wrapper">
            <form action="${pageContext.request.contextPath}/Login" method="POST">
                <h2>Login</h2>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="text" name="email" value="${email}" required>
                </div>

                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Login">
                </div>

                <div class="error-message">
                    ${error}
                </div>

                <div class="signup-link">
                    <p>Don't have an account? <a href="${pageContext.request.contextPath}/user/SignUp">Sign Up</a></p>
                </div>
            </form>
        </div>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>
