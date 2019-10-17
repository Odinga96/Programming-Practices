package medical.com.medicalApplication.services;

import java.util.ArrayList;
import java.util.List;

import medical.com.medicalApplication.model.Doctor;
/**
 * 
 * This class uses a singleton pattern to mock a service instead of using dependency injection
 * 
 * In addition, it stores data in memory only using Lists
 *
 */
public class DoctorService {
	private static DoctorService reference = new DoctorService();
	private static List<Doctor> doctors;
	
	 DoctorService(){
		doctors = new ArrayList<Doctor>();
	}
	 
	public static DoctorService getReference(){
		return reference;
	}
	
	public List<Doctor> getAllDoctors(){
		return doctors;
	}
	
	public boolean addDoctor(String name, String id){
		String tempId = new String(id);
		boolean createDoctor = !doctors.stream().anyMatch(doctor -> doctor.getId() == tempId);
		if (createDoctor) {
			doctors.add(new Doctor(name, id));
		}
		return createDoctor;
	}

}
