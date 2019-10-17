package acctMgr.model;

import javax.swing.*;
import java.math.BigDecimal;

public class Agent extends AbstractModel implements Runnable{
   private String agentID;
   private double operations_per_second;
   private double amount;
   private String operation;
   private Account account;
   private AgentStatus agentStatus;
   private double totalAmountTranfered=0.00;


    public Agent(String agentID, double operations_per_second, double ammount, String operation, Account account) {
        this.agentID = agentID;
        this.operations_per_second = operations_per_second;
        this.amount = ammount;
        this.operation = operation;
        this.account = account;
        this.agentStatus= AgentStatus.Running;
    }

    public Agent() { }


    public  synchronized void deposit(){
            account.deposit(BigDecimal.valueOf(getAmount()));
            totalAmountTranfered+=amount;

        ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountTransferredUpdate, BigDecimal.valueOf(totalAmountTranfered), this.agentStatus);
        notifyChanged(me);
    }

    public synchronized void withdraw(){
            if (account.getBalance().compareTo(BigDecimal.valueOf(this.amount))>=1) {
                account.withdraw(BigDecimal.valueOf(amount));


                totalAmountTranfered+=amount;

                ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountTransferredUpdate, BigDecimal.valueOf(totalAmountTranfered), getAgentStatus());
                notifyChanged(me);
            } else {
                this.agentStatus=AgentStatus.Blocked;

                ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountTransferredUpdate, BigDecimal.valueOf(totalAmountTranfered), getAgentStatus());
                notifyChanged(me);
            }

    }



    public String getAgentID() {
        return agentID;
    }

    public double getOperations_per_second() {
        return operations_per_second;
    }

    public double getAmount() {
        return amount;
    }

    public String getOperation() {
        return operation;
    }

    public Account getAccount() {
        return account;
    }

    public AgentStatus getAgentStatus() {
        return agentStatus;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(500);
                switch (operation) {
                    case "deposit":
                        this.deposit();
                        break;
                    case "withdraw":
                        this.withdraw();
                        break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (agentStatus.equals(AgentStatus.Running));

    }


}
