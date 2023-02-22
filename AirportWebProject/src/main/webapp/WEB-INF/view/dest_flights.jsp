<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Flights</title>

<style>
@import
    url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%27%27%27')
    ;

form {
    width: 600px;
    margin: 0 auto;
    text-align: center;
}

body {
    font-family: 'Copperplate', sans-serif;
    background-image:
        url("${pageContext.request.contextPath}/resources/imgs/backroundAirport.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
}

form input[type="text"], form input[type="password"] {
    padding: 10px;
    border-radius: 10px;
    border: 1px solid black;
    margin-bottom: 20px;
    width: 100%;
}

} /* sets font of everything under body tag*/
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #c0d4ff;
}

tr:hover {
    background-color: #f5d4ff;
}

input[type="submit"] {
    width: 23%;
    padding: 10px;
    border-radius: 5px;
    background-color: #c0d4ff;
    color: white;
    border: none;
    cursor: pointer;
    margin-right: 10px;
    margin-top: 10px;
}
input[type="submit"]:hover{
    background-color: #6e8bf0;
}
</style>


</head>
<body>
	<form:form action="proccessActions">
		<table style="width: 1500" align="center">
			<tr>
				<th>flight</th>
				<th>departureTime</th>
				<th>landingTime</th>
				<th>travelers</th>
				<th>choise</th>
			</tr>
			<c:forEach items="${flightDestenations}" var="destenation">
				<tr>
					<td>${destenation.id}</td>
					<td>${destenation.departureTime}</td>
					<td>${destenation.landingTime}</td>
					<td>
					<c:forEach items="${destenation.travelers}" var="traveler">
						<p>id: ${traveler.passportId}</p>
						<p>name: ${traveler.fullName}</p>
					</c:forEach>
					</td>
					<td><input type="radio" name="flight" value="${destenation.id}"/></td>
				</tr>
			</c:forEach>
		</table>
		<p>${flightNfound1}</p>
		<input type="submit" name="option" value="Add to flight" />
		<input type="submit" name="option" value="Remove from flight" />
		<input type="submit" name="option" value="Show your flights" />
		<input type="submit" name="option" value="Show all flights" />
		<input type="submit" name="option" value="Log out" />
	</form:form>
</body>
</html>