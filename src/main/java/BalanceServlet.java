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

@WebServlet("/BalanceServlet")
	public class BalanceServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String accountNumber = request.getParameter("userID");
	
	        final String dburl = "jdbc:postgresql://localhost/BankingApplication";
	        final String dbuser = "postgres";
	        final String dbpassword = "Sujangarh@1";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);

            String sql = "SELECT balance FROM user_balance WHERE accountnumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                request.setAttribute("balance", balance);
                request.getRequestDispatcher("balance.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Account not found!");
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
    }
}
