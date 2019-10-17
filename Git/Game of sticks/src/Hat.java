import java.util.ArrayList;
import java.util.List;

public class Hat {

    ArrayList<Ball> hats=new ArrayList<>();
    private Ball chosenBall;

    public Ball getChosenBall() {
        return chosenBall;
    }

    public void setChosenBall(Ball chosenBall) {
        this.chosenBall = chosenBall;
    }
}
