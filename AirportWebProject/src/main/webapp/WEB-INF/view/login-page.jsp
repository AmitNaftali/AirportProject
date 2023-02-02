<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Form</title>

<style>
@import
    url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%2')
    ;

body {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'Copperplate', sans-serif;
}

form {
    background-color: rgba(255, 255, 255, 0.7);
    padding: 20px;
    border-radius: 10px;
    text-align: center;
}

form input[type="text"], form input[type="password"] {
    padding: 10px;
    border-radius: 10px;
    border: 1px solid black;
    margin-bottom: 20px;
    width: 100%;
}

form input[type="submit"] {
    padding: 10px 20px;
    border-radius: 50px;
    background-color: #c0d4ff;
    border: none;
    color: white;
    font-size: 18px;
}
</style>

</head>
<body>
    <form:form action="processLogin" modelAttribute="user">
    User Name: <form:input path="username" />
        <br>
        <br>
    Password Id: <form:input type="password" path="password" />
        <br>
        <br>

        <p style="color: red">${message}</p>

        <br>

        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>