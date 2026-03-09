<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Motorbike List</title>
    </head>
    <body>

        <h1>Motorbike List</h1>
        
        <c:forEach var="b" items="${motorbikeList}">

            <div style="border:1px solid black; width:300px; padding:10px; margin:10px">

                <img src="images/${b.image}" width="200"><br><br>

                Bike Name: ${b.bikeName} <br>
                Brand: ${b.brand} <br>
                Model: ${b.model} <br>
                License Plate: ${b.licensePlate} <br>
                Price Per Day: ${b.pricePerDay} <br>
                Status: ${b.status} <br>

            </div>

        </c:forEach>

    </body>
</html>