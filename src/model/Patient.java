package model;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3758399780780821912L;
 
	private Integer id;
	private String name;
	public enum sex{
		MEN, WOMEN
	};
	private sex gender;
	private String medicalCondition;
	private Date dob;
	private Date dateAdmission;
	private List <Treatment> treatments;
	private List <Nurse> nurses;
	private List <Bills> bills;
	
	//Constructors
	
   public Patient () {
	
	this.treatments = new LinkedList <Treatment> ();
	this.nurses = new LinkedList<Nurse>();
	this.bills = new LinkedList<Bills>();
   }
   
  public Patient (Integer id, String name, sex gender, String medicalCondition, Date dob,
		   Date dateAdmission) {
	   this.id = id;
	   this.name = name;
	   this.gender = gender;
	   this.medicalCondition = medicalCondition;
	   this.dob = dob;
	   this.dateAdmission = dateAdmission;
	   this.treatments = new LinkedList <Treatment> ();
	   this.nurses = new LinkedList<Nurse>();
	   this.bills = new LinkedList<Bills>();
}	
   
   
   public Patient(Integer id, String name, sex gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.treatments = new LinkedList <Treatment> ();
		this.nurses = new LinkedList<Nurse>();
		this.bills = new LinkedList<Bills>();
		
	}


public Patient (String name, sex gender, String medicalCondition, Date dob,
		   Date dateAdmission) {
	   
	   this.name = name;
	   this.gender = gender;
	   this.medicalCondition = medicalCondition;
	   this.dob = dob;
	   this.dateAdmission = dateAdmission;
	   this.treatments = new LinkedList <Treatment> ();
	   this.nurses = new LinkedList<Nurse>();
	   this.bills = new LinkedList<Bills>();
}	
   
// Additional method to use LocalDate objects
	public void setDateDob(LocalDate ldate) {
		this.dob = Date.valueOf(ldate);
	}
		
//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public sex getGender() {
		return gender;
	}
	public void setGender(sex gender) {
		this.gender = gender;
	}
	public String getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(Date dateAdmission) {
		this.dateAdmission = dateAdmission;
	}
	public List<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(LinkedList<Treatment> treatments) {
		this.treatments = treatments;
	}
	public List<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(LinkedList<Nurse> nurses) {
		this.nurses = nurses;
	}
	public List<Bills> getBills() {
		return bills;
	}
	public void setBills(LinkedList<Bills> bills) {
		this.bills = bills;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", medicalCondition=" + medicalCondition
				+ ", dob=" + dob + ", dateAdmission=" + dateAdmission + ", treatments="
				+ treatments + ", bills=" + bills + "]";
	}
	
	
	
}
