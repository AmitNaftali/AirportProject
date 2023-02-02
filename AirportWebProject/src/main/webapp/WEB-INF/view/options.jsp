<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>options</title>
</head>
<body>
    <form:form action="processActions">
     <label for="input1">Custom Text 1</label>
        <input type="submit" name="add" title="Add to flight" value="1" id="input1"/> 
        <input type="submit" value="Remove from flight" />
        <input type="submit" value="Show your flights" />
        <input type="submit" value="Show all flights" />
        <input type="submit" value="Log out"/>
    </form:form>
</body>
</html>