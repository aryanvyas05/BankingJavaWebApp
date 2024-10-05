<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Login Successful</h1>
    <h2>Welcome, <%= session.getAttribute("userName") != null ? session.getAttribute("userName") : "Guest" %>!</h2>

    <form action="BalanceServlet" method="post"> 
        <input type="hidden" name="userID" value="<%= session.getAttribute("accountNumber") %>">
        <input type="submit" value="View Balance">
    </form>
    
    <form action = "deposit.jsp" method = "get">
    	<input type="submit" value="Deposit amount">
    </form>
    
    <form action = "withdraw.jsp" method = "get">
    	<input type="submit" value="Withdraw amount">
    </form>>
    <a href="input.jsp">Log Out</a>
</body>
</html>
