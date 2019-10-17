package javafit.src;

// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;
import java.io.BufferedReader;        
import java.io.FileReader;    
import java.io.IOException;

public class FileAccess {
  
  public static Workouts loadWorkouts() {
    Workouts retval = new Workouts();
    
    try { 
      Scanner scanner = new Scanner(new File(Config.WORKOUTFILE));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] fields = line.split(",");
        // Check to make sure glen didn't bork the data file.
        if (fields.length != 6)
          System.out.println("Line has "+fields.length+" fields instead of 6. Check your commas.");
        String name = fields[0];
        Config.Equipment equipment = Config.Equipment.valueOf(fields[1]);
        Config.Muscle primaryMuscle = Config.Muscle.valueOf(fields[2]);
        Config.Muscle secondaryMuscle = Config.Muscle.valueOf(fields[3]);
        String desc = fields[4];
        String reminders = fields[5];
        retval.addWorkout(name, equipment, primaryMuscle, secondaryMuscle, desc, reminders);
      }
      scanner.close();
    }
    catch (FileNotFoundException e) 
    {
      System.out.println("Unable to find workouts file. Is it in the same directory as the executable?\nError:"+e.toString());
    }
    return retval;
  }

  public static EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>> loadFormats() {
	
    EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>> retval  = new EnumMap<Config.MuscleGroup, ArrayList<Config.Muscle>>(Config.MuscleGroup.class);
	  
 	// Code goes here.
	String csvFileToRead = Config.WORKOUTFORMATFILE;
	BufferedReader br = null;
	String line = "";
	String splitBy = ",";
	
		    
	try {
		br = new BufferedReader(new FileReader(csvFileToRead));    
		while ((line = br.readLine()) != null) {
			String[] workoutFormats = line.split(splitBy);
			
			Config.MuscleGroup muscleGroup = Config.MuscleGroup.valueOf(workoutFormats[0]);
			
			ArrayList<Config.Muscle> muscleList = new ArrayList<Config.Muscle>();
			
			for (int i = 1; i < workoutFormats.length; i++) {
				muscleList.add(Config.Muscle.valueOf(workoutFormats[i]));
			}
				  
			retval.put(muscleGroup, muscleList);
				  
	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    
	  return retval;
  }
}
