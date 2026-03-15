<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike Detail</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/detail.css">
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
                            <p>
                                <b>Status:</b>
                                <span style="
                                      font-weight: bold;
                                      color:
                                      ${bike.status == STATUS_AVAILABLE ? 'green' :
                                        (bike.status == STATUS_RENTED ? 'red' : 'orange')}">
                                          ${bike.status}
                                      </span>
                                </p>
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

                            <!--rent now button-->       
                            <c:if test="${not empty sessionScope.user}">

                                <c:if test="${not empty error}">
                                    <p style="color:red; font-weight:bold; margin-top:10px;">${error}</p>
                                </c:if>

                                <c:if test="${not empty success}">
                                    <p style="color:green; font-weight:bold; margin-top:10px;">${success}</p>
                                </c:if>

                                <button type="button" class="rent-btn" onclick="toggleRentForm()">
                                    Rent Now
                                </button>

                                <div id="rentFormContainer" style="display:none; margin-top:20px;">
                                    <form action="${pageContext.request.contextPath}/user/CreateRental" method="post">
                                        <input type="hidden" name="bikeId" value="${bike.bikeId}">

                                        <div style="margin-bottom:10px;">
                                            <label for="startDate"><b>Start Date:</b></label><br>
                                            <input type="date" name="startDate" id="startDate" required>
                                        </div>

                                        <div style="margin-bottom:10px;">
                                            <label for="endDate"><b>End Date:</b></label><br>
                                            <input type="date" name="endDate" id="endDate" required>
                                        </div>

                                        <button type="submit" class="rent-btn">Confirm Rent</button>
                                    </form>
                                </div>
                            </c:if>

                            <c:if test="${empty sessionScope.user}">
                                <a href="${pageContext.request.contextPath}/user/Login" class="rent-btn" style="display:inline-block; text-decoration:none; text-align:center;">
                                    Login to Rent
                                </a>
                            </c:if>

                        </div>

                    </div>

                </c:if>

                <!-- nếu không có dữ liệu -->
                <c:if test="${bike == null}">
                    <h2 style="text-align:center;">Motorbike not found</h2>
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
                        <img src="${pageContext.request.contextPath}${r.avatar}" alt="${r.userName}" class="review-avatar">
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


            <script>
                function toggleRentForm() {
                    const form = document.getElementById("rentFormContainer");
                    if (form.style.display === "none" || form.style.display === "") {
                        form.style.display = "block";
                    } else {
                        form.style.display = "none";
                    }
                }

                window.addEventListener("DOMContentLoaded", function () {
                    const startInput = document.getElementById("startDate");
                    const endInput = document.getElementById("endDate");

                    const today = new Date();
                    const tomorrow = new Date();
                    tomorrow.setDate(today.getDate() + 1);

                    function formatDate(date) {
                        const year = date.getFullYear();
                        const month = String(date.getMonth() + 1).padStart(2, '0');
                        const day = String(date.getDate()).padStart(2, '0');
                        return year + "-" + month + "-" + day;
                    }

                    const todayStr = formatDate(today);
                    const tomorrowStr = formatDate(tomorrow);

                    startInput.min = todayStr;
                    endInput.min = todayStr;

                    if (!startInput.value) {
                        startInput.value = todayStr;
                    }

                    if (!endInput.value) {
                        endInput.value = tomorrowStr;
                    }

                    startInput.addEventListener("change", function () {
                        endInput.min = startInput.value;

                        if (endInput.value < startInput.value) {
                            endInput.value = startInput.value;
                        }
                    });
                });
            </script>
        </body>
    </html>