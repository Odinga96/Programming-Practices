import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LinearAnimation extends Application {
    @Override
    public void start(final Stage stage) throws Exception {


        final Group group = new Group();

        double width  = 800;
        double height = 800;

        final Scene scene = new Scene(group, width, height, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Linear animation");
        stage.show();


        // Ball
        final Circle circle = new Circle(0, 0, 50);
        circle.setFill(Color.RED);


        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setPath(new Line(-50,height+50,width+50,-50));
        pathTransition.setNode(circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);


        group.getChildren().add(circle);

        pathTransition.play();


    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }
}