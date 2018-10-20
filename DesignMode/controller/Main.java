package design_mode;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Screen cartScreen = new Screen(primaryStage, "Movie Basket", "view/basket.fxml");
        Controller bc = new Test(primaryStage);
        cartScreen.display(bc);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
