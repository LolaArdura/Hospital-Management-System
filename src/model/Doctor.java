package model;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.annotations.IdValidation;
import org.eclipse.persistence.annotations.PrimaryKey;

@Entity 
@Table(name="doctor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "specialty"})

public class Doctor extends Employee{
	
    @Id
    @GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="doctor")
    @XmlAttribute
	private Integer id;
    @XmlElement
	private String specialty;
	@OneToMany(mappedBy="prescriber")
	//@XmlElement(name = "Treatment")
   // @XmlElementWrapper(name = "treatments")
	@XmlTransient
	private List<Treatment> treatments;
    
	public Doctor() {
		super();
	}
	
	public Doctor (Integer id, String name, byte[] photo, String schedule, String specialty) {
		super(id,name,photo,schedule);
		this.id=id;
		this.specialty=specialty;
	}
	
	public Doctor (Integer id, String name, String schedule, String specialty) {
		super(id,name,schedule);
		this.id=id;
		this.specialty=specialty;
	}
	
	public Doctor(String name, byte[] photo,String schedule, String specialty) {
		super(name,photo,schedule);
		this.specialty=specialty;
	}
	
	public Doctor (String name, String schedule, String specialty) {
		super(name,schedule);
		this.specialty=specialty;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		super.setId(id);
		this.id=id;
	}
	
	public void addTreatment(Treatment treatment) {
		treatments.add(treatment);
	}
	
	public void deleteTreatment(Treatment treatment) {
		treatments.remove(treatment);
	}
	
	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Doctor [id:" + id + "\n name:" +  name + "\n photo:" + photo + "\n schedule:" + schedule+"\n specialty:" + specialty + "]\n";
	}
	
	
	
}
