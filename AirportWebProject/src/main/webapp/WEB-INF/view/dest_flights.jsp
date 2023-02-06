<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Flights</title>

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%27%27')
	;

form {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

body {
	font-family: 'Copperplate', sans-serif;
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

/
creates the blue hovering row in the table* /

  input[type="submit"] {
	padding: 10px 20px;
	border-radius: 5px;
	background-color: #c0d4ff;
	color: white;
	border: none;
	cursor: pointer;
	margin-top: 20px;
}
</style>



</head>
<body>
	<form:form action="proccessActions">
		<input type="submit" name="option" value="Add to flight" />
		<input type="submit" name="option" value="Remove from flight" />
		<input type="submit" name="option" value="Show your flights" />
		<input type="submit" name="option" value="Show all flights" />
		<input type="submit" name="option" value="Log out" />
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
	</form:form>
</body>
</html>