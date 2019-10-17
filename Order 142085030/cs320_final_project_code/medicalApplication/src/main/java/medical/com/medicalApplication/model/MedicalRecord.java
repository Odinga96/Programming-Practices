package medical.com.medicalApplication.model;
/**
 * 
 * 
 * This class represents a medical record model in the system
 *
 */
public class MedicalRecord {

	private Patient patient;
	private PatientHistory history;
	
	
	public MedicalRecord(Patient patient) {
		super();
		this.patient = patient;
		this.history = new PatientHistory();
	}

	public Patient getPatient() {
		return patient;
	}

	public PatientHistory getHistory() {
		return history;
	}
	
}
