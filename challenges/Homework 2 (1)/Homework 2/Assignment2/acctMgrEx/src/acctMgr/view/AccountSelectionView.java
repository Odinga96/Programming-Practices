package acctMgr.view;

import acctMgr.controller.AccountSelectionController;
import acctMgr.controller.Controller;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSelectionView extends JFrameView {
    /**
     * Constructor for JFrameView
     *
     * @param model      - model to be set to view.
     * @param controller controller to be set to view.
     */

    public static JComboBox<String> accounts=new JComboBox<>();
    public static final double YEN=94.1;
    public static final double EURO=0.79;

    public static final String CU_YEN="YEN";
    public static final String CU_EURO="EURO";
    public static final String CU_DOLLAR="$";

    public AccountSelectionView(Model model, Controller controller) {
        super(model, controller);
        Handler handler = new Handler();

        JLabel selectAccount=new JLabel("Select Account");
        selectAccount.setBounds(15,10,100,40);
        accounts.setBounds(130,10,200,40);


        JPanel selectionView = new JPanel();
        selectionView.setLayout(null);
        selectionView.setSize(600,300);
        selectionView.setBackground(Color.CYAN);

        JLabel editIn=new JLabel("Edit in:");
        editIn.setBounds(15,70,100,40);


        JButton edit1=new JButton(CU_DOLLAR);
        edit1.setBounds(130,70,140,40);
        edit1.addActionListener(handler);

        JButton edit2=new JButton(CU_EURO);
        edit2.setBounds(280,70,140,40);
        edit2.addActionListener(handler);


        JButton edit3=new JButton(CU_YEN);
        edit3.setBounds(430,70,140,40);
        edit3.addActionListener(handler);






        JButton save=new JButton("SAVE");
        save.setBounds(130,190,215,40);
        save.addActionListener(handler);

        JButton exit=new JButton("EXIT");
        exit.setBounds(355,190,215,40);
        exit.addActionListener(handler);


        selectionView.add(selectAccount);
        selectionView.add(accounts);

        selectionView.add(editIn);
        selectionView.add(edit1);
        selectionView.add(edit2);
        selectionView.add(edit3);
        selectionView.add(save);
        selectionView.add(exit);



        this.getContentPane().add(selectionView,BorderLayout.CENTER);
    }

    @Override
    public void modelChanged(ModelEvent me) {

    }

    // Inner classes for Event Handling
    class Handler implements ActionListener {
        // Event handling is handled locally
        public void actionPerformed(ActionEvent e) {
            ((AccountSelectionController)getController()).operation(e.getActionCommand());
        }
    }
}
