

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String accountNumber = request.getParameter("userID");
		
		
		try {
			Connection conn = DBConnection.getConnnection();
			
			String sql = "SELECT * FROM transaction_history where accountnumber = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, accountNumber);
			ResultSet rs = stmt.executeQuery();	
			List <String[]> transactions = new ArrayList<>();
			
			while(rs.next()) {
				String[] transaction = new String[4];
				transaction[0] = rs.getString("transaction_id");
				transaction[1] = rs.getString("transaction_type");
				transaction[2] = rs.getString("amount");
				transaction[4] = rs.getString("transaction_date");
				
				transactions.add(transaction);
			}
			
			 rs.close();
	         stmt.close();
	         conn.close();	            
	            
	            request.setAttribute("transactions", transactions);
	            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
	            
	            
		}catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
	}
}
