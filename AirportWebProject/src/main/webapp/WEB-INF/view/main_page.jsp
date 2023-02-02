<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
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
    font-family: 'Copperplate', sans-serif;} /* sets font of everything under body tag*/


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

  tr:hover {background-color: #f5d4ff;} /creates the blue hovering row in the table*/

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
<head>
<title>Flights</title>
</head>
<body>
    <form:form action="showFlightsToDestination">
        <table style="width: 1000" align="center">
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