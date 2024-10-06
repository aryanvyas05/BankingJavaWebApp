import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnnection() throws Exception{
		final String dburl = "jdbc:postgresql://localhost/BankingApplication";
		final String dbuser = "postgres";
		final String dbpassword = "Sujangarh@1";
		
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(dburl,dbuser, dbpassword);
		
	}
}
