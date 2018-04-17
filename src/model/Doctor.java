package model;

import javax.persistence.*;

@Entity 
@Table(name="doctor")
public class Doctor extends Employee{
	
    @Id
    @GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="doctor")
	private Integer id;
	private String specialty;

	public Doctor (Integer id, String name, byte[] photo, String schedule, String specialty) {
		super(id,name,photo,schedule);
		this.specialty=specialty;
	}
	
	public Doctor (Integer id, String name, String schedule, String specialty) {
		super(id,name,schedule);
		this.specialty=specialty;
	}
	
	public Doctor(String name, byte[] photo,String schedule, String specialty) {
		super(name,photo,schedule);
		this.specialty=specialty;
	}
	
	public Doctor (String name, String schedule, String specialty) {
		super(name,schedule);
		this.specialty=specialty;
	}
	
	public String getSpeciality() {
		return specialty;
	}
	public void setSpeciality(String speciality) {
		this.specialty = speciality;
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
		return "Doctor [id:" + id + "\n name:" +  name + "\n photo:" + photo + "\n schedule:" + schedule+"\n speciality:" + specialty + "]";
	}
	
	
	
}
