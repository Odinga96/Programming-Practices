package medical.com.medicalApplication.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import medical.com.medicalApplication.model.MedicalRecord;
import medical.com.medicalApplication.model.Patient;
/**
 * 
 * This class uses a singleton pattern to mock a service instead of using dependency injection
 * 
 * In addition, it stores data in memory only using Lists
 *
 */
public class MedicalRescordService {
	private static MedicalRescordService reference = new MedicalRescordService();
	private List<Patient> patients;
	private List<MedicalRecord> medicalRecords;

	public static MedicalRescordService getReference() {
		return reference;
	}

	MedicalRescordService() {
		this.patients = new ArrayList<Patient>();
		this.medicalRecords = new ArrayList<MedicalRecord>();
	}

	public boolean addPatient(String name, String id) {
		boolean patientAdded = !patients.stream().anyMatch(patient -> patient.getId().equals(id));

		if (patientAdded) {
			Patient newPatient = new Patient(name, id);
			patients.add(newPatient);
			medicalRecords.add(new MedicalRecord(newPatient));
		}
		return patientAdded;
	}
	
	public MedicalRecord getMedicalRecord(String patientId) {
		return medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getPatient().getId().equals(patientId)).findFirst().get();
	}

	public Patient getPatient(String patientId) {
		return patients.stream().filter(person -> person.getId().equals(patientId))
				.findFirst().get();
	}

	public List<Patient> getAllPatients() {
		return patients;
	}
	
	public List<Patient> getPatientsWithAllergies(String allergyName){
		for(Patient patient : getAllPatients()){
			if(getMedicalRecord(patient.getId()).getHistory().getAlergies().stream().filter(allergy -> allergy.getName().equals(allergyName)).findFirst().get() != null){
				return Collections.singletonList(patient);
			}
		}
		return Collections.emptyList();
	}
}
