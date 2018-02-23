package model;

import java.util.*;

public class Nurse extends Employee{
 
	private Integer id;
	private String role;
	private List <Patient> nurses;
	
	public Nurse ( String role ) {
		
		this.role= role;
		this.nurses = new LinkedList <Patient>() ;
	}
	public Nurse () {
		
		this.nurses = new LinkedList <Patient>() ;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurse other = (Nurse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Nurse [id=" + id + ", role=" + role + ", nurses=" + nurses + "]";
	}

	
	
	
	
}
