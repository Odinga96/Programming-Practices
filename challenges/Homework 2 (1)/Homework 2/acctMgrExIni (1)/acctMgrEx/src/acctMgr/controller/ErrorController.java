package acctMgr.controller;

import acctMgr.model.Error;
import acctMgr.view.ErrorView;

public class ErrorController extends AbstractController {

    public ErrorController(String message) {
        setModel(new Error(message));
        setView(new ErrorView(getModel(),this));


        ((ErrorView)getView()).setVisible(true);
        ((ErrorView)getView()).setResizable(false);
        ((ErrorView)getView()).setSize(500,260);
    }

    public void operation() {
        ((ErrorView)getView()).dispose();
    }
}
