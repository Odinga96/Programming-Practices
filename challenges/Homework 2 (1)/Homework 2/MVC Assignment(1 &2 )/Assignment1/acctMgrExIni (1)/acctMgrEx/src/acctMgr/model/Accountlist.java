package acctMgr.model;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountList class will be used to maintain the list of all the accounts from the specified file
 */
public class Accountlist {

    private List<Account> accountList;

    public Accountlist() {
        accountList=new ArrayList<>();
    }


    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * @param account :specifies the account to be added to the current list of accounts
     */
    public void addAaccount(Account account){ this.accountList.add(account); }

    /**
     * @param account :specifies the account to be removed from the current list of accounts
     */
    public void removeAccount(Account account){this.accountList.remove(account);}

    /**
     * @param filename :the file specified that contains the current list of accounts
     */
    public void loadAccounts(String filename){

        try {
            BufferedReader reader =new BufferedReader(new FileReader(filename));
            String readline;
            while ((readline=reader.readLine()) !=null){
                String[] account= readline.split(",");

                int           id =Integer.parseInt(account[0]);
                String      name =account[1];
                BigDecimal balance= new BigDecimal(account[2]);

                Account account1=new Account(balance,name,id);
                this.accountList.add(account1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param accountsFile :the  to save the current list of accounts
     */
    public void saveAccounts(File accountsFile){
        try {
            FileWriter inputStream=new FileWriter(accountsFile);
            accountList.forEach(e->{
                StringBuilder account=new StringBuilder();

                account.append(e.getID()).append(",")
                        .append(e.getName()).append(",")
                        .append(e.getBalance());
                try {
                    inputStream.write(String.valueOf(account));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
