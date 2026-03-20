<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Motorbike Management</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/listMotorbike.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <h2>Motorbike Management</h2>

        <!-- Nút Add -->
        <a href="${pageContext.request.contextPath}/admin/AddMotorbike">
            <button>Add Motorbike</button>
        </a>

        <br><br>

        <table border="1" cellpadding="10">

            <tr>
                <th>ID</th>
                <th>Bike Name</th>
                <th>Image</th>
                <th>Brand</th>
                <th>Model</th>
                <th>License Plate</th>
                <th>Price/Day</th>
                <th>Location</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="m" items="${motorbikeList}">
                <tr>
                    <td>${m.bikeId}</td>
                    <td>${m.bikeName}</td>
                    <td>
                        <img src="${m.image}" width="100"/>
                    </td>
                    <td>${m.brand}</td>
                    <td>${m.model}</td>
                    <td>${m.licensePlate}</td>
                    <td>${m.pricePerDay}</td>
                    <td>${m.locationName}</td>
                    <td>${m.status}</td>

                    <td>
                        <!-- Edit -->
                        <a href="${pageContext.request.contextPath}/admin/EditMotorbike?id=${m.bikeId}">
                            <button>Edit</button>
                        </a>

                        <!-- Delete -->
                        <a href="${pageContext.request.contextPath}/admin/DeleteMotorbike?id=${m.bikeId}"
                           onclick="return confirm('Are you sure to delete this motorbike?');">
                            <button>Delete</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>