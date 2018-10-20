package design_mode;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Screen cartScreen = new Screen(primaryStage, "Design Maze", "view/basket.fxml");
        Controller bc = new Test(primaryStage);
        cartScreen.display(bc);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
