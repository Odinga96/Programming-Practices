package acctMgr.model;

import java.math.BigDecimal;

public class Account extends AbstractModel {

    private BigDecimal balance;
    private String name;
    private int ID;

    /**
     * @param balance shows the balance in the account in the specified currency
     * @param name   is the name of the account holder, this can only contain digits
     * @param ID    unique number(Integer) used to identified a unique account
     */
    public Account(BigDecimal balance, String name, int ID) {
        this.balance = balance;
        this.name = name;
        this.ID = ID;
    }

    /**
     * @param amount :the amount the user wants to withdraw from the account, value should always be in dollars
     */
    public void withdraw(BigDecimal amount){

        this.balance=this.balance.subtract(amount);
    }

    /**
     * @param amount :the amount to be deposited to the account, value should always be in dollars
     */
    public void deposit(BigDecimal amount){
        this.balance=this.balance.add(amount);
    }

    public BigDecimal getBalance() { return balance; }

    public String getName() { return name; }

    public int getID() { return ID; }
}
