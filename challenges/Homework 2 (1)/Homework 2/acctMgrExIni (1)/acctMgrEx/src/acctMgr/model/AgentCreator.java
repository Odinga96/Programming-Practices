package acctMgr.model;

public class AgentCreator extends AbstractModel {
 private Account account;

    public AgentCreator(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
