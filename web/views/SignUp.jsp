<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="static/signup.css">
</head>

<body>

<div class="signup-container">

    <form action="SignUp" method="POST" class="signup-form">

        <h2>Sign Up</h2>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" value="${username}">
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password">
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="text" name="email" value="${email}">
        </div>

        <div class="form-group">
            <label>CCCD/CMT</label>
            <input type="text" name="citizen_id" value="${citizen_id}">
        </div>

        <div class="form-group">
            <label>First Name</label>
            <input type="text" name="firstname" value="${firstname}">
        </div>

        <div class="form-group">
            <label>Last Name</label>
            <input type="text" name="lastname" value="${lastname}">
        </div>

        <div class="form-group">
            <label>Phone Number</label>
            <input type="text" name="phonenumber" value="${phonenumber}">
        </div>

        <div class="form-group">
            <label>License Number</label>
            <input type="text" name="licensenumber" value="${licensenumber}">
        </div>

        <div class="form-group">
            <label>Address</label>
            <input type="text" name="address" value="${address}">
        </div>

        <div class="form-group">
            <label>Bank Number</label>
            <input type="text" name="banknumber" value="${banknumber}">
        </div>

        <button type="submit" class="btn-submit">Sign Up</button>

    </form>

</div>

</body>
</html>