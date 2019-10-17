import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;

public class A2HelpQ1 extends JFrame {
	private JComboBox<String> mycomboBox;
	DefaultComboBoxModel<String> srcModel, destModel;
	private JRadioButton radioButtonABC, radioButton123;
	private ButtonGroup radioButton;
    /** main method for A2
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new A2HelpQ1();
            }
        });
    }
    public A2HelpQ1() {
		super("A2");
		setupRadioButtons();
		setupMenu();
		getContentPane().add(setUpToolsPanel(), BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
		String labels1[] = { "A", "B", "C", "D", "E" };
		srcModel = new DefaultComboBoxModel<String>(labels1);
		String labels2[] = { "1", "2", "3", "4", "5" };
		destModel = new DefaultComboBoxModel<String>(labels2);
    }

	private void setupRadioButtons() {
		radioButtonABC = new JRadioButton("Source Hosts");
		radioButton123 = new JRadioButton("Destination Hosts");
		radioButton = new ButtonGroup();

		//radioButtonABC.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				//complete this
			//}
		//});
		//radioButton123.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				//complete this
			//}
		//});
	}
	private void setupMenu() {
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic('F');
	    fileMenu.setFont(getFont());
	    menuBar.add(fileMenu);
	    JMenuItem fileMenuOpen = new JMenuItem("Open trace file");
	    fileMenuOpen.setFont(getFont());
	    fileMenu.add(fileMenuOpen);
	    fileMenuOpen.addActionListener(new ActionListener()
	    		{
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser fc = new JFileChooser();
	    		if (fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
	    			fc.getSelectedFile();
	    			Scanner input = new Scanner((Readable) fc);
	    			input.close();
	    		}
	    		
	    	}
	    		});
	  JMenuItem fileMenuQuit = new JMenuItem("Quit");
	  fileMenuQuit.setFont(getFont());
	  fileMenu.add(fileMenuQuit);
	  fileMenuQuit.addActionListener(
	    new ActionListener()
	    {
	     public void actionPerformed(ActionEvent e) {
	      System.exit(0);
	     }
	    }
	    );
	    }
	public JPanel setUpToolsPanel() {
		mycomboBox = new JComboBox<String>();
		//mycomboBox.addItemListener(new ItemListener() {

			//@Override
			//public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
			//}
			//complete this
		//}
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(radioButtonABC);
		toolsPanel.add(radioButton123);
		toolsPanel.add(mycomboBox);
		return toolsPanel;
    }
}
