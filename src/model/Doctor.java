package model;

import java.util.*;

public class Doctor extends Employee{
 
	private Integer id;
	private String speciality;
	private List <Treatment> doctor;
	
	public Doctor () {
		this.doctor = new LinkedList <Treatment>() ;
		
	}
	
	public Doctor (String speciality) {
		this.speciality= speciality;
		this.doctor = new LinkedList <Treatment>() ;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", speciality=" + speciality + "]";
	}
	
	
	
}
