package model;
 
import java.io.Serializable;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3947234501234725063L;
	
    private Integer id;
    private Integer number;
    private String type;
    private Integer floor;
    private Integer capacity;
	private float costPerDay;
	
	//Constructor
	public Room (Integer id, Integer number, String type, Integer floor, Integer capacity, float costPerDay) {
		
		this.id = id;
		this.number = number;
		this.type = type;
		this.floor= floor;
		this.capacity= capacity;
		this.costPerDay= costPerDay;
		
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	
	//Function hashCode and equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result + Float.floatToIntBits(costPerDay);
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (capacity == null) {
			if (other.capacity != null)
				return false;
		} else if (!capacity.equals(other.capacity))
			return false;
		if (Float.floatToIntBits(costPerDay) != Float.floatToIntBits(other.costPerDay))
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", type=" + type + ", floor=" + floor + ", capacity="
				+ capacity + ", costPerDay=" + costPerDay + "]";
	}
	
	
}
