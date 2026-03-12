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

                    <td>${r.rentalId}</td>

                    <td>
                        ${r.firstName} ${r.lastName}
                    </td>

                    <td>${r.bikeName}</td>

                    <td>${r.startDate}</td>

                    <td>${r.endDate}</td>

                    <td>${r.totalAmount}</td>

                    <td>${r.status}</td>

                    <td>
                        <form action="update-rental-status" method="post">

                            <input type="hidden" name="id" value="${r.rentalId}">

                            <button type="submit" name="action" value="approve">
                                Approve
                            </button>

                            <button type="submit" name="action" value="cancel">
                                Cancel
                            </button>

                        </form>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </body>
</html>