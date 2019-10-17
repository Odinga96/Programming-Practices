package acctMgr.model;

import acctMgr.model.AbstractModel;

public class Error extends AbstractModel {
    String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
