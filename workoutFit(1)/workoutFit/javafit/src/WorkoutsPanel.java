import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.SwingConstants;

public class WorkoutsPanel extends JTabbedPane {

  private static final long serialVersionUID = 8172229761286610310L; // What is this thing?
  private final Workouts workouts;
  ArrayList<WorkoutPanel> PanelList = new ArrayList<WorkoutPanel>();


  private class WorkoutPanel extends JPanel {

    private static final long serialVersionUID = 7815971630539125935L; // What is serialization?
    private final Workouts workouts;
    private Workouts.Muscle muscle;
    private Workouts.Equipment equipment;
    private int workoutIndex;
    private int selectedName;
    private int selectedEquipment;
    JComboBox<String> cboNames;
    JComboBox<String> cboEquipment;
    JLabel lblPrimaryMuscle;
    JLabel lblSecondaryMuscle;
    JTextArea taDescription;
    JTextArea taNotes;
        
    WorkoutPanel(Workouts.Muscle muscle, ArrayList<Workouts.Muscle> muscles, Workouts workouts)
    {
      this.workouts = workouts.getWorkoutsByMuscle(muscle, false);
      this.muscle = muscle;
      workoutIndex = 0;
      this.equipment = Config.Equipment.valueOf(this.workouts.getWorkoutEquipment(workoutIndex)); // Grab the first equipment and make that our default.
      updatePanel();
    }

    private void updatePanel()
    {
      this.removeAll();

      // Layout window
      GridBagLayout gridBagLayout = new GridBagLayout();
      gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0};
      gridBagLayout.columnWeights = new double[]{1.0};
      setLayout(gridBagLayout);
              
      ArrayList<String> tempNames = this.workouts.getWorkoutsByEquipment(equipment).getNames();
      String[] names = new String[tempNames.size()];
      names = tempNames.toArray(names);
      cboNames = new JComboBox<String>(names);
      cboNames.setFont(new Font("Tahoma", Font.BOLD, 13));
      cboNames.setSelectedIndex(selectedName);
      GridBagConstraints gbc_cboNames = new GridBagConstraints();
      gbc_cboNames.ipady = 2;
      gbc_cboNames.ipadx = 5;
      gbc_cboNames.fill = GridBagConstraints.BOTH;
      gbc_cboNames.insets = new Insets(0, 5, 5, 0);
      gbc_cboNames.gridx = 0;
      gbc_cboNames.gridy = 0;
      add(cboNames, gbc_cboNames);
      cboNames.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          WorkoutPanel.this.workoutIndex = WorkoutPanel.this.workouts.findWorkoutByName(cboNames.getSelectedItem().toString());
          WorkoutPanel.this.selectedName = cboNames.getSelectedIndex();
          updatePanel();
        }
      });
  
      // Update the workout selected to whatever our top 
      if (workoutIndex == -1) workoutIndex = WorkoutPanel.this.workouts.findWorkoutByName(cboNames.getSelectedItem().toString());
      
      JPanel pnlEquipmentMuscles = new JPanel();
      GridBagConstraints gbc_pnlEquipmentMuscles = new GridBagConstraints();
      gbc_pnlEquipmentMuscles.fill = GridBagConstraints.BOTH;
      gbc_pnlEquipmentMuscles.insets = new Insets(0, 5, 5, 0);
      gbc_pnlEquipmentMuscles.gridx = 0;
      gbc_pnlEquipmentMuscles.gridy = 1;
      add(pnlEquipmentMuscles, gbc_pnlEquipmentMuscles);
      pnlEquipmentMuscles.setLayout(new GridLayout(0, 2, 0, 0));
      
      ArrayList<String> tempEquip = this.workouts.getWorkoutsByMuscle(muscle, false).getEquipment();
      String[] equipment = new String[tempEquip.size()];
      equipment = tempEquip.toArray(equipment);
      cboEquipment = new JComboBox<String>(equipment);
      cboEquipment.setSelectedIndex(selectedEquipment);
      pnlEquipmentMuscles.add(cboEquipment);
      cboEquipment.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          WorkoutPanel.this.equipment = Config.Equipment.valueOf(cboEquipment.getSelectedItem().toString());
          WorkoutPanel.this.selectedEquipment = cboEquipment.getSelectedIndex();
          WorkoutPanel.this.workoutIndex = -1; // Set to -1 so we know to pick a new random workout.
          updatePanel();
        }
      });
      
      JPanel pnlPrimarySecondary = new JPanel();
      pnlEquipmentMuscles.add(pnlPrimarySecondary);
      pnlPrimarySecondary.setLayout(new GridLayout(0, 2, 2, 0));
      
      lblPrimaryMuscle = new JLabel("Primary Muscle");
      lblPrimaryMuscle.setText("Primary: "+this.workouts.getWorkout(workoutIndex).getPrimaryMuscle());
      pnlPrimarySecondary.add(lblPrimaryMuscle);
      
      lblSecondaryMuscle = new JLabel("Secondary Muscle");
      lblSecondaryMuscle.setText("Secondary: "+this.workouts.getWorkout(workoutIndex).getSecondaryMuscle());
      pnlPrimarySecondary.add(lblSecondaryMuscle);
      
      taDescription = new JTextArea();
      taDescription.setFont(new Font("Arial", Font.PLAIN, 12));
      taDescription.setWrapStyleWord(true);
      taDescription.setText(this.workouts.getWorkout(workoutIndex).getDesc());
      taDescription.setLineWrap(true);
      taDescription.setEditable(false);
  
      JScrollPane spDescription = new JScrollPane(taDescription);
      GridBagConstraints gbc_spDescription = new GridBagConstraints();
      gbc_spDescription.insets = new Insets(0, 0, 5, 0);
      gbc_spDescription.fill = GridBagConstraints.BOTH;
      gbc_spDescription.gridx = 0;
      gbc_spDescription.gridy = 2;
      add(spDescription, gbc_spDescription);
      
      taNotes = new JTextArea();
      taNotes.setFont(new Font("Arial", Font.PLAIN, 12));
      taNotes.setWrapStyleWord(true);
      taNotes.setText(this.workouts.getWorkout(workoutIndex).getReminders());
      taNotes.setLineWrap(true);
      taNotes.setEditable(false);
  
      JScrollPane spNotes = new JScrollPane(taNotes);
      GridBagConstraints gbc_spNotes = new GridBagConstraints();
      gbc_spNotes.fill = GridBagConstraints.BOTH;
      gbc_spNotes.gridx = 0;
      gbc_spNotes.gridy = 3;
      add(spNotes, gbc_spNotes);
      
      revalidate();
      repaint();
    }
  }

  WorkoutsPanel(ArrayList<Workouts.Muscle> muscles, Workouts workouts)
  {
    this.workouts = workouts;
    
    for(Workouts.Muscle m : muscles) {
      WorkoutPanel wp = new WorkoutPanel(m, muscles, workouts);
      this.add(wp, m.toString());
    }
  }

}

