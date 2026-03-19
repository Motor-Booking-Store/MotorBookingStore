<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/adminHome.css">

    </head>

    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>

        <div>
            <!-- HERO SECTION -->
            <section class="hero">

                <div class="hero-content">

                    <h1>Chào Mừng Quản Trị viên</h1>
                    <p>Quản lý hệ thống xe máy, tài khoản người dùng và đơn thuê một cách dễ dàng và hiệu quả.</p>

                    <a href="MotorbikeManagement" class="hero-btn">
                        Xem danh sách xe
                    </a>

                </div>

            </section>

            <!-- ABOUT SECTION -->
            <section class="about">

                <h2>Các chức năng của admin</h2>

                <div class="about-container">

                    <div class="about-box">
                        <h3>Quản lý tài khoản</h3>
                        <p>Thêm, sửa, xóa và xem thông tin người dùng để đảm bảo hệ thống an toàn.</p>
                    </div>

                    <div class="about-box">
                        <h3>Quản lý xe máy</h3>
                        <p>Thêm xe mới, cập nhật thông tin xe và theo dõi tình trạng thuê của từng xe.</p>
                    </div>

                    <div class="about-box">
                        <h3>Quản lý đơn thuê xe</h3>
                         <p>Xem, phê duyệt hoặc từ chối các đơn thuê để đảm bảo trải nghiệm tốt cho khách hàng.</p>
                    </div>

                </div>

            </section>
        </div>

        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>