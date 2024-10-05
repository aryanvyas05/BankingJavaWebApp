<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>Welcome to My Bank!</h1>
<h2>Login:</h2>
<form action="VerificationServlet" method="post">
    <label for="userID">User ID:</label>
    <input type="text" id="userID" name="userID" required>
    <br><br>
    <label for="userPassword">User Password:</label>
    <input type="password" id="userPassword" name="userPassword" required>
    <br><br>
    <input type="submit" value="Login">	
</form>
<p style="color:red;">
    <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
</p>
</body>
</html>
