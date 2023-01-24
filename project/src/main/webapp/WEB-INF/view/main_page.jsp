<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<title>Flights</title>
</head>
<body>

<c:forEach items="${allFestivals}" var="festival">
    <tr>
        <td>${festival.festivalName}</td>
        <td>${festival.location}</td>
        <td>${festival.startDate}</td>
        <td>${festival.endDate}</td>
        <td>${festival.URL}</td>
    </tr>
</c:forEach>
</body>
</html>