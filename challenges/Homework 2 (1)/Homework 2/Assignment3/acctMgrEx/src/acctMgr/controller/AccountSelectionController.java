package acctMgr.controller;

import acctMgr.model.Account;
import acctMgr.model.Accountlist;
import acctMgr.view.AccountSelectionView;

import java.util.Objects;

public class AccountSelectionController extends AbstractController {

    public static  Accountlist ACCOUNTLIST;

    public AccountSelectionController(String file) {
        ACCOUNTLIST =new Accountlist(file);

        setModel(ACCOUNTLIST);
        setView(new AccountSelectionView(getModel(),this));
        ((AccountSelectionView)getView()).setVisible(true);
        ((AccountSelectionView)getView()).setResizable(false);
        ((AccountSelectionView)getView()).setSize(600,300);
    }

    public void operation(String operation){
        String[] selction= ((String) Objects.requireNonNull(AccountSelectionView.accounts.getSelectedItem())).split(":");
        final Account[] account = new Account[1];

        ACCOUNTLIST.getAccountList().forEach(e->{
            if (e.getID() == Integer.parseInt(selction[0]))
                account[0] =e;
        });

        switch (operation){
            case AccountSelectionView.CU_DOLLAR:
                new AccountController(account[0],operation);
                break;
            case AccountSelectionView.CU_EURO:
                new AccountController(account[0],operation);
                break;
            case AccountSelectionView.CU_YEN:
                new AccountController(account[0],operation);
                break;
            case "SAVE":
                ACCOUNTLIST.saveAccounts();
                break;
            case "EXIT":
                ((AccountSelectionView)getView()).unregisterWithModel();
                ((Accountlist)getModel()).saveAccounts();
                System.exit(0);
                break;
            case "CREATE DEPOSIT AGENT":

                new agentCreationController(account[0],"deposit");
                break;
            case "CREATE WITHDRAW AGENT":
                new agentCreationController(account[0],"withdraw");
                break;
        }
    }
}
