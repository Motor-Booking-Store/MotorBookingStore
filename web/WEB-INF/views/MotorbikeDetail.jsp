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
                        <img src="${pageContext.request.contextPath}/${bike.image}" alt="${bike.bikeName}">
                    </div>


                    <!-- RIGHT INFO -->
                    <div class="detail-info">

                        <h1 class="bike-title">${bike.bikeName}</h1>

                        <div class="bike-meta">
                            <p><b>Hãng:</b> ${bike.brand}</p>
                            <p><b>Dòng xe:</b> ${bike.model}</p>
                            <p><b>Biển số:</b> ${bike.licensePlate}</p>
                            <p><b>Giá thuê/ngày:</b> ${bike.pricePerDay} VND</p>
                            <p>
                                <b>Trạng thái:</b>
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

                                <h3>Địa điểm</h3>

                                <p><b>Cửa hàng:</b> ${bike.locationName}</p>
                                <p><b>Địa chỉ:</b> ${bike.address}</p>

                            </div>

                            <div class="bike-description">

                                <h3>Mô tả</h3>

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
                                    Thuê ngay
                                </button>

                                <div id="rentFormContainer" style="display:none; margin-top:20px;">
                                    <form action="${pageContext.request.contextPath}/user/CreateRental" 
                                          method="post" 
                                          onsubmit="return confirmRental()">

                                        <input type="hidden" name="bikeId" value="${bike.bikeId}">
                                        <input type="hidden" id="totalAmountInput" name="totalAmount" value="0">

                                        <div style="margin-bottom:10px;">
                                            <label for="startDate"><b>Ngày thuê:</b></label><br>
                                            <input type="date" name="startDate" id="startDate" required onchange="calculateTotal()">
                                        </div>

                                        <div style="margin-bottom:10px;">
                                            <label for="endDate"><b>Ngày trả:</b></label><br>
                                            <input type="date" name="endDate" id="endDate" required onchange="calculateTotal()">
                                        </div>

                                        <div id="rentSummary" style="margin-top: 15px; padding: 10px; background: #f5f5f5; border-radius: 8px; display: none;">
                                            <p><b>Số ngày thuê:</b> <span id="totalDays">0</span> ngày</p>
                                            <p><b>Giá/ngày:</b> ${bike.pricePerDay} VND</p>
                                            <p style="color: green; font-size: 18px;">
                                                <b>Tổng tiền:</b> <span id="totalAmount">0</span> VND
                                            </p>
                                        </div>

                                        <button type="submit" class="rent-btn">Xác nhận thuê</button>
                                    </form>
                                </div>
                            </c:if>

                            <c:if test="${empty sessionScope.user}">
                                <a href="${pageContext.request.contextPath}/Login" class="rent-btn" style="display:inline-block; text-decoration:none; text-align:center;">
                                    Đăng nhập để thuê xe
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
               const pricePerDay = ${bike.pricePerDay};

               function toggleRentForm() {
                   const form = document.getElementById("rentFormContainer");
                   if (form.style.display === "none" || form.style.display === "") {
                       form.style.display = "block";
                       calculateTotal();
                   } else {
                       form.style.display = "none";
                   }
               }

               function formatDate(date) {
                   const year = date.getFullYear();
                   const month = String(date.getMonth() + 1).padStart(2, '0');
                   const day = String(date.getDate()).padStart(2, '0');
                   return year + "-" + month + "-" + day;
               }

               function calculateTotal() {
                   const startInput = document.getElementById("startDate");
                   const endInput = document.getElementById("endDate");
                   const summary = document.getElementById("rentSummary");
                   const totalDaysEl = document.getElementById("totalDays");
                   const totalAmountEl = document.getElementById("totalAmount");
                   const totalAmountInput = document.getElementById("totalAmountInput");

                   if (!startInput.value || !endInput.value) {
                       summary.style.display = "none";
                       return;
                   }

                   const startDate = new Date(startInput.value);
                   const endDate = new Date(endInput.value);

                   const diffTime = endDate - startDate;
                   const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;

                   if (diffDays <= 0) {
                       summary.style.display = "none";
                       return;
                   }

                   const total = diffDays * pricePerDay;

                   totalDaysEl.textContent = diffDays;
                   totalAmountEl.textContent = total.toLocaleString("vi-VN");
                   totalAmountInput.value = total;
                   summary.style.display = "block";
               }

               function confirmRental() {
                   const startInput = document.getElementById("startDate");
                   const endInput = document.getElementById("endDate");

                   if (!startInput.value || !endInput.value) {
                       alert("Vui lòng chọn ngày thuê và ngày trả.");
                       return false;
                   }

                   const startDate = new Date(startInput.value);
                   const endDate = new Date(endInput.value);

                   const diffTime = endDate - startDate;
                   const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;

                   if (diffDays <= 0) {
                       alert("Ngày trả phải lớn hơn hoặc bằng ngày thuê.");
                       return false;
                   }

                   const total = diffDays * pricePerDay;

                   return confirm(
                           "Xác nhận thuê xe?\n" +
                           "Số ngày thuê: " + diffDays + " ngày\n" +
                           "Tổng tiền: " + total.toLocaleString("vi-VN") + " VND"
                           );
               }

               window.addEventListener("DOMContentLoaded", function () {
                   const startInput = document.getElementById("startDate");
                   const endInput = document.getElementById("endDate");

                   if (!startInput || !endInput)
                       return;

                   const today = new Date();
                   const tomorrow = new Date();
                   tomorrow.setDate(today.getDate() + 1);

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

                       calculateTotal();
                   });

                   endInput.addEventListener("change", calculateTotal);

                   calculateTotal();
               });
            </script>
        </body>
    </html>