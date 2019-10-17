package acctMgr.controller;

import acctMgr.model.Account;
import acctMgr.model.Accountlist;
import acctMgr.model.Agent;
import acctMgr.model.AgentCreator;
import acctMgr.view.AccountSelectionView;
import acctMgr.view.AgentCreationView;
import acctMgr.view.AgentView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class agentCreationController extends AbstractController {

    private static final List<String> agentIDS=new ArrayList<>();
    private Accountlist accountlist=AccountSelectionController.ACCOUNTLIST;

    public agentCreationController(Account account, String type) {
        setModel(new AgentCreator(account));

        setView(new AgentCreationView(getModel(),this,type));

        ((AgentCreationView)getView()).setVisible(true);
        ((AgentCreationView)getView()).setResizable(false);
        ((AgentCreationView)getView()).setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ((AgentCreationView)getView()).setSize(600,400);
    }


    public void operation(String actionCommand) {

        switch (actionCommand){
            case "Start agent":
                String  id        =((AgentCreationView)getView()).getAgentId().getText();
                String  amount    =((AgentCreationView)getView()).getAmount().getText();
                String  ops       =((AgentCreationView)getView()).getOperationsPs().getText();
                String  agentType =((AgentCreationView)getView()).getAgentType();

                String[] selction= ((String) Objects.requireNonNull(AccountSelectionView.accounts.getSelectedItem())).split(":");
                final Account[] account = new Account[1];

                accountlist.getAccountList().forEach(e->{
                    if (e.getID() == Integer.parseInt(selction[0]))
                        account[0] =e;
                });

                double ammountAssigned=0.00;
                double operationsPs=0.00;

                if (!agentIDS.contains(id) && !id.isEmpty()){
                    try {
                        ammountAssigned=Double.parseDouble(amount);
                        try {  operationsPs=Double.parseDouble(ops);
                            Agent agent=new Agent(id,  operationsPs,  ammountAssigned,  agentType, account[0]);
                            new AgentController(agent);
                            agentIDS.add(id);
                        }
                        catch (NumberFormatException e){ new ErrorController("Operations per second can only be a number"); }

                    }
                    catch (NumberFormatException e){ new ErrorController("Amount can only be a number"); }




                }else{ new ErrorController("The selected Id is already in use"); }

                break;
            case "Dismiss":
                if (AgentController.thread != null)
                   AgentController.thread.stop();
                ((AgentCreationView)getView()).unregisterWithModel();
                ((AgentCreationView)getView()).dispose();
                break;
        }
    }
}
