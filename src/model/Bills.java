package model;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table (name="bills")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="Bills")
@XmlType(propOrder = {"cost", "bankID","paid"})
public class Bills implements Serializable {
	
	private static final long serialVersionUID = -6423194198676609863L;
	
	@Id
	@GeneratedValue(generator="bills")
	@TableGenerator(name="bills", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="bills")
	@XmlAttribute
	private Integer id;
	@XmlElement
	private float cost;
	@XmlElement
	private String bankID;
	@XmlElement
	private Boolean paid;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id")
	@XmlTransient
	private Patient patient;
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "bill")
	@XmlTransient
	private Treatment treatment;
	
	//Constructors

	public Bills() {
		paid=false;
	}
	
	public Bills(int id,float totalCost, String bankID, Boolean paid ) {
		super ();
		this.id= id;
		this.cost = totalCost;
		this.bankID = bankID;
		this.paid = paid;
		
	}
	public Bills(float totalCost, String bankID, Boolean paid, Patient patient ) {
		super();
		this.cost = totalCost;
		this.bankID = bankID;
		this.paid = paid;
		this.patient = patient;
	}
    
	public Bills(String bankID, Boolean paid) {
		super();
		this.bankID = bankID;
		this.paid = paid;
	}

	public Bills(float totalCost, String bankID, Patient patient) {
		super();
		this.cost = totalCost;
		this.bankID = bankID;
		paid=false;
		this.patient=patient;
	}


	public Bills (Integer id , float totalCost , String bankID, boolean paid, Patient patient) {
		
		 this.id = id;
		 this.cost = totalCost;
		 this.bankID = bankID;
		 this.paid = paid;
		 this.patient= patient;	 
		
	}
	 
	
	//Getters and Setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getTotalCost() {
		return cost;
	}
	public void setTotalCost(float totalCost) {
		this.cost = totalCost;
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
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
		return "Bills [id=" + id + ", totalCost=" + cost + ", bankID=" + bankID + ", paid=" + paid + ", patient="
				+ patient + "]";
	}
	

	

	
}


	
	

