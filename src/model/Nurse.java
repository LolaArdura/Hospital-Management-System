package model;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="nurse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Nurse")
@XmlType(propOrder = { "role" })
public class Nurse extends Employee {
    @Id
    @GeneratedValue(generator="nurse")
	@TableGenerator(name="nurse",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="nurse")
    @XmlAttribute
	private Integer id;
    @XmlElement
	private String role;
	
	@ManyToMany
	@JoinTable(name="nurse_patient",
	joinColumns={@JoinColumn(name="nurse_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	@XmlTransient
	private List <Patient> listOfPatients;
	
	public Nurse (Integer id, String name, byte[] photo, String schedule, String role) {
	    super(id,name,photo,schedule);
	    this.id= id;
		this.role=role;	
		this.listOfPatients = new LinkedList <Patient>();
	}
	public Nurse (Integer id, String name,  String schedule, String role) {
	   super(id, name, schedule);
	    this.id= id;
		this.role=role;	
		this.listOfPatients = new LinkedList <Patient>();
	}
	
	
	public Nurse(String name, byte[] photo, String schedule, String role) {
		super(name, photo,schedule);
		this.schedule=schedule;
		this.role=role;
		this.listOfPatients= new LinkedList <Patient>();
	}
	
	public Nurse(String name, String schedule, String role) {
		super(name, schedule);
		this.role=role;
		this.listOfPatients=new LinkedList <Patient>();
	}

	
	public Nurse ( String role ) {
		this.role= role;
		this.listOfPatients = new LinkedList <Patient>() ;
	}
	public Nurse () {
		this.listOfPatients = new LinkedList <Patient>() ;
	}
	
	public Integer getId() {
		return this.id;
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
	
	public List<Patient> getListOfPatients() {
		return listOfPatients;
	}

	public void setListOfPatients(List<Patient> listOfPatients) {
		this.listOfPatients.addAll(listOfPatients);
	}
	
	public void addPatientToNurse(Patient patient) {
				this.listOfPatients.add(patient);
		}
		
		public void deletePatientFromNurse(Patient patient) {
			if (listOfPatients.contains(patient)) {
				this.listOfPatients.remove(patient);
			}
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
		return "Nurse [id:" + id + ", name:" + name + ", photo:"+ photo +", schedule:"+ schedule +", role:" + role + ", patients:" + listOfPatients + "]";
	}

	
}
	
	

