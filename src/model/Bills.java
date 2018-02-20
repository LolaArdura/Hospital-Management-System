package model;
import java.io.Serializable;


public class Bills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423194198676609863L;

	private Integer id;
	private float totalCost;
	private String bankID;
	private Boolean paid;
	
	//Constructors

	public Bills() {
		paid=false;
	}
	
		
	public Bills(float totalCost, String bankID, Boolean paid) {
		super();
		this.totalCost = totalCost;
		this.bankID = bankID;
		this.paid = paid;
	}

    
	public Bills(float totalCost, String bankID) {
		super();
		this.totalCost = totalCost;
		this.bankID = bankID;
		paid=false;
	}


	public Bills (Integer id , float totalCost , String bankID, boolean paid) {
		
		 this.id = id;
		 this.totalCost = totalCost;
		 this.bankID = bankID;
		 this.paid = paid;
		
	}
	 
	
	//Getters and Setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public String getBankID() {
		return bankID;
	}
	public void setBankID(String bankID) {
		this.bankID = bankID;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	//HashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	//Equals
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bills other = (Bills) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	

	
	@Override
	public String toString() {
		return "Bills [id=" + id + ", totalCost=" + totalCost + ", bankID=" + bankID + ", paid=" + paid + "]";
	}


	
	
}
