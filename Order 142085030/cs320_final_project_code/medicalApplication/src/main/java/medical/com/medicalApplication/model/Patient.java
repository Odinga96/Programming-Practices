package medical.com.medicalApplication.model;
/**
 * 
 * This class represents a patient model in the system
 *
 */
public class Patient {
	private String name;
	private String id;
	
	public Patient(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Patient Name: "+name+ " ID: "+id;
	}
	
}
