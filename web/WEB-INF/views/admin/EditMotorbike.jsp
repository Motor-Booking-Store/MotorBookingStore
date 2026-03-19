<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Motorbike</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/editMotorbike.css">
    </head>
    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <h2>Edit Motorbike</h2>

        <!-- ERROR -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/EditMotorbike"
              method="post"
              enctype="multipart/form-data">

            <!-- ID + OLD IMAGE -->
            <input type="hidden" name="bikeId" value="${bike.bikeId}">
            <input type="hidden" name="oldImage" value="${bike.image}">

            <table>

                <tr>
                    <td>Bike Name:</td>
                    <td>
                        <input type="text" name="bikeName" value="${bike.bikeName}" required>
                    </td>
                </tr>

                <tr>
                    <td>Brand:</td>
                    <td>
                        <input type="text" name="brand" value="${bike.brand}" required>
                    </td>
                </tr>

                <tr>
                    <td>Model:</td>
                    <td>
                        <input type="text" name="model" value="${bike.model}">
                    </td>
                </tr>

                <tr>
                    <td>License Plate:</td>
                    <td>
                        <input type="text" name="licensePlate" value="${bike.licensePlate}" required>
                    </td>
                </tr>

                <tr>
                    <td>Price Per Day:</td>
                    <td>
                        <input type="number" step="0.01" name="pricePerDay" value="${bike.pricePerDay}" required>
                    </td>
                </tr>

                <tr>
                    <td>Location ID:</td>
                    <td>
                        <input type="number" name="locationId" value="${bike.locationId}" required>
                    </td>
                </tr>

                <tr>
                    <td>Description:</td>
                    <td>
                        <textarea name="description" rows="4">${bike.description}</textarea>
                    </td>
                </tr>

                <!-- IMAGE -->
                <tr>
                    <td>Current Image:</td>
                    <td>
                        <c:if test="${not empty bike.image}">
                            <img src="${pageContext.request.contextPath}${bike.image}" width="150">
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td>Upload New Image:</td>
                    <td>
                        <input type="file" name="imageUpload" accept="image/*">
                    </td>
                </tr>

                <!-- STATUS -->
                <tr>
                    <td>Status:</td>
                    <td>
                        <select name="status">
                            <option value="Available" ${bike.status eq 'Available' ? 'selected' : ''}>
                                Available
                            </option>
                            <option value="Rented" ${bike.status eq 'Rented' ? 'selected' : ''}>
                                Rented
                            </option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit">Update Motorbike</button>
                    </td>
                </tr>

            </table>

        </form>
    <jsp:include page="../component/footer.jsp"/>
    </body>
</html>