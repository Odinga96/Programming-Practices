import java.util.ArrayList;
import java.util.EnumMap;

/**
 * 
 */

/**
 * @author Glen
 *
 */

public class javaFit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	  // Load data into app
    Workouts workouts= FileAccess.loadWorkouts();
    EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>> muscleGroups = FileAccess.loadFormats();
	  
	  // Create Screen Handler
	  MainWindow mainScreen = new MainWindow(workouts, muscleGroups);
	  
	  


	}

}
