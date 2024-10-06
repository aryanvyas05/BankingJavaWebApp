<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Balance</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Your Account Balance</h1>
    <%
        Double balance = (Double) request.getAttribute("balance");
        if (balance != null) {
    %>
        <h2>Your balance is: $<%= balance %></h2>
    <%
        } else {
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
    %>
        <h3 style="color:red;"><%= errorMessage %></h3>
    <%
            }
        }
    %>
    <a href="welcome.jsp" class = "logout">Go Back</a>
</body>
</html>
