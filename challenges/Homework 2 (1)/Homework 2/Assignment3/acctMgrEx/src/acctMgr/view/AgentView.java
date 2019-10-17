package acctMgr.view;

import acctMgr.controller.AgentController;
import acctMgr.model.Agent;
import acctMgr.model.ModelEvent;

import javax.swing.*;
import java.awt.*;

public class AgentView extends JFrameView {
    /**
     * Constructor for JFrameView
     *
     * @param model      - model to be set to view.
     * @param controller controller to be set to view.
     */
    public JTextField ammountTransfered,state,operationsCompleted;
    public AgentView(Agent model, AgentController controller, String agentType) {
        super(model, controller);


        JLabel title=new JLabel("A "+agentType+" for account "+((Agent)getModel()).getAccount().getID());
        title.setBounds(15,10,500,60);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setSize(600,300);
        panel.setBackground(Color.CYAN);

        JLabel ammountJLabel=new JLabel("Amount:");
        ammountJLabel.setBounds(10,80,100,50);
        JTextField ammount =new JTextField();
        ammount.setText(((Agent)getModel()).getAmount()+"");
        ammount.setBounds(130,80,150,50);
        ammount.setEditable(false);




        JLabel stateLabel=new JLabel("State");
        stateLabel.setBounds(10,150,100,50);
        state=new JTextField();
        state.setBounds(130,150,150,50);
        state.setEditable(false);

        JLabel transferedAmmountLabel=new JLabel("Amount in $");
        transferedAmmountLabel.setBounds(10,220,100,50);
        ammountTransfered=new JTextField();
        ammountTransfered.setBounds(130,220,150,50);
        ammountTransfered.setEditable(false);


        JLabel completedOperationsLalbel=new JLabel("Operations completed:");
        completedOperationsLalbel.setBounds(10,290,100,50);
        operationsCompleted=new JTextField();
        operationsCompleted.setText("0");
        operationsCompleted.setBounds(130,290,150,50);
        operationsCompleted.setEditable(false);


        panel.add(ammountJLabel);
        panel.add(ammount);

        panel.add(stateLabel);
        panel.add(state);

        panel.add(transferedAmmountLabel);
        panel.add(ammountTransfered);

        panel.add(completedOperationsLalbel);
        panel.add(operationsCompleted);


        this.getContentPane().add(panel, BorderLayout.CENTER);

    }

    @Override
    public void modelChanged(ModelEvent me) {
        state.setText(me.getAgStatus()+"");
        ammountTransfered.setText(me.getBalance()+"");
        operationsCompleted.setText((Integer.parseInt(operationsCompleted.getText())+1)+"");
    }

}
