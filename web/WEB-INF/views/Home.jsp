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

                <h1>Rent Your Perfect Motorbike</h1>

                <p>
                    Discover the best motorbikes for your journey.
                    Fast booking, affordable prices, and reliable service.
                </p>

                <a href="MotorbikeList" class="hero-btn">
                    Browse Motorbikes
                </a>

            </div>

        </section>

        <!-- ABOUT SECTION -->
        <section class="about">

            <h2>Why Choose Us</h2>

            <div class="about-container">

                <div class="about-box">
                    <h3>Affordable Price</h3>
                    <p>We offer competitive rental prices for all types of motorbikes.</p>
                </div>

                <div class="about-box">
                    <h3>Easy Booking</h3>
                    <p>Book your favorite motorbike quickly with just a few clicks.</p>
                </div>

                <div class="about-box">
                    <h3>Trusted Service</h3>
                    <p>Reliable motorbikes and professional customer support.</p>
                </div>

            </div>

        </section>

        <jsp:include page="./component/footer.jsp"/>

    </body>
</html>