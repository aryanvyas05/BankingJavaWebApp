<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Deposit Money</h1>
    <form action="DepositServlet" method="post">
    <label for="amount">Deposit Amount:</label>
    <input type="number" id="amount" name="depositAmount" required>
    <input type="hidden" name="userID" value="<%= session.getAttribute("accountNumber") %>">
    <input type="submit" value="Deposit">
</form>

    <a href="welcome.jsp" class = "logout">Back to Welcome</a>
</body>
</html>
