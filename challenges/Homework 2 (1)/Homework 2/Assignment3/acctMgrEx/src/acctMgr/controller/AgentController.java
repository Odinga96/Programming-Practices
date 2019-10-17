package acctMgr.controller;

import acctMgr.model.Agent;
import acctMgr.view.AgentView;

public class AgentController extends AbstractController{

    public static Thread thread;
    public AgentController(Agent agent) {
        setModel(agent);
        setView(new AgentView(agent,this, agent.getOperation()));

        ((AgentView)getView()).setVisible(true);
        ((AgentView)getView()).setResizable(false);
        ((AgentView)getView()).setSize(600,400);

        thread=new Thread((Agent)getModel());
        thread.start();

    }
}
