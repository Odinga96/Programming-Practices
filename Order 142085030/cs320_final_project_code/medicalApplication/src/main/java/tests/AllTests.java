package tests;

import medical.com.medicalApplication.model.Allergey;
import medical.com.medicalApplication.model.MedicalRecord;
import medical.com.medicalApplication.model.Medication;
import medical.com.medicalApplication.model.Treatment;
import medical.com.medicalApplication.services.DoctorService;
import medical.com.medicalApplication.services.MedicalRescordService;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AllTests {

    @Test
    public void addDoctors(){
        DoctorService.getReference().addDoctor("Musa Dembele","E098");
        DoctorService.getReference().addDoctor("Davis John","E097");
        DoctorService.getReference().addDoctor("Callum Churchil","E096");


    }

    @Test
    public void addDublicateDoctor(){
        assertTrue(DoctorService.getReference().addDoctor("Mike Phelan","1234"));
        assertTrue(DoctorService.getReference().addDoctor("Warrior B","1235"));
        assertTrue(DoctorService.getReference().addDoctor("George Onyango","1236"));

        assertFalse(DoctorService.getReference().addDoctor("John Ferguson","1234"));

    }

    @After
    public void clearLists(){
        DoctorService.getReference().getAllDoctors().clear();
        MedicalRescordService.getReference().getAllPatients().clear();
    }

    @Test
    public void addDPatients(){
        assertTrue(MedicalRescordService.getReference().addPatient("Origi","E1234a"));
        assertTrue(MedicalRescordService.getReference().addPatient("Victor","E1234b"));
        assertTrue(MedicalRescordService.getReference().addPatient("Jacob","E1234c"));
        assertTrue(MedicalRescordService.getReference().addPatient("Murimi","E1234d"));

        assertEquals(4,MedicalRescordService.getReference().getAllPatients().size());


    }

    @Test
    public void addDublicatePatient(){

        assertTrue(MedicalRescordService.getReference().addPatient("Origi","12345"));
        assertTrue(MedicalRescordService.getReference().addPatient("Victor","12346"));
        assertTrue(MedicalRescordService.getReference().addPatient("Jacob","12347"));
        assertTrue(MedicalRescordService.getReference().addPatient("Murimi","12348"));


        assertFalse(MedicalRescordService.getReference().addPatient("Origi","12345"));
        assertFalse(MedicalRescordService.getReference().addPatient("Victor","12346"));
        assertFalse(MedicalRescordService.getReference().addPatient("Jacob","12347"));
        assertFalse(MedicalRescordService.getReference().addPatient("Murimi","12348"));

    }


    @Test
    public void addTreatment(){
        MedicalRescordService.getReference().addPatient("Origi","12345");

        Treatment treatment = new Treatment("12/05/19", "Sick", "Fever");
        MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord("12345");

        medicalRecord.getHistory().addTreatment(treatment);
        medicalRecord.getHistory().addTreatment(treatment);
    }


    @Test
    public void addAllergy(){

        String allergyName="Itching";
        String patientId="12345";

       MedicalRescordService.getReference().addPatient("Origi",patientId);


        Allergey allergy = new Allergey(allergyName);
        MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord(patientId);

        medicalRecord.getHistory().addAllergy(allergy);


    }


    @Test
    public void addMedication(){

        String allergyName="Itching";
        String patientId="12345";

        Medication medication = new Medication("name1", "03/09/2017", "03/09/2017", "3 moringa tablets");
        MedicalRecord medicalRecord = MedicalRescordService.getReference().getMedicalRecord(patientId);

        medicalRecord.getHistory().addMedication(medication);
    }

}
