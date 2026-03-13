<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Pending Rentals</title>
    </head>

    <body>
        <!--<h3>Filter Rentals</h3>-->

        <a href="${pageContext.request.contextPath}/admin/pending-rentals?status=all">All</a> |
        <a href="${pageContext.request.contextPath}/admin/pending-rentals?status=Pending">Pending</a> |
        <a href="${pageContext.request.contextPath}/admin/pending-rentals?status=Approved">Approved</a> |
        <a href="${pageContext.request.contextPath}/admin/pending-rentals?status=Completed">Completed</a> |
        <a href="${pageContext.request.contextPath}/admin/pending-rentals?status=Cancelled">Cancelled</a>

        <br><br>
        <h2>${currentStatus} Rental Requests</h2>

        <table border="1">

            <tr>
                <th>Rental ID</th>
                <th>Customer</th>
                <th>Motorbike</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Total Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="r" items="${pendingList}">

                <tr>

                    <td><a href="rental-detail?id=${r.rentalId}">
                            ${r.rentalId}
                        </a></td>

                    <td>
                        <a href="user-profile?id=${r.userId}">
                            ${r.firstName} ${r.lastName}
                        </a>
                    </td>

                    <td> <a href="bike-detail?id=${r.bikeId}">
                            ${r.bikeName}
                        </a></td>

                    <td>${r.startDate}</td>

                    <td>${r.endDate}</td>

                    <td>${r.totalAmount}</td>

                    <td>${r.status}</td>

                    <td>
                        <c:choose>

                            <c:when test="${r.status == 'Pending'}">

                                <form action="update-rental-status" method="post">

                                    <input type="hidden" name="id" value="${r.rentalId}">

                                    <button type="submit" name="action" value="approve">
                                        Approve
                                    </button>

                                    <button type="submit" name="action" value="cancel">
                                        Cancel
                                    </button>

                                </form>

                            </c:when>

                            <c:otherwise>
                                <span style="color:gray;">Processed</span>
                            </c:otherwise>

                        </c:choose>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </body>
</html>