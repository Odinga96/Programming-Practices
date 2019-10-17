import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Visualizer extends JFrame {

    Graph  graph=new Graph();

    private ArrayList<Host> sourceHosts=new ArrayList<>();
    private ArrayList<Host> destinationHosts=new ArrayList<>();


    private JComboBox<String> mycomboBox;
//    private DefaultComboBoxModel<String> srcModel, destModel;


    private File    selectedFile=null;
    private HashSet<String> sourceIPS=null;
    private HashSet<String> destinationIPS=null;


    private JRadioButton radioButtonSource, radioButtonDestination;
    private ButtonGroup radioButton;

    /** main method for A2
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Visualizer();
            }
        });
    }



    public Visualizer() {
        super("A2");
        setupRadioButtons();
        setupMenu();
        getContentPane().add(setUpToolsPanel(), BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1150, 700);
        setVisible(true);



            add(graph);
    }

    private void setupRadioButtons() {
        radioButtonSource = new JRadioButton("Source Hosts");
        radioButtonDestination = new JRadioButton("Destination Hosts");
        radioButton = new ButtonGroup();

        radioButton.add(radioButtonDestination);
        radioButton.add(radioButtonSource);

        //Setting the source button to be selected
        radioButtonSource.setSelected(true);


        //Handling action on the source radio button
        radioButtonSource.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mycomboBox.removeAllItems();


            if (sourceIPS !=null)
            for (String host:sourceIPS) {
                mycomboBox.addItem(host);
            }
        }
        });


//        Handling action on the destination action button
        radioButtonDestination.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mycomboBox.removeAllItems();
            remove(graph);

            if (destinationIPS !=null)
                for (String host:destinationIPS) {
                    mycomboBox.addItem(host);
                }

        }
        });
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

                    selectedFile=fc.getSelectedFile();

                    //obtain the source Ip and the destination IP

                    if(selectedFile != null){
                        sourceIPS =new  HashSet<>();
                        destinationIPS=new HashSet<>();



                        try (Scanner in = new Scanner(selectedFile)) {

                            List<String> source=new ArrayList<>();
                            List<String> destination=new ArrayList<>();

                            int max=0;
                            while (in.hasNextLine()) {
                                String[] line = in.nextLine().split("\t");
                                String sourceHost = line[2].trim();
                                String destinationHost = line[4].trim();

                                String timeStamp=line[1].trim();
                                double time=Double.parseDouble(timeStamp);


                                String packet_size=(line.length>=8)?line[7].trim():line[6];
                                int size=(!packet_size.equals(""))?Integer.parseInt(packet_size):0;



                                if (!sourceHost.equals("") && !destinationHost.equals(""))
                                    if (size>=max)
                                        max=size;



                                if (!sourceHost.equals("")) {
                                    source.add(sourceHost);
                                    sourceHosts.add(new Host(sourceHost,size,time));

                                }
                                if (!destinationHost.equals("")) {
                                    destination.add(destinationHost);

                                    destinationHosts.add(new Host(destinationHost,size,time));
                                }
                            }


                            sourceIPS.addAll(source);

                            destinationIPS.addAll(destination);

                            System.out.println(max);


                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }


                        //populate combobox and make it visible

                        if(sourceIPS != null && destinationIPS != null){

                            if(radioButtonSource.isSelected()){
                                mycomboBox.removeAllItems();
                                for(String s :sourceIPS ){
                                    mycomboBox.addItem(s);
                                }

                            }else if(radioButtonDestination.isSelected()){
                                System.out.println("Destination");
                                mycomboBox.removeAllItems();
                                for(String s: destinationIPS){
                                    mycomboBox.addItem(s);
                                }

                            }
                            mycomboBox.setVisible(true);
                        }
                    }
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
        mycomboBox = new JComboBox<>();
        //mycomboBox.addItemListener(new ItemListener() {

        //@Override
        //public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

        //}
        //complete this
        //}

        mycomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ip= (String) mycomboBox.getSelectedItem();

                if(ip !=null){

                    //Check if graph to check is null
                    if (Graph.hosts_tocheck == null)
                        Graph.hosts_tocheck =new ArrayList<>();



                    if(radioButtonSource.isSelected()){
                        if (sourceHosts.size()>0){

                            Graph.hosts_tocheck.clear();

                            for (Host host:sourceHosts) {
                                  if (host.getIP_address().equals(ip))
                                      Graph.hosts_tocheck.add(host);
                            }

                        }
                    } else if(radioButtonDestination.isSelected()){
                        if (destinationHosts.size()>0){

                            Graph.hosts_tocheck.clear();

                            for (Host host:destinationHosts) {
                                if (host.getIP_address().equals(ip))
                                    Graph.hosts_tocheck.add(host);
                            }

                        }
                    }

                    remove(graph);
                    graph=new Graph();
                    add(graph);
                    graph.repaint();
                    setSize(1155,703);
                    setSize(1150, 700);

                }
            }
        });


        mycomboBox.setVisible(false);
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
        toolsPanel.add(radioButtonSource);
        toolsPanel.add(radioButtonDestination);
        toolsPanel.add(mycomboBox);
        return toolsPanel;
    }
}
