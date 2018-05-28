package jdbcManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import interfaces.UserInterface;
import model.User;

public class JDBCUserController implements UserInterface{
	private static JDBCUserController singleton;
	
	private JDBCUserController() {
		super();
	}
	
	public static JDBCUserController getUserController() {
		if(singleton==null) {
			singleton=new JDBCUserController();
		}
		return singleton;
	}
	

	@Override
	public List<User> getAllUsers() throws Exception {

		String sql= "SELECT * FROM user";
		PreparedStatement prep= JDBConnection.getConnection().prepareStatement(sql);

		ResultSet rs=prep.executeQuery();
		List<User> users=new LinkedList<User>();
		while(rs.next()) {
			Integer id= rs.getInt("id");
			String username=rs.getString("username");
			String password=rs.getString("password");
			String usertype=rs.getString("type");
			User user = new User(id, username, password, usertype);
			users.add(user);
		}
		rs.close();
		prep.close();
		return users;

	}

	@Override
	public void insertUser(User user) throws Exception {
		String sql="INSERT INTO user (username,password, type)  VALUES (?,?,?)";
		PreparedStatement prep= JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1,user.getUsername());
		prep.setString(2, user.getPassword());
		prep.setString(3,user.getTypeOfUser());
		prep.executeUpdate();
		prep.close();

	}

	@Override
	public List<User> searchUserByType(String usertype) throws Exception {
		String sql="SELECT id,username,password FROM user WHERE type = ?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, usertype);
		ResultSet rs=prep.executeQuery();
		List<User> users=new LinkedList<User>();
		while(rs.next()) {
			Integer id= rs.getInt("id");
			String username=rs.getString("username");
			String password=rs.getString("password");;
			User user=new User(id,username,password,usertype);
			users.add(user);	
			}
		rs.close();
		prep.close();
		return users;

	}

	@Override
	public User validateUser(User user) throws Exception {
		String sql="SELECT id,type FROM user WHERE username = ? and password = ?";
		PreparedStatement prep= JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, user.getUsername());
		prep.setString(2,user.getPassword());
		ResultSet rs= prep.executeQuery();
		if(rs.next()) {
			 user.setId(rs.getInt("id"));
			 user.setTypeOfUser(rs.getString("type"));
		}
		rs.close();
		prep.close();
		return user;

	}

	@Override
	public void deleteUser(User user) throws Exception {
		String sql = "DELETE FROM user WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setInt(1,  user.getId());
		prep.executeUpdate();
		prep.close();

	}

	@Override
	public void updateUser(User user) throws Exception {
		String sql = "UPDATE user SET username=?, password=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, user.getUsername());
		prep.setString(2, user.getPassword());
		prep.setInt(3, user.getId());

		prep.executeUpdate();
		prep.close();

	}
	
	public User searchUserById(Integer id) throws Exception{
		String sql="SELECT * FROM user WHERE id = ?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		
		prep.setInt(1, id);
		ResultSet rs=prep.executeQuery();
		User user = null;
		if(rs.next()) {
			int Id =rs.getInt("id");
			String username=rs.getString("username");
			String password=rs.getString("password");
			String type=rs.getString("type");
			user=new User(Id,username,password,type);
			}
		rs.close();
		prep.close();
		return user;

	}

}
