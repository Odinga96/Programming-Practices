package cs249.finalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TMDBTester extends Application {

	static TMDBMainHandler tmdbHandler;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 tmdbHandler = new TMDBMainHandler();
		//System.out.println(tmdbHandler.getApiKey());
		launch(args);

	}
	@Override
	   public void start(Stage primaryStage) {
			GridPane pane = new GridPane();
	        
	                 
	        String imageSource = tmdbHandler.returnImageUrl("/gAXGlrS9RlNA1sxJqi9C8gVsnUB.jpg");
	        System.out.println(imageSource);
	         
	        ImageView imageView = new ImageView(new Image(imageSource));
	         
	        pane.add(imageView, 0, 0);
	         
	        Scene scene = new Scene(pane, 500, 400);
	   
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

}
