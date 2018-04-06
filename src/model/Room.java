package model;
 
import java.io.Serializable;
import java.util.*;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3947234501234725063L;
	
    private Integer id;
    private Integer number;
    public enum roomType {
    	SUITE, DOUBLE, INDIVIDUAL, BOX, UCI
    };
    private roomType type; 
    private Integer floor;
    private Integer capacity;
	private float costPerDay;
	
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

    public Room ()	{
	  number = null;
	  type= roomType.BOX;
	  floor = 0;
	  capacity = 2;
	  costPerDay = 5;
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
		return "Room [id=" + id + ", number=" + number + ", type=" + type + ", floor=" + floor + ", capacity="
				+ capacity + ", costPerDay=" + costPerDay + "]";
	}
	
	
	

	
	
}
