package model;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3758399780780821912L;
 
	private Integer id;
	private String name;
	private String gender;
	private String medicalCondition;
	private String wayOfHosp;
	private Date dob;
	private LocalDate dateAdmission;
	private LinkedList <Treatment> treatments;
	private LinkedList <Nurse> nurses;
	private LinkedList <Bills> bills;
	
	//Constructors
	
   public Patient () {
	
	this.treatments = new LinkedList <Treatment> ();
	this.nurses= new LinkedList<Nurse>();
	this.bills = new LinkedList<Bills>();
   }
   
   public Patient (Integer id, String name, String gender, String medicalCondition, String wayOfHosp, Date dob,
		   LocalDate dateAdmission) {
	   
	   this.id = id;
	   this.name = name;
	   this.gender=gender;
	   this.medicalCondition= medicalCondition;
	   this.wayOfHosp = wayOfHosp;
	   this.dob= dob;
	   this.dateAdmission = dateAdmission;
	   this.treatments = new LinkedList <Treatment> ();
	   this.nurses= new LinkedList<Nurse>();
		this.bills = new LinkedList<Bills>();
}	
   
// Additional method to use LocalDate objects
	public void setLocalDateDob(LocalDate ldate) {
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public String getWayOfHosp() {
		return wayOfHosp;
	}
	public void setWayOfHosp(String wayOfHosp) {
		this.wayOfHosp = wayOfHosp;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public LocalDate getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(LocalDate dateAdmission) {
		this.dateAdmission = dateAdmission;
	}
	public LinkedList<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(LinkedList<Treatment> treatments) {
		this.treatments = treatments;
	}
	public LinkedList<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(LinkedList<Nurse> nurses) {
		this.nurses = nurses;
	}
	public LinkedList<Bills> getBills() {
		return bills;
	}
	public void setBills(LinkedList<Bills> bills) {
		this.bills = bills;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bills == null) ? 0 : bills.hashCode());
		result = prime * result + ((dateAdmission == null) ? 0 : dateAdmission.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medicalCondition == null) ? 0 : medicalCondition.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((treatments == null) ? 0 : treatments.hashCode());
		result = prime * result + ((wayOfHosp == null) ? 0 : wayOfHosp.hashCode());
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
		if (bills == null) {
			if (other.bills != null)
				return false;
		} else if (!bills.equals(other.bills))
			return false;
		if (dateAdmission == null) {
			if (other.dateAdmission != null)
				return false;
		} else if (!dateAdmission.equals(other.dateAdmission))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medicalCondition == null) {
			if (other.medicalCondition != null)
				return false;
		} else if (!medicalCondition.equals(other.medicalCondition))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (treatments == null) {
			if (other.treatments != null)
				return false;
		} else if (!treatments.equals(other.treatments))
			return false;
		if (wayOfHosp == null) {
			if (other.wayOfHosp != null)
				return false;
		} else if (!wayOfHosp.equals(other.wayOfHosp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", medicalCondition=" + medicalCondition
				+ ", wayOfHosp=" + wayOfHosp + ", dob=" + dob + ", dateAdmission=" + dateAdmission + ", treatments="
				+ treatments + ", bills=" + bills + "]";
	}
	
	
	
}
