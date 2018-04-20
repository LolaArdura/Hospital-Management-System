package jdbcManager;
import java.sql.*;
import interfaces.*;
public class JDBConnection implements ConnectionInterface {
	private static Connection con;
	public static Connection getConnection() throws Exception {
		if (con==null) {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:./database/Hospital-Management-System.db");
		}
		return con;
	}
	
	@Override
	public void stopConnection() throws SQLException {
		con.close();
	}
}
