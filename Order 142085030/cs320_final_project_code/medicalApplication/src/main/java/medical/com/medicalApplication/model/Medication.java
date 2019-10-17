package medical.com.medicalApplication.model;

/**
 * 
 * This class represents the mediation model in the system
 *
 */
public class Medication {
	private String name;
	private String startDate;
	private String endDate;
	private String dose;

	public Medication(String name, String startDate, String endDate, String dose) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dose = dose;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	@Override
	public String toString() {
		return "Medication:"+name + " Start Date: " + startDate + " End Date: "+endDate+ " Dose: "+dose;
	}

}
