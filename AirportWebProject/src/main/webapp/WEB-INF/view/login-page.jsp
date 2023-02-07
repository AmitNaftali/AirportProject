<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Form</title>

<style>
@import
    url('https://fonts.googleapis.com/css2?family=Copperplate&display=swap%2%27%27')
    ;

body {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: 'Copperplate', sans-serif;
    font-size: 18px;
    line-height: 1.5;
    background-image:
        url("${pageContext.request.contextPath}/resources/imgs/backroundAirport.jpg");
    background-size: 100%;
    background-repeat: no-repeat;
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
    width: 40%;
    padding: 10px;
    border-radius: 5px;
    background-color: #c0d4ff;
    color: white;
    border: none;
    cursor: pointer;
    margin-top: 10px;
}

.message {
    color: red;
    text-align: center;
    margin-top: 20px;
}

.container {
    background-image:
        url(${pageContext.request.contextPath}/resources/imgs/backroundAirport.jpg);
    background-size : cover; background-position : center;
    height: 100%;
    background-size: cover;
    background-position: center;
}
</style>

</head>
<body>



    <form:form action="processLogin" modelAttribute="user">
        <label>User Name:</label>
        <form:input path="username" />

        <label>Password Id:</label>
        <form:input type="password" path="password" />

        <p class="message">${message}</p>

        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>