package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Shapes extends Application {




    @Override
    public void start(Stage primaryStage){
        AnchorPane pane=new AnchorPane();

        Rectangle  rectangle=new Rectangle(100,100, 400,220);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);


        Ellipse tyle1=new Ellipse(100,320,80,80);
        tyle1.setFill(null);
        tyle1.setStroke(Color.BLACK);

        Ellipse tyle2=new Ellipse(500,320,80,80);
        tyle2.setFill(null);
        tyle2.setStroke(Color.BLACK);

        pane.getChildren().addAll(rectangle,tyle1,tyle2);


        primaryStage.setTitle("Shapes");
        primaryStage.setScene(new Scene(pane, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
