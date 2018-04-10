package model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(generator="user")
	@TableGenerator(name="user",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="user")
	private Integer id;
	private String username;
	private String password;
	public enum userType{
		ADMIN,RECEPTIONIST,DOCTOR,NURSE
	};
    private userType typeOfUser;
    
    public User(String username, String password, userType typeOfUser) {
		super();
		this.username = username;
		this.password = password;
		this.typeOfUser = typeOfUser;
	}
    
	
    public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public userType getTypeOfUser() {
		return typeOfUser;
	}


	public void setTypeOfUser(userType typeOfUser) {
		this.typeOfUser = typeOfUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", typeOfUser=" + typeOfUser + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
    
    
}
