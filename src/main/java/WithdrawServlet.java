import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

        final String dburl = "jdbc:postgresql://localhost/BankingApplication";
        final String dbuser = "postgres";
        final String dbpassword = "Sujangarh@1";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
            
            // Check current balance
            String balanceQuery = "SELECT balance FROM user_balance WHERE accountnumber = ?";
            PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery);
            balanceStmt.setString(1, accountNumber);
            ResultSet rs = balanceStmt.executeQuery();
            double currentBalance = 0.0;

            if (rs.next()) {
                currentBalance = rs.getDouble("balance");
            }
            
            // Ensure sufficient funds
            if (currentBalance >= withdrawalAmount) {
                String sql = "UPDATE user_balance SET balance = (balance::numeric - ?) WHERE accountnumber = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, withdrawalAmount);
                stmt.setString(2, accountNumber);
                int rowsUpdated = stmt.executeUpdate();
                
                if (rowsUpdated > 0) {
                    request.setAttribute("message", "Withdrawal successful! New balance: $" + (currentBalance - withdrawalAmount));
                } else {
                    request.setAttribute("errorMessage", "Withdrawal failed. Please try again.");
                }
                stmt.close();
            } else {
                request.setAttribute("errorMessage", "Insufficient balance for this withdrawal.");
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
