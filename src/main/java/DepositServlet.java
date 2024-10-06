	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	
	@WebServlet("/DepositServlet")
	public class DepositServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String accountNumber = request.getParameter("userID");
	        double depositAmount;
	
	        
	        try {
	            depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "Invalid deposit amount! Please enter a numeric value.");
	            request.getRequestDispatcher("welcome.jsp").forward(request, response);
	            return;
	        }
	
	        
	        if (depositAmount <= 0) {
	            request.setAttribute("errorMessage", "Deposit amount must be positive!");
	            request.getRequestDispatcher("welcome.jsp").forward(request, response);
	            return;
	        }
	        
	        try {
	        	Connection conn = DBConnection.getConnnection();
	           
	            String sql = "UPDATE user_balance SET balance = (balance::numeric + ?) WHERE accountnumber = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setDouble(1, depositAmount);
	            stmt.setString(2, accountNumber);
	            int rs = stmt.executeUpdate();
	
	            if (rs > 0) {
	            	String transactionSql = "INSERT INTO transaction_history (accountnumber, transaction_type, amount, transaction_date) "
	                        + "VALUES (?, 'Deposit', ?, CURRENT_TIMESTAMP)";
	            	PreparedStatement transactionStmt = conn.prepareStatement(transactionSql);
	            	transactionStmt.setString(1, accountNumber);
	            	transactionStmt.setDouble(2, depositAmount);
	            	transactionStmt.executeUpdate();
	            	String balanceSql = "SELECT balance FROM user_balance WHERE accountnumber = ?";
	                PreparedStatement balanceStmt = conn.prepareStatement(balanceSql);
	                balanceStmt.setString(1, accountNumber);
	                ResultSet balanceRs = balanceStmt.executeQuery();
	            	
	                if (balanceRs.next()) {
	                    double newBalance = balanceRs.getDouble("balance");

	                    request.setAttribute("message", "Deposit successful! New balance: $" + newBalance);
	                } else {
	                    request.setAttribute("errorMessage", "Could not retrieve the updated balance.");
	                }

	                balanceStmt.close();
	            } else {
	                request.setAttribute("errorMessage", "Deposit failed. Try again.");
	            }
	            
	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Error: " + e.getMessage());
	        }
	        request.getRequestDispatcher("welcome.jsp").forward(request, response);
	    }
	}
