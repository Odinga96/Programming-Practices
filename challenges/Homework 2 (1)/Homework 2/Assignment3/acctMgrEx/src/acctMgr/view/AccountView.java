package acctMgr.view;

import acctMgr.controller.AccountController;
import acctMgr.controller.Controller;
import acctMgr.model.Account;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

public class AccountView extends JFrameView {

    /**
     * Constructor for JFrameView
     *
     * @param model      - model to be set to view.
     * @param controller controller to be set to view.
     */

    private   JTextField availableFunds;
    private   JTextField ammount;
    private   String operation;

    public JTextField getAvailableFunds() {
        return availableFunds;
    }

    public JTextField getAmmount() {
        return ammount;
    }

    public AccountView(Model model, Controller controller, String operations) {
        super(model, controller);

        Handler handler=new Handler();
        operation=operations;

        JLabel title=new JLabel(((Account)model).getName()+" "+((Account)model).getID()+" operations in "+operation);
        title.setBounds(15,10,500,60);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setSize(600,300);
        panel.setBackground(Color.CYAN);



        Label availableFundsL = new Label("Available funds:");
        availableFundsL.setBounds(15,90,200,40);


        BigDecimal funds;
        if (operation.equals(AccountSelectionView.CU_DOLLAR))
            funds = ((Account) model).getBalance();
        else if (operation.equals(AccountSelectionView.CU_EURO))
            funds = ((Account) model).getBalance().multiply(BigDecimal.valueOf(AccountSelectionView.EURO));
        else
            funds = ((Account) model).getBalance().multiply(BigDecimal.valueOf(AccountSelectionView.YEN));

        availableFunds = new JTextField(funds+"");
        availableFunds.setEnabled(false);
        availableFunds.setBounds(230,90,350,40);


        JLabel ammountL=new JLabel("Enter amount in "+operation);
        ammountL.setBounds(15,160,200,40);


        ammount=new JTextField("0.00");
        ammount.setBounds(230,160,350,40);


        JButton deposit=new JButton("DEPOSIT");
        deposit.setBounds(130,220,170,40);
        deposit.addActionListener(handler);

        JButton withdraw=new JButton("WITHDRAW");
        withdraw.setBounds(320,220,170,40);
        withdraw.addActionListener(handler);

        JButton dismiss  =new JButton("DISMISS");
        dismiss.setBounds(510,220,170,40);
        dismiss.addActionListener(handler);



        panel.add(title);
        panel.add(availableFundsL);
        panel.add(availableFunds);

        panel.add(ammountL);
        panel.add(ammount);

        panel.add(deposit);
        panel.add(withdraw);
        panel.add(dismiss);

        this.getContentPane().add(panel, BorderLayout.CENTER);


    }

    public String getOperation() {
        return operation;
    }

    @Override
    public void modelChanged(ModelEvent me) {

        BigDecimal funds;
        if (operation.equals(AccountSelectionView.CU_DOLLAR))
            funds = me.getBalance();
        else if (operation.equals(AccountSelectionView.CU_EURO))
            funds = me.getBalance().multiply(BigDecimal.valueOf(AccountSelectionView.EURO));
        else
            funds = me.getBalance().multiply(BigDecimal.valueOf(AccountSelectionView.YEN));

       availableFunds.setText(funds+"");
       ammount.setText("0.00");
    }


    // Inner classes for Event Handling
    class Handler implements ActionListener {
        // Event handling is handled locally
        public void actionPerformed(ActionEvent e) {
            ((AccountController)getController()).operation(e.getActionCommand());
        }
    }
}

