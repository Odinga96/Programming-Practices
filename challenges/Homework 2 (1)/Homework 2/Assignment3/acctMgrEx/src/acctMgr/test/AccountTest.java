package acctMgr.test;

import acctMgr.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    Account account;



    @Test
   public void test1(){
        account=new Account(new BigDecimal(1200.00),"David Mike",30);

        assertEquals(new BigDecimal(1200.00),account.getBalance());
        assertEquals("David Mike",account.getName());
        assertEquals(30,account.getID());

        account.withdraw(new BigDecimal(1000));
        assertEquals(new BigDecimal(200),account.getBalance());

        account.deposit(new BigDecimal(10800));
        assertEquals(new BigDecimal(11000),account.getBalance());


    }
}
