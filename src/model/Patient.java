package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sample.db.xml.utils.SQLDateAdapter; 

@Entity
@Table(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType (propOrder = {"name","gender","dob","dateAdmission","diagnose", "room_id"})
public class Patient implements Serializable {

	private static final long serialVersionUID = 3758399780780821912L;

	@Id
	@GeneratedValue(generator = "patient")
	@TableGenerator(name = "patient", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "patient")
	
	@XmlAttribute
	
	private Integer id;
	@XmlAttribute
	private String name;
    @XmlType(name="sex")
    @XmlEnum
	public enum sex {
		MALE, FEMALE
	};
    @XmlElement
	private sex gender;
	@XmlAttribute
	private String diagnose;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dateAdmission;
	
	@XmlElement(name="treatment")
	@XmlElementWrapper(name="listOfTreatments")
	@OneToMany(mappedBy = "patient")
	private List<Treatment> listOfTreatments;
	@XmlElement(name="Nurse")
	@XmlElementWrapper(name="listOfNurses")
	@ManyToMany(mappedBy = "ListOfPatients")
	private List<Nurse> listOfNurses;
	@XmlElement (name= "bill")
	@XmlElementWrapper (name="listOfBills")
	@OneToMany(mappedBy = "patient")
	private List<Bills> listOfBills;
	@XmlTransient
	private Room room;

	// Constructors

	public Patient() {

		this.listOfTreatments = new LinkedList<Treatment>();
		this.listOfNurses = new LinkedList<Nurse>();
		this.listOfBills = new LinkedList<Bills>();
	}

	public Patient(Integer id, String name, sex gender, String diagnose, Date dob, Date dateAdmission) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.diagnose = diagnose;
		this.dob = dob;
		this.dateAdmission = dateAdmission;
		this.listOfTreatments = new LinkedList<Treatment>();
		this.listOfNurses = new LinkedList<Nurse>();
		this.listOfBills = new LinkedList<Bills>();
	}

	public Patient(String name, sex gender, String diagnose, Date dob, Date dateAdmission, Room room) {
		super();
		this.name = name;
		this.gender = gender;
		this.diagnose = diagnose;
		this.dob = dob;
		this.dateAdmission = dateAdmission;
		this.room = room;
		this.listOfTreatments = new LinkedList<Treatment>();
		this.listOfNurses = new LinkedList<Nurse>();
		this.listOfBills = new LinkedList<Bills>();
	}

	public Patient(String name, sex gender, String diagnose, Date dob, Date dateAdmission) {

		this.name = name;
		this.gender = gender;
		this.diagnose = diagnose;
		this.dob = dob;
		this.dateAdmission = dateAdmission;
		this.listOfTreatments = new LinkedList<Treatment>();
		this.listOfNurses = new LinkedList<Nurse>();
		this.listOfBills = new LinkedList<Bills>();
	}

	public Patient(String name, sex gender, Date dob, Date dateAdmission) {

		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.dateAdmission = dateAdmission;
		this.listOfTreatments = new LinkedList<Treatment>();
		this.listOfNurses = new LinkedList<Nurse>();
		this.listOfBills = new LinkedList<Bills>();
	}

	public Patient(Integer id, String name, sex gender, Date dob, Date dateAdmission, Room room) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.dateAdmission = dateAdmission;
		this.room = room;

	}

	public Patient(Integer id, String name) {
		this.id = id;
		this.name = name;
		listOfTreatments = new LinkedList<Treatment>();
		listOfNurses = new LinkedList<Nurse>();
		listOfBills = new LinkedList<Bills>();
	}

	// Additional method to use LocalDate objects
	public void setDateDob(LocalDate ldate) {
		this.dob = Date.valueOf(ldate);
	}

	// Getters and Setters
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

	public void removeTreatment(Treatment treatment) {
		if (listOfTreatments.contains(treatment)) {
			listOfTreatments.remove(treatment);
		}
	}

	public void removeBill(Bills bill) {
		if (listOfBills.contains(bill)) {
			listOfBills.remove(bill);
		}
	}

	public void removeNurse(Nurse nurse) {
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
		return "Patient id:" + id + ", name:" + name + ", gender:" + gender + ", diagnose:" + diagnose + ", dob:" + dob
				+ ", dateAdmission:" + dateAdmission + ",Room: " + room;
	}

}
