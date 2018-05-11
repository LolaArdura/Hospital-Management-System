package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserInterface {
	public List<User> getAllUsers() throws Exception;
	public void insertUser(User user) throws Exception;
	public List<User> searchUserByType(User.userType usertype) throws Exception;
	public User validateUser(User user) throws Exception;

}
