package model;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sample.db.xml.utils.*;

import java.time.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="treatment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "treatment")
@XmlType(propOrder = {"type", "startDate", "endDate", "dose", "routeOfAdmin", "cost", "bill_id", "patient_id", "doctor_id"})
public class Treatment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1419910528213957445L;
	@Id
	@GeneratedValue(generator = "treatment")
	@TableGenerator (name ="treatment", table="sqlite_sequence", pkColumnName = "name", valueColumnName ="seq", pkColumnValue="treatment")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String routeOfAdmin;
	@XmlAttribute
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date startDate;
	@XmlAttribute
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date endDate;
	@XmlAttribute
	private float cost;
	@XmlAttribute
	private String treatmentType;
	@XmlAttribute
	private String dose;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="doctor_id")
	@XmlTransient
	private Doctor prescriber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id")
	@XmlTransient
	private Patient patient ;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bill_id")
	@XmlTransient
	private Bills bill;
	
	public Treatment() {
		super();
	}
	
	//Constructor without id
	
	public Treatment (String routeOfAdmin, Date startDate, Date endDate, float cost ,
			String treatmentType, String dose, Doctor prescriber, Patient patient) {
		this.routeOfAdmin= routeOfAdmin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.treatmentType = treatmentType;
		this.dose = dose;
		this.prescriber=prescriber;
		this.patient = patient;
		
	}
	//Constructor with id
	
	
	public Treatment (int Id, String routeOfAdmin, Date startDate, Date endDate, float cost ,
			String treatmentType, String dose, Doctor prescriber, Patient patient) {
		this.id = Id;
		this.routeOfAdmin= routeOfAdmin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.treatmentType = treatmentType;
		this.dose = dose;
		this.prescriber = prescriber;
		this.patient = patient;
	}
	
	public Treatment(String routeOfAdmin, Date startDate, Date endDate, float cost, String treatmentType, String dose,
			Doctor prescriber, Patient patient, Bills bill) {
		super();
		this.routeOfAdmin = routeOfAdmin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.treatmentType = treatmentType;
		this.dose = dose;
		this.prescriber = prescriber;
		this.patient = patient;
		this.bill = bill;
	}

	public Treatment (int Id, String routeOfAdmin, Date startDate, Date endDate, float cost ,
			String treatmentType, String dose, Doctor prescriber) {
		this.id = Id;
		this.routeOfAdmin= routeOfAdmin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.treatmentType = treatmentType;
		this.dose = dose;
		this.prescriber = prescriber;
	
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRouteOfAdmin() {
		return routeOfAdmin;
	}
	public void setRouteOfAdmin(String routeOfAdmin) {
		this.routeOfAdmin = routeOfAdmin;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public Doctor getPrescriber() {
		return prescriber;
	}

	public void setDoctorId(Doctor prescriber) {
		this.prescriber = prescriber;
	}
	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

	public Bills getBill() {
		return bill;
	}

	public void setBill(Bills bill) {
		this.bill = bill;
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
		Treatment other = (Treatment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", routeOfAdmin=" + routeOfAdmin + ", startDate=" + startDate + ", endDate="
				+ endDate + ", cost=" + cost + ", treatmentType=" + treatmentType + ", dose=" + dose + ", prescriber="
				+ prescriber + ", patient=" + patient + "]";
	}

	
	
	
	
}
