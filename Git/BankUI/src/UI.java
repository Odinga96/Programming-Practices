

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class UI extends JFrame {

    private static String OS=System.getProperty("os.name");

    public static void main (String[] args){
        UI a = new UI();
        a.setVisible(true);

        System.out.println(OS);
    }


     UI (){

        // Title at the top
        setTitle("Bank UI");

        // Default behavior on closing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        // This panel contains the screen to prevent the screen from being resized
        BankUI screenPanel = new BankUI(9);

        screenPanel.getDoTask1Button().setText("CLEAR");
        screenPanel.getDoTask1Button().setSize(200,40);
        screenPanel.getDoTask1Button().addActionListener(e -> screenPanel.clearFields());


        screenPanel.getDoTask2Button().setText("SAVE");
        screenPanel.getDoTask2Button().setSize(200,40);
        screenPanel.getDoTask2Button().addActionListener(e->{
               String[] values= screenPanel.getFieldValues();

               System.out.println(Arrays.toString(values));
            System.out.println(values[BankUI.ACCOUNT]);
        });

        JPanel heading=new JPanel();
        heading.setSize(500,80);
        Label tittle= new Label("Transaction Details");
        heading.add(tittle);


        // Organize everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(screenPanel, "Center");
        mainPanel.add(heading, "North");
        setContentPane(mainPanel);

        this.setSize(500,500);
//        pack();
    }

}