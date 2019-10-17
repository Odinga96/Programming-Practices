package acctMgr.controller;

import acctMgr.model.Account;
import acctMgr.view.AccountSelectionView;
import acctMgr.view.AccountView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AccountController extends AbstractController {

    public AccountController(Account account,String operations) {
        setModel(account);
        setView(new AccountView(account,this,operations));

        ((AccountView)getView()).setVisible(true);
        ((AccountView)getView()).setResizable(false);
        ((AccountView)getView()).setSize(700,300);
    }

    public void operation(String operation){
        BigDecimal balance=new BigDecimal(((AccountView)getView()).getAvailableFunds().getText());
        BigDecimal ammount=new BigDecimal(((AccountView)getView()).getAmmount().getText());

        BigDecimal exchangedAmmount;
        if (((AccountView) getView()).getOperation().equals(AccountSelectionView.CU_DOLLAR))
            exchangedAmmount = ammount;
        else if (((AccountView) getView()).getOperation().equals(AccountSelectionView.CU_EURO))
            exchangedAmmount = ammount.divide(BigDecimal.valueOf(AccountSelectionView.EURO),2, RoundingMode.HALF_EVEN);
        else exchangedAmmount = ammount.divide(BigDecimal.valueOf(AccountSelectionView.YEN),2, RoundingMode.HALF_EVEN);

        switch (operation){
            case "DEPOSIT":
                if (ammount.compareTo(new BigDecimal(0))>=1){
                    ((Account)getModel()).deposit(exchangedAmmount);

                }else{
                    System.out.println("warning window");
                }
                break;
            case "WITHDRAW":
                if (balance.compareTo(ammount)>=1){
                    ((Account)getModel()).withdraw(exchangedAmmount);
                }else{
                    System.out.println("warning window");
                }

                break;
            case "DISMISS":
                ((AccountView)getView()).unregisterWithModel();
                ((AccountView)getView()).dispose();
                break;
        }
    }
}
