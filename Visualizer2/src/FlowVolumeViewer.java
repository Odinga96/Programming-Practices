import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlowVolumeViewer extends JFrame {
	private JComboBox combo;
	//DefaultComboBoxModel<String> srcModel, destModel;
	private JRadioButton radioButtonSource, radioButtonDestination;
        private File traceFile = null;
        private Set<String> sourceHosts = null;
        private Set<String> destinationHosts = null;
        private JPanel graph;
        
    /** main method for A2
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FlowVolumeViewer();
            }
        });
    }
    public FlowVolumeViewer() {
		super("Flow Volume Viewer");
                graph= new PlotGraph();
                //graph.setLocation(0,100);
                add(graph);
                
                setJMenuBar(setMenuBar());
		setupRadioButtons();
		getContentPane().add(setUpToolsPanel(), BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setVisible(true);
                
		//String labels1[] = { "A", "B", "C", "D", "E" };
		//srcModel = new DefaultComboBoxModel<String>(labels1);
		//String labels2[] = { "1", "2", "3", "4", "5" };
		///destModel = new DefaultComboBoxModel<String>(labels2);
                
    }

	private void setupRadioButtons() {
            ButtonGroup group = new ButtonGroup ();
		radioButtonSource = new JRadioButton("Source hosts");
		radioButtonDestination = new JRadioButton("Destination hosts");
		// add a ButtonGroup

		radioButtonSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//complete this
			}
		});
		radioButtonDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//complete this
			}
		});
                
                ///C
      ItemListener itemListener = new ItemListener() {
      String lastSelected;


      public void itemStateChanged(ItemEvent itemEvent) {
        AbstractButton aButton = (AbstractButton)itemEvent.getSource();
        int state = itemEvent.getStateChange();
        String label = aButton.getText();
        String msgStart;
        if (state == ItemEvent.SELECTED) {
          populateCombo();
        }
      }
    };
                    //C
                radioButtonSource.addItemListener(itemListener);radioButtonDestination.addItemListener(itemListener);
                radioButtonSource.setSelected(true);
                group.add(radioButtonSource);group.add(radioButtonDestination);
	}
	public JPanel setUpToolsPanel() {
		combo = new JComboBox<String>();
		combo.addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        String ip  = (String) combo.getSelectedItem();
                        System.out.println(ip);
                    }
			//complete this
		});
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(radioButtonSource);
		toolsPanel.add(radioButtonDestination);
		toolsPanel.add(combo);
                combo.setVisible(false);
		return toolsPanel;
    }
        private JMenuBar setMenuBar(){
            JMenuBar menu = new JMenuBar();
            JButton openTraceFile =new JButton ("Open trace file");
            openTraceFile.addActionListener(e->{
            chooseTraceFile();
                try {
                    obtainHosts();
                } catch (FileNotFoundException ex) {
                    System.err.println("No such file found");
                }
            populateCombo();
            });
            JButton quit = new JButton("Quit");
            quit.addActionListener(e->{exit(0);});
            menu.add(openTraceFile);
            menu.add(quit);
            return menu;
        }
        
        private void chooseTraceFile(){
            JFileChooser chooser =new JFileChooser();
              if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             traceFile = chooser.getSelectedFile();
            }
        }
        
        private void obtainHosts() throws FileNotFoundException{
            
            if(traceFile != null){
                sourceHosts =new  HashSet<String>();
                destinationHosts=new HashSet<String>();
                Scanner in = new Scanner(traceFile);
                while(in.hasNextLine()){
                    String[] line = in.nextLine().split("\t");
                    String sourceHost = line[2].trim();
                    String destinationHost = line[4].trim();
                    if(!sourceHost.equals("")){sourceHosts.add(sourceHost);}
                    if(!destinationHost.equals("")){destinationHosts.add(destinationHost);}
                }
            }
        }
        private void populateCombo(){
           if(sourceHosts != null && destinationHosts != null){

                if(radioButtonSource.isSelected()){
                    combo.removeAllItems();
                    for(String s :sourceHosts ){
                        combo.addItem(s);
                    }
                
            }else if(radioButtonDestination.isSelected()){
                System.out.println("Destination");
                 combo.removeAllItems();
                    for(String s: destinationHosts){
                        combo.addItem(s);
                    }
                
            }
            combo.setVisible(true);
           }
            
        }
        
        
}