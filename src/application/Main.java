package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		// set the stage height to be 400
        //primaryStage.setHeight(400);
        // set the stage width to be 600
        //primaryStage.setWidth(600);
        
        //BasicScreen bs = new BasicScreen(primaryStage, "Video Rental", "application/home.fxml");
        //bs.start(new HomeController(bs.getStage()));
		GameplayStage gs = new GameplayStage(primaryStage, 0.016, 1);
		gs.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
