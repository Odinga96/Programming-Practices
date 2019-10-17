package acctMgr.test;

import acctMgr.model.Accountlist;
import org.junit.Test;

public class AccountListTest {
    Accountlist accountlist;

    @Test
    public void test1(){
        accountlist=new Accountlist();
        accountlist.loadAccounts("src/accounts");

        accountlist.getAccountList().forEach(e-> System.out.println(e.getID()+","+e.getName()+","+e.getBalance()));
    }
}
