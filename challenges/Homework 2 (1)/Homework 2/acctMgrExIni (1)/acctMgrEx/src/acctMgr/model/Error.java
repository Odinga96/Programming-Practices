package acctMgr.model;

public class Error extends AbstractModel{
    String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
