package acctMgr.model;

import acctMgr.view.AccountSelectionView;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountList class will be used to maintain the list of all the accounts from the specified file
 */
public class Accountlist extends AbstractModel{

    private List<Account> accountList;
    private File accountsFile;

    public Accountlist(String file) {
        accountList=new ArrayList<>();
        accountsFile=new File(file);
        loadAccounts();

        accountList.forEach(e->AccountSelectionView.accounts.addItem(e.getID()+": "+e.getName()));
    }

    public Accountlist() {
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
     *
     */
    public void loadAccounts(){

        try {
            BufferedReader reader =new BufferedReader(new FileReader(accountsFile));
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


    public void saveAccounts(){
        try {
            FileWriter inputStream=new FileWriter(accountsFile);
            accountList.forEach(e->{
                StringBuilder account=new StringBuilder();

                account.append(e.getID()).append(",")
                        .append(e.getName()).append(",")
                        .append(e.getBalance()).append("\n");
                try {
                    inputStream.write(String.valueOf(account));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
           inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
