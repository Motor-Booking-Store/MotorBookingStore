<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike List</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/card.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/search.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layout.css">

    </head>

    <body>

        <jsp:include page="./component/navbar.jsp" />

        <!-- SEARCH BAR -->
        <div class="search-container">

            <form action="searchMotorbike" method="get" class="search-form">

                <input 
                    type="text"
                    name="keyword"
                    placeholder="Search motorbike name..."
                    class="search-input"
                    >

                <button type="submit" class="search-btn">
                    Search
                </button>

            </form>

        </div>


        <!-- MAIN LAYOUT -->
        <div class="main-layout">

            <!-- SIDEBAR -->
            <jsp:include page="./component/sidebar.jsp"/>

            <!-- CONTENT -->
            <div class="content">

                <div class="bike-container">

                    <c:forEach var="b" items="${motorbikeList}">

                        <a href="${pageContext.request.contextPath}/user/MotorbikeDetail?id=${b.bikeId}" class="bike-link">

                            <div class="bike-card">

                                <img class="bike-img" src="${b.image}">

                                <div class="bike-info">
                                    <p><b>Bike Name:</b> ${b.bikeName}</p>
                                    <p><b>Brand:</b> ${b.brand}</p>
                                    <p><b>Model:</b> ${b.model}</p>
                                    <p><b>License Plate:</b> ${b.licensePlate}</p>
                                    <p><b>Price Per Day:</b> ${b.pricePerDay}</p>
                                    <p><b>Status:</b> ${b.status}</p>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>

        </div>

        <jsp:include page="./component/footer.jsp" />

    </body>
</html>