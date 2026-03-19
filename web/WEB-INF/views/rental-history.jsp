<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>My Rental History</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/rental-history.css">
    </head>

    <body>
        <jsp:include page="./component/navbar.jsp"/>
        <div class="layout-wrappe">
            <div class="layout-main">
                <h2>My Rental History</h2>


                <div class="filter-bar">
                    <a href="?status=all&userId=${sessionScope.user.userID}">All</a>
                    <a href="?status=Pending&userId=${sessionScope.user.userID}">Pending</a>
                    <a href="?status=Approved&userId=${sessionScope.user.userID}">Approved</a>
                    <a href="?status=Completed&userId=${sessionScope.user.userID}">Completed</a>
                    <a href="?status=Cancelled&userId=${sessionScope.user.userID}">Cancelled</a>
                </div>

                <h3>${currentStatus} Rentals</h3>

                <table border="1" class="rental-table">

                    <tr>
                        <th>Rental ID</th>
                        <th>Motorbike</th>  
                        <th>image</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Total Amount</th>
                        <th>Address</th>
                        <th>Status</th>
                    </tr>

                    <c:forEach var="r" items="${pendingList}">
                        <tr>
                            <td>
                                ${r.rentalId}
                            </td>

                            <td>
                                ${r.bikeName}
                            </td>

                            <td>
                                <img src="${r.image}" alt="error"/>
                            </td>

                            <td>${r.startDate}</td>

                            <td>${r.endDate}</td>

                            <td>${r.totalAmount}</td>

                            <<td>${r.address}</td>
                            
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