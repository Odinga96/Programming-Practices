package medical.com.medicalApplication.model;
/**
 * 
 * This class represents a treatment model in the system.
 *
 */
public class Treatment {
	private String treatmentDate;
	private String diagnose;
	private String description;

	public Treatment(String treatmentDate, String diagnose, String description) {
		super();
		this.treatmentDate = treatmentDate;
		this.diagnose = diagnose;
		this.description = description;
	}

	public String getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Treatment: "+ " Date: "+ treatmentDate+ " Diagnose: " + diagnose;
	}

}
