package model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"username", "password", "typeOfUser"})
public class User {
	
	@Id
	@GeneratedValue(generator="user")
	@TableGenerator(name="user",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="user")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String username;
	@XmlAttribute
	private String password;
	@XmlElement
	private String type;
    
    public User() {
    	super();
    }
    
    
    public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public User(String username, String password, String type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}
    
    public User(Integer id, String username,String password, String type) {
    	this.id=id;
    	this.username=username;
    	this.password=password;
    	this.type=type;
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


	public String getTypeOfUser() {
		return type;
	}


	public void setTypeOfUser(String type) {
		this.type = type;
	}

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
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
		return "User [username=" + username + ", password=" + password + ", type=" + type + "]";
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
