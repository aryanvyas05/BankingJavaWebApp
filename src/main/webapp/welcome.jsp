<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Welcome</title>
      <link rel="stylesheet" type="text/css" href="css/style.css">
   </head>
   <body>
      <h1>Login Successful</h1>
      <p style="color:red;">
         <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
      </p>
      <p style="color:green;">
         <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
      </p>
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
      </form>
      >
      <a href="input.jsp" class= "logout">Log Out</a>
   </body>
</html>