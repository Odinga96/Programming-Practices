package medical.com.medicalApplication.prompts;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import medical.com.medicalApplication.model.Allergey;
import medical.com.medicalApplication.model.MedicalRecord;
import medical.com.medicalApplication.model.Medication;
import medical.com.medicalApplication.model.Patient;
import medical.com.medicalApplication.model.Treatment;
import medical.com.medicalApplication.services.MedicalRescordService;
/**
 * 
 * This class creates the prompts for the medical application
 *
 */
public class MedicalRecordPrompt {
	private static List<String> prompt = Arrays.asList("","Medical Record Menu", "1 Add a Treatment", "2 Add a Medication",
			"3 Print Patient's Treatments", "4 Print Patient's Medications", "5 Add Allergy", "6 Print Allergies", "0 Main Menu");

	public void mainPrompt(Scanner scanner) {
		int input = -1;
		System.out.println("Enter Patient ID:");
		String patientId = scanner.next();
		Patient patient = MedicalRescordService.getReference().getPatient(patientId);
		
		if (patient != null) {
			while (input != 0) {
				System.out.println("Patient: " + patient.getName());
				prompt.stream().forEach(System.out::println);
				input = scanner.nextInt();

				switch (input) {
				case 1:
					addTreatment(scanner, patient.getId());
					break;
				case 2:
					addMedication(scanner, patient.getId());
					break;
				case 3:
					MedicalRescordService.getReference().getMedicalRecord(patientId).getHistory().getAllTreatments()
							.forEach(System.out::println);
					break;
				case 4:
					MedicalRescordService.getReference().getMedicalRecord(patientId).getHistory().getAllMedications()
							.forEach(System.out::println);
					break;
				case 5:
					addAllergy(scanner, patient.getId());
					break;
				case 6:
					MedicalRescordService.getReference().getMedicalRecord(patientId).getHistory().getAlergies()
							.forEach(System.out::println);
					break;
				case 0:
					break;
				default:
					break;
				}
			}
		} else {
			System.out.println("Patient with that ID could not be found");
		}
	}

	private void addAllergy(Scanner scanner, String patientId) {
		int input = -1;

		while (input != 0) {
			System.out.println("Enter Allergy:");
			String allergyName = scanner.next();

			Allergey allergy = new Allergey(allergyName);
			MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord(patientId);

			if (medicalRecord != null) {
				medicalRecord.getHistory().addAllergy(allergy);
			} else {
				System.err.println("Error! Medical Record is null");
			}

			System.out.println(
					"Would you like to add another Allergy?\n 1 for Yes\n 0 To return to the Medical Record Menu");
			input = scanner.nextInt();
		}
		
	}

	public void addTreatment(Scanner scanner, String patientId) {
		int input = -1;

		while (input != 0) {
			System.out.println("Enter the treatment date:");
			String treatmentDate = scanner.next();

			System.out.println("Enter diagnose:");
			String diagnose = scanner.next();

			System.out.println("Enter description:");
			String description = scanner.next();

			Treatment treatment = new Treatment(treatmentDate, diagnose, description);
			MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord(patientId);

			if (medicalRecord != null) {
				medicalRecord.getHistory().addTreatment(treatment);
			} else {
				System.err.println("Error! Medical Record is null");
			}

			System.out.println(
					"Would you like to add another Treatment?\n 1 for Yes\n 0 To return to the Medical Record Menu");
			input = scanner.nextInt();
		}
	}

	public List<Patient> findAllPatientsWithAllergy(Scanner scanner){
		System.out.println("Enter Allergy:");
		String allergy = scanner.next();
		return MedicalRescordService.getReference().getPatientsWithAllergies(allergy);
	}
	public void addMedication(Scanner scanner, String patientId) {
		int input = -1;

		while (input != 0) {
			System.out.println("Enter medication name:");
			String name = scanner.next();
			System.out.println("Enter startDate:");
			String startDate = scanner.next();
			System.out.println("Enter endDate:");
			String endDate = scanner.next();
			System.out.println("Enter dose:");
			String dose = scanner.next();

			Medication medication = new Medication(name, startDate, endDate, dose);
			MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord(patientId);

			if (medicalRecord != null) {
				medicalRecord.getHistory().addMedication(medication);
			} else {
				System.err.println("Error! Medical Record is null");
			}

			System.out.println(
					"Would you like to add another Medication?\n 1 for Yes\n 0 To return to the Medical Record Menu");
			input = scanner.nextInt();
		}
	}
}
