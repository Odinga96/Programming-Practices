package javafit.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Bonus points: Create an icon (or find a public domain icon. Keep in mind federal Copyright law and TAMU's plagiarism policy and add it to the home screen window.
public class MainWindow {
  
  private final JFrame mainFrame = new JFrame(Config.APPLICATIONNAME);
  private final JDialog selectWorkout = new JDialog(mainFrame, "Select Workout");
  private JComboBox<String> cboType, cboGoal;
  private JSpinner spnDuration;
  private final Workouts workouts;
  private final EnumMap<Config.MuscleGroup, ArrayList<Workouts.Muscle>> muscleGroups;

  MainWindow(Workouts workouts, EnumMap<Config.MuscleGroup, ArrayList<Workouts.Muscle>> muscleGroups) {
    // Code goes here.
  }
  
  private void launchHomeScreen() {
    // Code goes here.
 
  }
  
  // This is the method your actionlistener should call. It should create and display a WorkoutsPanel.
  private void showWorkouts(ArrayList<Config.Muscle> muscles) {
    
  // Code goes here.
  }
}
