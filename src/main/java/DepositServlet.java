import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));

        final String dburl = "jdbc:postgresql://localhost/BankingApplication";
        final String dbuser = "postgres";
        final String dbpassword = "Sujangarh@1";
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);

            String sql = "UPDATE user_balance SET balance = (balance::numeric + ?) WHERE accountnumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, depositAmount);
            stmt.setString(2, accountNumber);
            int rs = stmt.executeUpdate();
            
            if (rs> 0) {
                
                request.setAttribute("message", "Deposit successful!");
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Deposit failed. Try again.");
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
            stmt.close();
            conn.close();
    }catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Error: " + e.getMessage());
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    	}
    }
        
}