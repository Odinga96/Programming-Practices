import java.io.FileNotFoundException;
import java.util.Date;

public class StaticDataNotFoundException extends FileNotFoundException {
  private   String message;
  private Date date;

    public StaticDataNotFoundException(String s, String message) {
        super(s);
        this.message = message+" "+date.getTime();
    }

    public StaticDataNotFoundException(Exception e) {
        this.message = e.getClass().toString() + ": " + e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
