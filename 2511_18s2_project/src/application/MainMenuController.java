package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.GameplayStage;
import javafx.event.EventHandler;

public class MainMenuController extends Controller{

	 @FXML
	 private Label Dungeon_Master;
	 
	 @FXML
	 private Button startButton;
	 
	 @FXML
	 private Button quitButton;
	 
	 @FXML
	 private Button designButton;
	 
	 
	
	public MainMenuController(Stage s) {
		super(s);
		
	}
	
	//not sure if this is needed yet
	public void initialize() {
		//THIS IS WHERE WE SHOULD INITIALIZE THE GAME
		System.out.println("initialize");
	}
	
	@FXML
    public void handleStartButton() {
		System.out.println("clicked start");
		GameplayStage gs = new GameplayStage(this.getS(), 1);
		gs.start();
    }
	
	@FXML
    public void handleQuitButton() {
        System.out.println("clicked quit");
    }
	
	@FXML
    public void handleDesignButton() {
		System.out.println("clicked design");
    }
}
