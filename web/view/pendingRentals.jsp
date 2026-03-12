<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Pending Rentals</title>
    </head>

    <body>

        <h2>Pending Rental Requests</h2>

        <table border="1">

            <tr>
                <th>Rental ID</th>
                <th>User ID</th>
                <th>Motorbike ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="r" items="${pendingList}">

                <tr>

                    <td>${r.rentalId}</td>
                    <td>${r.userID}</td>
                    <td>${r.startDate}</td>
                    <td>${r.endDate}</td>
                    <td>${r.totalAmount}</td>
                    <td>${r.status}</td>

                    <td>
                        <a href="ApproveRentalController?id=${r.rentalId}">
                            Approve
                        </a>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </body>
</html>