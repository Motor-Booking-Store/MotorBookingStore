<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>

        <link rel="stylesheet" 
              href="${pageContext.request.contextPath}/static/admin.css">

    </head>

    <body>

        <div class="container">

            <!-- SIDEBAR -->
            <div class="sidebar">

                <div>

                    <div class="profile">

                        <img src="${pageContext.request.contextPath}/images/avatar.png">

                        <div>${sessionScope.account.userName}</div>

                        <div class="role">
                            <c:choose>
                                <c:when test="${sessionScope.account.roleId == 1}">
                                    Admin
                                </c:when>
                                <c:otherwise>
                                    Customer
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>

                    <div class="menu">

                        <a href="${pageContext.request.contextPath}/admin/UserManagement">
                            Account Management
                        </a>

                        <a href="${pageContext.request.contextPath}/admin/MotorbikeManagement">
                            Motorbike Management
                        </a>

                        <a href="${pageContext.request.contextPath}/admin/pending-rentals">
                            Rental Management
                        </a>

                    </div>

                </div>

                <!-- LOGOUT -->

                <div class="logout">

                    <a href="${pageContext.request.contextPath}/Logout">
                        Logout
                    </a>

                </div>

            </div>

            <!-- MAIN CONTENT -->

            <div class="main">

                <div class="logo">
                    Motorbike Rental
                </div>

                <div class="slogan">
                    High quality motorbike rental service <br>
                    Safe - Affordable - Convenient
                </div>

            </div>

        </div>

    </body>
</html>