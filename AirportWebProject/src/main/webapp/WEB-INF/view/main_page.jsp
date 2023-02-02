
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<style>
@import url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%27');

body {font-family: 'Copperplate', sans-serif;} /* sets font of everything under body tag/

table, th, td {border: 1px solid black;}

table{  border-collapse: collapse; table-layout:fixed;}  /collapses the inside borders in the table/

td {width:1px;white-space:nowrap;}

th { background-color: #c0d4ff; color: white;} /sets the color of the header row in the table/

tr:hover {background-color: #f5d4ff;} /creates the blue hovering row in the table*/


form input[type="submit"] {
    padding: 10px 20px;
    border-radius: 50px;
    background-color: lightblue;
    border: none;
    color: white;
    font-size: 18px;

        height: 8vh;
    display: flex;
    align-items: center;
    justify-content: center;

    font-family: 'Copperplate', sans-serif;
}
</style>
<head>
<title>Flights</title>
</head>
<body>
    <form:form action="showFlightsToDestination">
        <table style="width: 100%">
            <tr>
                <th>destenation</th>
                <th>choise</th>
            </tr>
            <c:forEach items="${destenations}" var="destenation">
                <tr>
                    <td>${destenation}</td>
                    <td><input type="radio" name="chosenFlight"
                        value="${destenation}" /></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Select" />
    </form:form>
</body>
</html>