<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Motorbike</title>
</head>
<body>

<h2>Add Motorbike</h2>

<!-- Hiển thị lỗi -->
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/admin/AddMotorbike" method="post">

    <table>

        <tr>
            <td>Bike Name:</td>
            <td>
                <input type="text" name="bikeName" value="${bikeName}">
            </td>
        </tr>

        <tr>
            <td>Brand:</td>
            <td>
                <input type="text" name="brand" value="${brand}">
            </td>
        </tr>

        <tr>
            <td>Model:</td>
            <td>
                <input type="text" name="model" value="${model}">
            </td>
        </tr>

        <tr>
            <td>License Plate:</td>
            <td>
                <input type="text" name="licensePlate" value="${licensePlate}">
            </td>
        </tr>

        <tr>
            <td>Price Per Day:</td>
            <td>
                <input type="text" name="pricePerDay" value="${pricePerDay}">
            </td>
        </tr>

        <tr>
            <td>Location ID:</td>
            <td>
                <input type="text" name="locationId" value="${locationId}">
            </td>
        </tr>

        <tr>
            <td>Description:</td>
            <td>
                <textarea name="description">${description}</textarea>
            </td>
        </tr>

        <tr>
            <td>Image URL:</td>
            <td>
                <input type="text" name="image" value="${image}">
            </td>
        </tr>

        <tr>
            <td>Status:</td>
            <td>
                <select name="status">
                    <option value="Available" ${status == 'Available' ? 'selected' : ''}>Available</option>
                    <option value="Rented" ${status == 'Rented' ? 'selected' : ''}>Rented</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit">Add Motorbike</button>
            </td>
        </tr>

    </table>

</form>

</body>
</html>