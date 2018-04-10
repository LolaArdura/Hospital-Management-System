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
	private String diagnose;
	private Date dob;
	private Date dateAdmission;
	private List <Treatment> listOfTreatments;
	private List <Nurse> listOfNurses;
	private List <Bills> listOfBills;
	private Room room;
	
	//Constructors
	
   public Patient () {
	
	this.listOfTreatments = new LinkedList <Treatment> ();
	this.listOfNurses = new LinkedList<Nurse>();
	this.listOfBills = new LinkedList<Bills>();
   }
   
  public Patient (Integer id, String name, sex gender, String diagnose, Date dob,
		   Date dateAdmission) {
	   this.id = id;
	   this.name = name;
	   this.gender = gender;
	   this.diagnose = diagnose;
	   this.dob = dob;
	   this.dateAdmission = dateAdmission;
	   this.listOfTreatments = new LinkedList <Treatment> ();
	   this.listOfNurses = new LinkedList<Nurse>();
	   this.listOfBills = new LinkedList<Bills>();
}	
   
   
   


public Patient (String name, sex gender, String diagnose, Date dob,
		   Date dateAdmission) {
	   
	   this.name = name;
	   this.gender = gender;
	   this.diagnose = diagnose;
	   this.dob = dob;
	   this.dateAdmission = dateAdmission;
	   this.listOfTreatments = new LinkedList <Treatment> ();
	   this.listOfNurses = new LinkedList<Nurse>();
	   this.listOfBills = new LinkedList<Bills>();
}	

public Patient (String name, sex gender,  Date dob,
		   Date dateAdmission) {
	   
	   this.name = name;
	   this.gender = gender;
	   this.dob = dob;
	   this.dateAdmission = dateAdmission;
	   this.listOfTreatments = new LinkedList <Treatment> ();
	   this.listOfNurses = new LinkedList<Nurse>();
	   this.listOfBills = new LinkedList<Bills>();
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
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
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
	
	

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Treatment> getListOfTreatments() {
		return listOfTreatments;
	}

	public void setListOfTreatments(List<Treatment> listOfTreatments) {
		this.listOfTreatments.addAll(listOfTreatments);
	}

	public List<Nurse> getListOfNurses() {
		return listOfNurses;
	}

	public void setListOfNurses(List<Nurse> listOfNurses) {
		this.listOfNurses.addAll(listOfNurses);
	}

	public List<Bills> getListOfBills() {
		return listOfBills;
	}

	public void setListOfBills(List<Bills> listOfBills) {
		this.listOfBills.addAll(listOfBills);
	
	}

	public void removeTreatment (Treatment treatment) {
		if (listOfTreatments.contains(treatment)) {
			listOfTreatments.remove(treatment);
		}
	}
	
	public void removeBill (Bills bill) {
		if (listOfBills.contains(bill)) {
			listOfBills.remove(bill);
		}
	}
	
	public void removeNurse (Nurse nurse) {
		if (listOfNurses.contains(nurse)) {
			listOfNurses.remove(nurse);
		}
	}
	
	public void addNurse(Nurse nurse) {
		listOfNurses.add(nurse);
	}
	
	public void addTreatment(Treatment treatment) {
		listOfTreatments.add(treatment);
	}
	
	public void addBills(Bills bill) {
		listOfBills.add(bill);
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
		return "Patient [id:" + id + ", name:" + name + ", gender:" + gender + ", diagnose:" + diagnose
				+ ", dob:" + dob + ", dateAdmission:" + dateAdmission + ",Room: "+ room +" ]";
	}
	
	
	
}
