package xmlManager;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import model.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PatientsList")

public class PatientsList {
	@XmlElement (name = "Patient")
	@XmlElementWrapper (name = "Patients")
	private List<Patient> patients;

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public PatientsList(List<Patient> patients) {
		super();
		this.patients = patients;
	}
	
}
