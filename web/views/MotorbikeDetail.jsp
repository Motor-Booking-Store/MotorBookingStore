<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike Detail</title>

        <link rel="stylesheet" href="static/detail.css">

    </head>

    <body>

        <jsp:include page="./component/navbar.jsp"/>

        <div class="detail-wrapper">

            <!-- nếu có dữ liệu -->
            <c:if test="${bike != null}">

                <div class="detail-container">

                    <!-- LEFT IMAGE -->
                    <div class="detail-image">

                        <img src="${bike.image}" 
                             alt="${bike.bikeName}">

                    </div>


                    <!-- RIGHT INFO -->
                    <div class="detail-info">

                        <h1 class="bike-title">${bike.bikeName}</h1>

                        <div class="bike-meta">
                            <p><b>Brand:</b> ${bike.brand}</p>
                            <p><b>Model:</b> ${bike.model}</p>
                            <p><b>License Plate:</b> ${bike.licensePlate}</p>
                            <p><b>Price Per Day:</b> ${bike.pricePerDay} VND</p>
                            <p><b>Status:</b> ${bike.status}</p>
                        </div>

                        <div class="bike-location">

                            <h3>Location</h3>

                            <p><b>Store:</b> ${bike.locationName}</p>
                            <p><b>Address:</b> ${bike.address}</p>

                        </div>

                        <div class="bike-description">

                            <h3>Description</h3>

                            <p>${bike.description}</p>

                        </div>

                        <button class="rent-btn">
                            Rent Now
                        </button>

                    </div>

                </div>

            </c:if>

            <!-- nếu không có dữ liệu -->
            <c:if test="${bike == null}">
                <h2 style="text-align:center;">Motorbike not found</h2>
            </c:if>

        </div>

        <jsp:include page="./component/footer.jsp"/>

    </body>
</html>