package jdbcManager;
import java.sql.*;

public class DBConnection {
	private static Connection con;
	public static Connection getConnection() throws Exception {
		if (con==null) {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:./database/Hospital-Management-System.db");
		}
		return con;
	}
}
