<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike Detail</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/detail.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

        <div class="bike-reviews">
            <div>
                <c:if test="${not empty sessionScope.user}">
                    <div class="write-comment">
                        <form action="${pageContext.request.contextPath}/user/AddReview" method="post">
                            <input type="hidden" name="bikeId" value="${bike.bikeId}">

                            <label>Rating:</label>
                            <select name="rating" required>
                                <option value="1">1 sao</option>
                                <option value="2">2 sao</option>
                                <option value="3">3 sao</option>
                                <option value="4">4 sao</option>
                                <option value="5" selected>5 sao</option>
                            </select>

                            <textarea name="comment" placeholder="Viết bình luận..." required></textarea>
                            <button type="submit">Gửi bình luận</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <h2>Bình luận của khách hàng</h2>

            <c:if test="${empty reviews}">
                <p>Chưa có bình luận nào.</p>
            </c:if>

            <c:forEach var="r" items="${reviews}">
                <div class="review-item">
                    <img src="${r.avatar}" alt="${r.userName}" class="review-avatar">
                    <div class="review-content">
                        <strong class="review-username">${r.userName}</strong>

                        <div class="review-rating">
                            <c:forEach begin="1" end="${r.rating}" var="i">
                                <i class="fa fa-star"></i>
                            </c:forEach>
                            <c:forEach begin="${r.rating + 1}" end="5" var="i">
                                <i class="fa fa-star-o"></i>
                            </c:forEach>
                        </div>

                        <p class="review-comment">${r.comment}</p>
                    </div>
                </div>
            </c:forEach>
        </div>

        <jsp:include page="./component/footer.jsp"/>

    </body>
</html>