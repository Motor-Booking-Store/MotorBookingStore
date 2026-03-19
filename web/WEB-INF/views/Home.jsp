<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike Booking - Home</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/home.css">

    </head>

    <body>

        <jsp:include page="./component/navbar.jsp"/>

        <!-- HERO SECTION -->
        <section class="hero">

            <div class="hero-content">

                <h1>Thuê Xe Máy Phù Hợp Với Bạn</h1>

                <p>
                    Khám phá những chiếc xe máy tốt nhất cho hành trình của bạn.
                    Đặt xe nhanh chóng, giá cả hợp lý và dịch vụ đáng tin cậy.
                </p>

                <a href="MotorbikeList" class="hero-btn">
                    Xem danh sách xe
                </a>

            </div>

        </section>

        <section class="newMotorbike">
            <h2>Xe máy mới cập nhật</h2>
            <jsp:include page="./component/NewMotorbike.jsp"/>
        </section>
        
        <div class="section-divider"></div>


        <!-- ABOUT SECTION -->
        <section class="about">

            <h2>Tại Sao Chọn Chúng Tôi</h2>

            <div class="about-container">

                <div class="about-box">
                    <h3>Giá Cả Hợp Lý</h3>
                    <p>Chúng tôi cung cấp giá thuê cạnh tranh cho nhiều loại xe máy.</p>
                </div>

                <div class="about-box">
                    <h3>Đặt Xe Dễ Dàng</h3>
                    <p>Đặt xe yêu thích của bạn nhanh chóng chỉ với vài thao tác.</p>
                </div>

                <div class="about-box">
                    <h3>Dịch Vụ Uy Tín</h3>
                    <p>Xe máy chất lượng và hỗ trợ khách hàng chuyên nghiệp.</p>
                </div>

            </div>

        </section>

        <jsp:include page="./component/footer.jsp"/>
    </body>
</html>