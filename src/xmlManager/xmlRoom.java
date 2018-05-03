package xmlManager;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table (name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Room")
@XmlType(propOrder = {"number", "type", "floor", "capacity" ,"costPerDay"})
public class xmlRoom implements Serializable{
	
private static final long serialVersionUID = 3947234501234725063L;
	
	@Id
	@GeneratedValue(generator="room")
	@TableGenerator(name="room",table="sqlite_sequence",pkColumnName="name",valueColumnName="seq",
			pkColumnValue="room")
	
	

}
