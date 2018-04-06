package model;

public class Doctor extends Employee{
 
	private String speciality;
	
	public Doctor (Integer id, String name, byte[] photo, String schedule, String speciality) {
		this.id=id;
		this.name=name;
		this.photo=photo;
		this.schedule=schedule;
		this.speciality=speciality;
	}
	public Doctor (Integer id, String name, String schedule, String speciality) {
		this.id=id;
		this.name=name;
	
		this.schedule=schedule;
		this.speciality=speciality;
	}
	
	public Doctor(String name, byte[] photo,String schedule, String speciality) {
		this.name=name;
		this.schedule=schedule;
		this.photo=photo;
		this.speciality=speciality;
	}
	
	public Doctor (String name, String schedule, String speciality) {
		this.name=name;
		this.schedule=schedule;
		this.speciality=speciality;
	}
	
	public Doctor (String speciality) {
		this.speciality= speciality;
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
		return "Doctor [id:" + id + "\n name:" +  name + "\n photo=" + photo + "\n schedule:" + schedule+"\n speciality:" + speciality + "]";
	}
	
	
	
}
