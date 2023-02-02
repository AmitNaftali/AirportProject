<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Flights</title>
</head>
<body>
	<form:form action="actions">
		<table style="width: 100%">
			<tr>
				<th>flight</th>
				<th>departureTime</th>
				<th>landingTime</th>
				<th>choise</th>
			</tr>
			<c:forEach items="${flightDestenations}" var="destenation">
				<tr>
					<td>${destenation.id}</td>
					<td>${destenation.departureTime}</td>
					<td>${destenation.landingTime}</td>
					<td>${destenation.destination}</td>
					<td><input type="radio" name="flight" value= "${destenation}"/></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Select" />
	</form:form>
</body>
</html>