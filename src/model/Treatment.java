package model;

import java.io.Serializable;
import java.sql.Date;

public class Treatment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1419910528213957445L;

	private Integer id;
	private String routeOfAdmin;
	private Date startDate;
	private Date endDate;
	private float cost;
	private String treatmentType;
	private String dose;
	
	//Constructor
	
	public Treatment (Integer id, String routeOfAdmin, Date startDate, Date endDate, float cost ,
			String treatmentType, String dose) {
		this.id = id;
		this.routeOfAdmin= routeOfAdmin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.treatmentType = treatmentType;
		this.dose = dose;
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result + ((dose == null) ? 0 : dose.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((routeOfAdmin == null) ? 0 : routeOfAdmin.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((treatmentType == null) ? 0 : treatmentType.hashCode());
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
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (dose == null) {
			if (other.dose != null)
				return false;
		} else if (!dose.equals(other.dose))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (routeOfAdmin == null) {
			if (other.routeOfAdmin != null)
				return false;
		} else if (!routeOfAdmin.equals(other.routeOfAdmin))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (treatmentType == null) {
			if (other.treatmentType != null)
				return false;
		} else if (!treatmentType.equals(other.treatmentType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Treatment [id=" + id + ", routeOfAdmin=" + routeOfAdmin + ", startDate=" + startDate + ", endDate="
				+ endDate + ", cost=" + cost + ", treatmentType=" + treatmentType + ", dose=" + dose + "]";
	}
	
	
	
}
