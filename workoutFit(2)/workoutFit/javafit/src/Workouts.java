package javafit.src;

import java.util.ArrayList; // We choose ArrayList over Vector because...?


public class Workouts {

  private final ArrayList<Workout> workoutList = new ArrayList<Workout>();
  
  //This should function just like the getWorkoutsByEquipment equivalent.
  public final Workouts getWorkoutsByMuscle(ArrayList<Config.Muscle> m, boolean includeSecondary) {
	  Workouts retval = new Workouts();
	    for(Workout w : workoutList) {
	      if(w.hasPrimaryMuscle(m)) {
	        retval.addWorkout(w);
	      }
	      else if (includeSecondary && w.hasSecondaryMuscle(m)) {
	        retval.addWorkout(w);
	      }
	    }
	    return retval;
  }
  
  //This should function just like the getNames equivalent.
  public final ArrayList<String> getEquipment() {
	  ArrayList<String> retval = new ArrayList<String>();
	    for(Workout w : workoutList) {
	      if (!retval.contains(w.getEquipment())) {
	    	  retval.add(w.getEquipment());
	      }
	    }
	    return retval;
  }
  
  //This should return the number of workouts in the class.
  public final int size() {
	  int size = 0;
	  for(Workout w : workoutList) {
	      size++;
	    }
	    return size;
  }
  
  //This should return an instance of the workout class that matches the index in the workoutList.
  public final Workout getWorkout(int index) {
	  return workoutList.get(index);
  }
  
  //This should return the equipment of the workout at the given index.
  public final String getWorkoutEquipment(int index) {
	  Workout w = workoutList.get(index);
	  return w.getEquipment();
  }
  
  //This searches through the list of workouts in the class and returns the index of the workout with that name.
  public final int findWorkoutByName(String name) { 
      if (workoutList == null) { 
          return -1; 
      } 
      
      int i = 0; 
 
      for(Workout w : workoutList) {
          if (w.getName() == name) { 
              return i; 
          } 
          else { 
              i = i + 1; 
          } 
      } 
      return -1; 
  }

  public final void addWorkout(String name, Config.Equipment equipment, Config.Muscle primaryMuscle, Config.Muscle secondaryMuscle, String desc, String reminders)
  {
    Workout newWorkout = new Workout(name, equipment, primaryMuscle, secondaryMuscle, desc, reminders);
    workoutList.add(newWorkout);
  }
  
  public final void addWorkout(Workout workout)
  {
    workoutList.add(workout);
  }
  
  public final Workouts getWorkoutsByMuscle(Config.Muscle m, boolean includeSecondary)
  {
    Workouts retval = new Workouts();
    for(Workout w : workoutList) {
      if(w.hasPrimaryMuscle(m)) {
        retval.addWorkout(w);
      }
      else if (includeSecondary && w.hasSecondaryMuscle(m)) { // Why did I put the includeSecondary first?
        retval.addWorkout(w);
      }
    }
    return retval;
  }
  
  public final Workouts getWorkoutsByEquipment(Config.Equipment e)
  {
    Workouts retval = new Workouts();
    for(Workout w : workoutList) {
      if(w.hasEquipment(e)) {
        retval.addWorkout(w);
      }
    }
    return retval;
  }
  
  public final Workouts getWorkoutsByEquipment(ArrayList<Config.Equipment> e)
  {
    Workouts retval = new Workouts();
    for(Workout w : workoutList) {
      if(w.hasEquipment(e)) {
        retval.addWorkout(w);
      }
    }
    return retval;
  }
  
  public final ArrayList<String> getNames()
  {
    ArrayList<String> retval = new ArrayList<String>();
    for(Workout w : workoutList) {
      retval.add(w.getName());
    }
    return retval;
  }
 
  public final ArrayList<String[]> getFullInformation()
  {
    ArrayList<String[]> retval = new ArrayList<String[]>();
    for(Workout w : workoutList) {
      String[] info = new String[6];
      info[0] = w.getName();
      info[1] = w.getEquipment();
      info[2] = w.getPrimaryMuscle();
      info[3] = w.getSecondaryMuscle();
      info[4] = w.getDesc();
      info[5] = w.getReminders();
      retval.add(info);
    }
    return retval; 
  }
}