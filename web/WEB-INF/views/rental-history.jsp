<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>My Rental History</title>
    </head>

    <body>
        <jsp:include page="./component/navbar.jsp"/>
        <div class="layout-wrappe">
            <div class="layout-top">
                
            </div>
            <div class="layout-main">
                <h2>My Rental History</h2>

                <!-- Status Filter -->

                <a href="${pageContext.request.contextPath}/user/rental-history?status=all">All</a> |
                <a href="${pageContext.request.contextPath}/user/rental-history?status=Pending">Pending</a> |
                <a href="${pageContext.request.contextPath}/user/rental-history?status=Approved">Approved</a> |
                <a href="${pageContext.request.contextPath}/user/rental-history?status=Completed">Completed</a> |
                <a href="${pageContext.request.contextPath}/user/rental-history?status=Cancelled">Cancelled</a>

                <br><br>

                <h3>${currentStatus} Rentals</h3>

                <table border="1">

                    <tr>
                        <th>Rental ID</th>
                        <th>Motorbike</th>  
                        <th>image</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                    </tr>

                    <c:forEach var="r" items="${pendingList}">

                        <tr>

                            <td>
                                <a href="bike-detail?id=${r.rentalId}">
                                    ${r.rentalId}
                                </a>
                            </td>

                            <td>
                                <a href="bike-detail?id=${r.bikeId}">
                                    ${r.bikeName}
                                </a>
                            </td>

                            <td>
                                <a href="bike-detail?id=${r.bikeId}">
                                    ${r.image}
                                </a>
                            </td>

                            <td>${r.startDate}</td>

                            <td>${r.endDate}</td>

                            <td>${r.totalAmount}</td>

                            <td>

                                <c:choose>

                                    <c:when test="${r.status == 'Pending'}">
                                        <span style="color:orange;">Pending</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Approved'}">
                                        <span style="color:blue;">Approved</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Completed'}">
                                        <span style="color:green;">Completed</span>
                                    </c:when>

                                    <c:when test="${r.status == 'Cancelled'}">
                                        <span style="color:red;">Cancelled</span>
                                    </c:when>

                                </c:choose>

                            </td>

                        </tr>

                    </c:forEach>

                </table>
                <c:if test="${empty pendingList}">
                    <p>No rental history found.</p>
                </c:if>
            </div>
        </div>
                <jsp:include page="./component/footer.jsp"/>
    </body>
</html>