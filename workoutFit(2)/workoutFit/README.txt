Phase 2 is due Wednesday the 24th at 11:59pm.

Coding:
Use Refactor -> Move to move the Muscle and the Equipment enumerations to the Config class, which is where they should have been from the beginning. 

Take what you learned writing the loadWorkouts() method and write a loadFormats() group. It should read a file defined in Config.WORKOUTFORMATFILE as a CSV file and return an enumeration hashmap.

Set Workout inner class to public and use the Refactor option to extract the workout class to a new file, so the class is now its own file. This reflects a design change in the future use of the software.

Add these methods to the Workout class. They should function just like the hasEquipment equivalent.
protected boolean hasPrimaryMuscle(ArrayList<Config.Muscle> muscleList) 
protected boolean hasSecondaryMuscle(ArrayList<Config.Muscle> muscleList).

Add these methods to the Workouts class. 
public final Workouts getWorkoutsByMuscle(ArrayList<Config.Muscle> m, boolean includeSecondary)
This should function just like the getWorkoutsByEquipment equivalent.
public final ArrayList<String> getEquipment()
This should function just like the getNames equivalent.
public final int size()
This should return the number of workouts in the class.
public final Workout getWorkout(int index)
This should return an instance of the workout class that matches the index in the workoutList.
public final String getWorkoutEquipment(int index)
This should return the equipment of the workout at the given index.
public final int findWorkoutByName(String name)
This searches through the list of workouts in the class and returns the index of the workout with that name.

You have a new javaFit.java file. It loads the two data files and creates a new window. This is the bulk of the assignment: Create a Swing class that creates and displays three full-size buttons in a 600x400 window and then add ActionListeners to them that call a function to display the appropriate workout groups.

The WorkoutsPanel.java class is provided for you.

You will want to make sure you get the new workouts.csv file from github so you have a larger selection of test data. 

What You Turn In:
1) The completed .java files.

Grading: 
Grading will be based upon completeness. If your code is fully functional and passes all tests while adhering to the design guidelines, you get full points for this section. If your code is partially functional, you will get partial credit. If your code does not compile or run, you will get minimal credit. If you turn nothing in, you will get no credit.

Academic Honesty: As with most programming project, student code will be subjected to extensive anti-cheating monitoring. Algorithm design will be checked, as well as code layout. 

