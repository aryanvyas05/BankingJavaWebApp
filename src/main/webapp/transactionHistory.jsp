<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<h1>Transaction History</h1>
	
	<%
		List<String[]> transactions = (List<String[]>) request.getAttribute("transactions");
		
		
		if(transactions != null && !transactions.isEmpty()){
	%>
	
	<table border= "1">
		<tr>
			<th>Transaction ID</th>
			<th>Type</th>
			<th>Amount</th>
			<th>Date</th>
		</tr>
		
		<%
			for(String[] transaction : transactions){
		
		%>
		<tr>
            <td><%= transaction[0] %></td>
            <td><%= transaction[1] %></td>
            <td>$<%= transaction[2] %></td>
            <td><%= transaction[3] %></td>
        </tr>
        <%
        	}
        %>
	</table>
    
    <%
        } else {
    %>
        <p>No transactions found for this account.</p>
	
	<%
		}
	%>
	
	<br>
    <a href="welcome.jsp" class = "logout">Back to Welcome</a>
</body>
</html>