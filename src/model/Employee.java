package model;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {
	
	@Id
	@GeneratedValue(generator="employee")
	@TableGenerator(name = "employee",
	table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue="doctor")
	protected Integer id;
	protected String name;
	@Basic(fetch=FetchType.LAZY)
	@Lob
	protected byte[] photo;
	protected String schedule;
	
	public Employee() {
		super();
	}
	
	public Employee (Integer id, String name, byte[] photo, String schedule) {
		this.id=id;
		this.name=name;
		this.photo=photo;
		this.schedule=schedule;
	}
	
	
	public Employee(String name, byte[] photo, String schedule) {
		this.name = name;
		this.photo = photo;
		this.schedule = schedule;
	}


	public Employee(Integer id, String name, String schedule) {
		this.id = id;
		this.name = name;
		this.schedule = schedule;
	}
	

    
	public Employee(String name, String schedule) {
		this.name = name;
		this.schedule = schedule;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
  
	
	

	
	
	
}
