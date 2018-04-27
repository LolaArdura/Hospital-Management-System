package jdbcManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import interfaces.UserInterface;
import model.User;
import model.User.userType;

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
			User user;
			if(usertype.toLowerCase().equals("admin")) {
				user=new User(id,username,password,User.userType.ADMIN);
			}
			else {
				if(usertype.toLowerCase().equals("receptionist")) {
					user=new User(id,username,password,User.userType.RECEPTIONIST);
				}else {
					if(usertype.toLowerCase().equals("doctor")) {
						user=new User(id,username,password,User.userType.DOCTOR);
					}else {
						user=new User(id,username,password,User.userType.NURSE);
					}
				}
			}
			users.add(user);
		}
		prep.close();
		return users;
	}

	@Override
	public void insertUser(User user) throws Exception {
		String sql="INSERT INTO user (username,password, type)  VALUES (?,?,?)";
		PreparedStatement prep= JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1,user.getUsername());
		prep.setString(2, user.getPassword());
		prep.setString(3,user.getTypeOfUser().name().toLowerCase());
		prep.executeUpdate();
		prep.close();
	}

	@Override
	public List<User> searchUserByType(userType usertype) throws Exception {
		String sql="SELECT id,username,password FROM user WHERE type = ?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, usertype.name().toLowerCase());
		ResultSet rs=prep.executeQuery();
		List<User> users=new LinkedList<User>();
		while(rs.next()) {
			Integer id= rs.getInt("id");
			String username=rs.getString("username");
			String password=rs.getString("password");;
			User user=new User(id,username,password,usertype);
			users.add(user);	
			}
		prep.close();
		return users;
	}

}