package model;


public abstract class Employee {
	private Integer id;
	private String name;
	private byte[] photo;
	private String schedule;
	
	public Employee(String name) {
		super();
		this.name = name;
	}
		
	public Employee(String name, String schedule) {
		super();
		this.name = name;
		this.schedule = schedule;
	}

	
	public Employee(String name, byte[] photo, String schedule) {
		super();
		this.name = name;
		this.photo = photo;
		this.schedule = schedule;
	}


	public Employee(Integer id, String name, byte[] photo, String schedule) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
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
			if(obj==null) return false;
			if(this==obj) return true;
			if(this.getClass()!= obj.getClass()) return false;
			Employee other= (Employee) obj;
			if(id==null) {
				if(other.getId()!=null) return false;
			}else if(!this.id.equals(other.getId())) return false;
			
		return true;
		}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", schedule=" + schedule
				+ "]";
	}
	
		
	
}
