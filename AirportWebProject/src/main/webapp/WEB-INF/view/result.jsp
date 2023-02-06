<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>result</title>
</head>
<body>
	<p>${exception}</p>
	<c:choose>
		<c:when test="${action=='1'}">
			<form:form action="showMainScreen">

				<p>you have registered to flight:</p>
				<table style="width: 1500" align="center">
					<tr>
						<th>flight</th>
						<th>departureTime</th>
						<th>landingTime</th>
						<th>destination</th>
						<th>travelers</th>
					</tr>
					<tr>
						<td>${addFlight.id}</td>
						<td>${addFlight.departureTime}</td>
						<td>${addFlight.landingTime}</td>
						<td>${addFlight.destination}</td>
						<td><c:forEach items="${addFlight.travelers}" var="traveler">
								<p>id: ${traveler.passportId}</p>
								<p>name: ${traveler.fullName}</p>
							</c:forEach></td>
					</tr>
				</table>
				<input type="submit" value="Ok" />
			</form:form>
		</c:when>
		<c:when test="${action=='2'}">
			<form:form action="showMainScreen">

				<p>you have been removed from flight:</p>
				<table style="width: 1500" align="center">
					<tr>
						<th>flight</th>
						<th>departureTime</th>
						<th>landingTime</th>
						<th>destination</th>
						<th>travelers</th>
					</tr>
					<tr>
						<td>${removeFlight.id}</td>
						<td>${removeFlight.departureTime}</td>
						<td>${removeFlight.landingTime}</td>
						<td>${removeFlight.destination}</td>
						<td><c:forEach items="${addFlight.travelers}" var="traveler">
								<p>id: ${traveler.passportId}</p>
								<p>name: ${traveler.fullName}</p>
							</c:forEach></td>
					</tr>
				</table>
				<input type="submit" value="Ok" />
			</form:form>
		</c:when>
		<c:when test="${action=='3'}">
			<form:form action="showMainScreen">

				<p>all of your flights:</p>
				<table style="width: 1500" align="center">
					<tr>
						<th>flight</th>
						<th>departureTime</th>
						<th>landingTime</th>
						<th>travelers</th>
					</tr>
					<c:forEach items="${travelerFlights}" var="destenation">
						<tr>
							<td>${destenation.id}</td>
							<td>${destenation.departureTime}</td>
							<td>${destenation.landingTime}</td>
							<td><c:forEach items="${destenation.travelers}"
									var="traveler">
									<p>id: ${traveler.passportId}</p>
									<p>name: ${traveler.fullName}</p>
								</c:forEach></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="Ok" />
			</form:form>

		</c:when>
		<c:when test="${action=='0'}">
		<form:form action="/AirportWebProject">
			<p>Hope to see you soon!</p>
			<input type="submit" value="Log out"/>
		</form:form>
		</c:when>
	</c:choose>
</body>
</html>