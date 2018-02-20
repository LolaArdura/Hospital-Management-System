package jdbcManager;
import java.sql.*;

public class DatabaseManager {
	private String url;
	
	
	public void connect() throws Exception  {
		Class.forName("org.sqlite.JDBC");
	}
    
	public void createTables() {
		
	}
}
