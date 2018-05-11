package model;
 
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.util.*;


@Entity
@Table (name="room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Room")
@XmlType(propOrder = {"id", "number", "type", "floor", "capacity", "costPerDay" })

public class Room implements Serializable {

	
	private static final long serialVersionUID = 3947234501234725063L;
	
	@Id
	@GeneratedValue(generator="room")
	@TableGenerator(name="room",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="room")
	@XmlAttribute
    private Integer id;
	@XmlAttribute
    private Integer number;
    public enum roomType {
    	SUITE, DOUBLE, INDIVIDUAL, BOX, ICU
    };
	@XmlElement
    private roomType type; 
	@XmlElement
    private Integer floor;
	@XmlElement
    private Integer capacity;
	@XmlElement
	private float costPerDay;
	
	@OneToMany(mappedBy="room")
	@XmlTransient
   // @XmlElementWrapper(name = "listOfPatients")
	private List<Patient> listOfPatients;
	
	//Constructor
	public Room (Integer id, Integer number, roomType type, Integer floor, Integer capacity, float costPerDay) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.floor= floor;
		this.capacity= capacity;
		this.costPerDay= costPerDay;
		listOfPatients = new LinkedList <Patient>();
		
	}
	
    public Room ( Integer number, roomType type, Integer floor, Integer capacity, float costPerDay) {
		
		this.number = number;
		this.type = type;
		this.floor= floor;
		this.capacity= capacity;
		this.costPerDay= costPerDay;
		listOfPatients = new LinkedList <Patient>();
	}
    
    public Room(Integer number, roomType type, Integer floor, Integer capacity) {
		super();
		this.number = number;
		this.type = type;
		this.floor = floor;
		this.capacity = capacity;
	}

	public Room(Integer id, Integer number, roomType type, Integer floor, float costPerDay) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.floor = floor;
		this.costPerDay = costPerDay;
	}

	public Room(Room.roomType type, float cost) {
    	this.type=type;
    	this.costPerDay=cost;
    	listOfPatients= new LinkedList <Patient>();
    }

    public Room ()	{
	  super();
	  listOfPatients= new LinkedList <Patient>();
    }
	 
	//functions gets and sets
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public roomType getType() {
		return type;
	}

	public void setType(roomType type) {
		this.type = type;
	}

	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public float getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(float costPerDay) {
		this.costPerDay = costPerDay;
	}
	
	public List<Patient> getListOfPatients() {
		return listOfPatients;
	}

	public void setListOfPatients(List<Patient> listOfPatients) {
		this.listOfPatients.addAll(listOfPatients);
		
	}
	
	public void removePatient(Patient patient) {
		if(listOfPatients.contains(patient)){
			listOfPatients.remove(patient);
		}
	}
	public void addPatient(Patient patient) {
		listOfPatients.add(patient);
	}
	
	//Function hashCode and equals
	

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
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+ number + "["+type+"]";
	}
	
	
	

	
	
}
