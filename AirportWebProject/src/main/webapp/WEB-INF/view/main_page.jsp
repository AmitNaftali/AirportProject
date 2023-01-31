<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<head>
<title>Flights</title>
</head>
<body>
	<form:form action="showFlightsToDestination" >
		<table style="width: 100%">
			<tr>
				<th>destenation</th>
				<th>choise</th>
			</tr>
			<c:forEach items="${destenations}" var="destenation">
				<tr>
					<td>${destenation}</td>
					<td><input type="radio" name="chosenFlight" value= "${destenation}"/></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Select" />
	</form:form>
</body>
</html>