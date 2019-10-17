import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SatelliteTest extends JFrame {
    private JRadioButton sourceHosts;
    private JRadioButton destinationHosts; //two buttons under the file
private Font font;
private JPanel radioButtonPanel;
    private ButtonGroup radioButtons;
    private JPanel radioButtonPanel2;
   
    public SatelliteTest() {
    super("Flow volumn viewer");
    setLayout(new FlowLayout());
    setSize(1000,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    font = new Font("Sans-serif", Font.PLAIN, 20);
    setupMenu();
    setupRadiobutton();
    paint(null);
   
    setVisible(true);
    }

@Override
public void paint(Graphics g) {
super.paint(g);
g.setColor(Color.WHITE);
g.fillRect(0, 150, 1000, 325);
}
private void setupMenu() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    fileMenu.setFont(font);
    menuBar.add(fileMenu);
    JMenuItem fileMenuOpen = new JMenuItem("Open trace file");
    fileMenuOpen.setFont(font);
    fileMenu.add(fileMenuOpen);
    //implement an action listner here. just like quit
       // need to complete this.
  JMenuItem fileMenuQuit = new JMenuItem("Quit");
  fileMenuQuit.setFont(font);
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
    private void setupRadiobutton() {
    radioButtonPanel = new JPanel();
    radioButtonPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = GridBagConstraints.RELATIVE;
    c.anchor = GridBagConstraints.WEST;
        radioButtons = new ButtonGroup();
        sourceHosts = new JRadioButton("Source hosts");
        sourceHosts.setFont(font);
        sourceHosts.setSelected(true);
        radioButtons.add(sourceHosts);
        radioButtonPanel.add(sourceHosts, c);
        destinationHosts = new JRadioButton("Destination host");
        destinationHosts.setFont(font);
        radioButtons.add(destinationHosts);
    radioButtonPanel.add(destinationHosts, c);
    add(radioButtonPanel);
    setVisible(true);
    }
}

