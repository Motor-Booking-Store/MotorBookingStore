<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/newMotorbike.css">


<div class="container">
    <div class="grid">
        <c:forEach var="b" items="${newBikes}">

            <a href="${pageContext.request.contextPath}/MotorbikeDetail?id=${b.bikeId}" class="card-link">
                <div class="card">

                    <!-- LEFT: IMAGE -->
                    <img src="${pageContext.request.contextPath}/${b.image}" alt="${b.bikeName}">

                    <!-- RIGHT: INFO -->
                    <div class="card-content">
                        <h3>${b.bikeName}</h3>

                        <p><strong>Biển số xe:</strong> 
                            <span class="status">${b.licensePlate}</span>
                        </p>

                        <p class="date">
                            <strong>Thời gian xe mới về:</strong> ${b.createdAt}
                        </p>
                    </div>

                </div>
            </a>

        </c:forEach>
    </div>
</div>
