package acctMgr.view;

import acctMgr.controller.AccountController;
import acctMgr.controller.Controller;
import acctMgr.controller.agentCreationController;
import acctMgr.model.Agent;
import acctMgr.model.AgentCreator;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgentCreationView extends JFrameView {
    private JTextField agentId;
    private JTextField amount;
    private JTextField operationsPs;
    private String agentType;
    /**
     * Constructor for JFrameView
     *
     * @param model      - model to be set to view.
     * @param controller controller to be set to view.
     */
    public AgentCreationView(Model model, Controller controller, String agentType) {
        super(model, controller);
        this.agentType=agentType;

        JLabel title=new JLabel("Start "+agentType+" agent for account: "+((AgentCreator)getModel()).getAccount().getID());
        title.setBounds(15,10,500,60);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setSize(600,300);
        panel.setBackground(Color.CYAN);

        JLabel agentIdLabel=new JLabel("Agent Id:");
        agentIdLabel.setBounds(10,80,200,50);

        agentId =new JTextField();
        agentId.setBounds(230,80,300,50);


        JLabel amountLabel=new JLabel("Amount:");
        amountLabel.setBounds(10,150,200,50);

        amount =new JTextField();
        amount.setBounds(230,150,300,50);


        JLabel operationsPsLabel=new JLabel("Operations p/s:");
        operationsPsLabel.setBounds(10,220,200,50);

        operationsPs =new JTextField();
        operationsPs.setBounds(230,220,300,50);



        Handler handler=new Handler();

        JButton  startAgent=new JButton("Start agent");
        startAgent.setBounds(130,280,200,50);
        startAgent.addActionListener(handler);

        JButton  dismiss=new JButton("Dismiss");
        dismiss.setBounds(350,280,200,50);
        dismiss.addActionListener(handler);


        panel.add(title);
        panel.add(agentIdLabel);
        panel.add(agentId);

        panel.add(amountLabel);
        panel.add(amount);

        panel.add(operationsPsLabel);
        panel.add(operationsPs);

        panel.add(startAgent);
        panel.add(dismiss);




        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public JTextField getAgentId() {
        return agentId;
    }

    public JTextField getAmount() {
        return amount;
    }

    public JTextField getOperationsPs() {
        return operationsPs;
    }

    public String getAgentType() {
        return agentType;
    }

    @Override
    public void modelChanged(ModelEvent me) {

    }

    // Inner classes for Event Handling
    class Handler implements ActionListener {
        // Event handling is handled locally
        public void actionPerformed(ActionEvent e) {
            ((agentCreationController)getController()).operation(e.getActionCommand());
        }
    }
}
