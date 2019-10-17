package pendu;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class pendulum extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        PendulumPane pendulumPane = new PendulumPane(700, 400);

        Scene scene = new Scene(pendulumPane);
        primaryStage.setTitle("Pendulum");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private class PendulumPane extends Pane {

        private double width;
        private double height;
        PathTransition path;

        Rectangle topbar;
        Circle ball;
        Line spring, centerline;
        Arc arc;

        PendulumPane(double width, double height) {

            this.width = width;
            this.height = height;
            setPrefWidth(this.width);
            setPrefHeight(this.height);

            arc = new Arc(this.width / 2, this.height * 0.8, this.width * 0.15, this.width * 0.15, 10, 10);


            ball = new Circle(arc.getCenterX() - arc.getRadiusX(), arc.getCenterY(), 20);

            topbar=new Rectangle(arc.getCenterX()-25,arc.getCenterY()-100- (this.height / 2),50,10);


            arc = new Arc(topbar.getX(), topbar.getY()+10, (this.width / 2)+100, (this.height / 2)+100, 240, 60);
            spring = new Line(topbar.getX()+25, topbar.getY()+9, ball.getCenterX(), ball.getCenterY());

            

            centerline = new Line(topbar.getX()+25, topbar.getY()-9, width/2, height-10);

            centerline.getStrokeDashArray().addAll(1d,5d,1d);
            centerline.setStroke(Color.RED);



            spring.endXProperty().bind(ball.translateXProperty().add(ball.getCenterX()));
            spring.endYProperty().bind(ball.translateYProperty().add(ball.getCenterY()));

            path = new PathTransition();
            path.setDuration(Duration.millis(900));
            path.setPath(arc);
            path.setNode(ball);

            path.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            path.setCycleCount(PathTransition.INDEFINITE);
            path.setAutoReverse(true);

            getChildren().addAll(ball, topbar, spring, centerline);
            path.play();
        }
    }
}

