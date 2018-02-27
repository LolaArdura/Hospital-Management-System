package jdbcManager;
import java.sql.*;

public class DBConnection {
	private static Connection con;
	public static Connection getConnection() throws SQLException {
		if (con==null) {
			con = DriverManager.getConnection("jdbc:sqlite:./db/Hospital-Management-System.db");
		}
		return con;
	}
}
