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

@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String accountNumber = request.getParameter("userID");
        String password = request.getParameter("userPassword");

        final String dburl = "jdbc:postgresql://localhost/BankingApplication";
        final String dbuser = "postgres";
        final String dbpassword = "Sujangarh@1";

        try { 
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);

           
            String sql = "SELECT name, accountnumber FROM user_info WHERE accountnumber=? AND user_password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNumber);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String accountnumber = rs.getString("accountnumber");
                request.getSession().setAttribute("userName", name);
                request.getSession().setAttribute("accountNumber", accountnumber);
                request.getRequestDispatcher("welcome.jsp").forward(request, response); 
            } else {
                request.setAttribute("errorMessage", "Invalid username or password!");
                request.getRequestDispatcher("input.jsp").forward(request, response);
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("input.jsp").forward(request, response);
        }
    }
}
