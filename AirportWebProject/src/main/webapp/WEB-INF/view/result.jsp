<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<style>
@import
    url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%27%27%27%27%27')
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

img{
margin-bottom: 10px;
}
.exception {
    color: red;
    text-align: center;
    margin-top: 20px;
}
input[type="submit"]:hover{
    background-color: #6e8bf0;
} 
</style>
<head>
<title>result</title>
</head>
<body>
	<p class="exception">${exception}</p>
	<c:choose>
		<c:when test="${action=='1'}">
			<form:form action="showMainScreen">
			
            	<c:choose>
					<c:when test="${destFlight=='Japan'}">
						<img src="${pageContext.request.contextPath}/resources/imgs/japan.jpg" alt="japan" width="500" height="300">
					</c:when>
					<c:when test="${destFlight=='New York'}">
						<img src="${pageContext.request.contextPath}/resources/imgs/usa.jpg" alt="usa" width="500" height="300">
					</c:when>
					<c:when test="${destFlight=='Paris'}">
						<img src="${pageContext.request.contextPath}/resources/imgs/paris.jpg" alt="paris" width="500" height="300">
					</c:when>
				</c:choose>
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
				<input type="submit" value="Log out" />
			</form:form>
		</c:when>
		<c:when test="${action=='5'}">
			<form:form action="showMainScreen">
				<input type="submit" value="Ok" />
			</form:form>
		</c:when>
	</c:choose>
</body>
</html>