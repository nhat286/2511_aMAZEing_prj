package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Screen menuScreen = new Screen(primaryStage, "Main Menu Screen", "view/mainMenu.fxml");
		MainMenuController mainMenuController = new MainMenuController(menuScreen.getStage());
		menuScreen.start(mainMenuController);	//this connects the screen with its controller
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
