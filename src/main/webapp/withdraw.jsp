<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw Money</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<h1>Withdraw Money</h1>
	<form action="WithdrawServlet" method="post">
    <label for="amount">Withdrawal Amount:</label>
    <input type="number" id="amount" name="withdrawalAmount" required> <!-- Ensure this name matches -->
    <input type="hidden" name="userID" value="<%= session.getAttribute("accountNumber") %>">
    <input type="submit" value="Withdraw">
</form>


	<a href="welcome.jsp" class = "logout">Back to Welcome</a>
</body>
</html>