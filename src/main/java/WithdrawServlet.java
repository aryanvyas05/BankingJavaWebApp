import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("userID");
        double withdrawalAmount = Double.parseDouble(request.getParameter("withdrawalAmount"));
        
        if (withdrawalAmount <= 0) {
            request.setAttribute("errorMessage", "Withdrawal amount must be positive!");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
            return;
        }
        
        
        try {
        	Connection conn = DBConnection.getConnnection();
            
            
            String balanceQuery = "SELECT balance FROM user_balance WHERE accountnumber = ?";
            PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery);
            balanceStmt.setString(1, accountNumber);
            ResultSet rs = balanceStmt.executeQuery();
            double currentBalance = 0.0;

            if (rs.next()) {
                currentBalance = rs.getDouble("balance");
            }
            
            
            if (withdrawalAmount > currentBalance) {
                request.setAttribute("errorMessage", "Insufficient balance for this withdrawal.");
            } else {
                String sql = "UPDATE user_balance SET balance = (balance::numeric - ?) WHERE accountnumber = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, withdrawalAmount);
                stmt.setString(2, accountNumber);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                	String transactionSql = "INSERT INTO transaction_history (accountnumber, transaction_type, amount, transaction_date) "
                            + "VALUES (?, 'Withdrawal', ?, CURRENT_TIMESTAMP)";
                	PreparedStatement transactionStmt = conn.prepareStatement(transactionSql);
                	transactionStmt.setString(1, accountNumber);
                	transactionStmt.setDouble(2, withdrawalAmount);
                	transactionStmt.executeUpdate();
      
                    request.setAttribute("message", "Withdrawal successful! New balance: $" + (currentBalance - withdrawalAmount));
                    
                } else {
                    request.setAttribute("errorMessage", "Withdrawal failed. Please try again.");
                }
                stmt.close();
            }

            balanceStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
        }
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }
}