package medical.com.medicalApplication.model;
/**
 * This class represent the Allergy model in the application
 *
 */
public class Allergey {
	private String name;

	public Allergey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Allergy " + name;
	}	
	
}
