import java.util.ArrayList; // We choose ArrayList over Vector because...?


public class Workouts {

  public enum Muscle {ABS, BACK, BICEPS, CHEST, FOREARM, GLUTES, LOWERLEG, SHOULDER, TRICEPS, UPPERLEG, NONE} // Why didn't I have to declare this static?
  public enum Equipment {BARBELL, BODYWEIGHT, DUMBBELL, CABLE, HAMMERSTRENGTH}
  private final ArrayList<Workout> workoutList = new ArrayList<Workout>();

  private class Workout {
    private String name;
    private Equipment equipment;
    private Muscle primaryMuscle;
    private Muscle secondaryMuscle;
    private String desc;
    private String reminders;
  
    Workout(String name, Equipment equipment, Muscle primaryMuscle, Muscle secondaryMuscle, String desc, String reminders) {
      this.name = name;
      this.equipment = equipment;
      this.primaryMuscle = primaryMuscle;
      this.secondaryMuscle = secondaryMuscle;
      this.desc = desc;
      this.reminders = reminders;
    }
    
    protected boolean hasPrimaryMuscle(Muscle m) {
      return primaryMuscle == m;
    }
    protected boolean hasSecondaryMuscle(Muscle m) {
      return secondaryMuscle == m;
    }
    protected boolean hasEquipment(Equipment e) {
      return equipment == e;
    }
    protected boolean hasEquipment(ArrayList<Equipment> equipmentList) {
      for (Equipment e : equipmentList) {// This is a ForEach, and uses an iterator in the background to loop through the collection.
        if(hasEquipment(e)) return true;
      }
      return false;
    }
    
    public String getName() {
      return name;
    }
    public String getEquipment() { // How do we get the name of an enumeration value?
      return equipment.name();
    }
    public String getPrimaryMuscle() { // How do we get the name of an enumeration value?
      return primaryMuscle.name();
    }
    public String getSecondaryMuscle() { // How do we get the name of an enumeration value?
      return secondaryMuscle.name();
    }
    public String getDesc() {
      return desc;
    }
    public String getReminders() {
      return reminders;
    }
  }
  
  public final void addWorkout(String name, Equipment equipment, Muscle primaryMuscle, Muscle secondaryMuscle, String desc, String reminders)
  {
    Workout newWorkout = new Workout(name, equipment, primaryMuscle, secondaryMuscle, desc, reminders);
    workoutList.add(newWorkout);
  }
  
  public final void addWorkout(Workout workout)
  {
    workoutList.add(workout);
  }
  
  public final Workouts getWorkoutsByMuscle(Muscle m, boolean includeSecondary)
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
  
  public final Workouts getWorkoutsByEquipment(Equipment e)
  {
    Workouts retval = new Workouts();
    for(Workout w : workoutList) {
      if(w.hasEquipment(e)) {
        retval.addWorkout(w);
      }
    }
    return retval;
  }
  
  public final Workouts getWorkoutsByEquipment(ArrayList<Equipment> e)
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