package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import view.GameplayStage;	this is causing an error but should be used later
import javafx.event.EventHandler;

public class LevelScreenController extends Controller {
	 
	@FXML
	private Button back; 
	
	@FXML
	private Label Level_Selection;
	 
	@FXML
	private Button levelOneButton;
	 
	@FXML
	private Button levelTwoButton;
	 
	@FXML
	private Button levelThreeButton;
	 
	 
	public LevelScreenController(Stage s) {
		super(s);
	}
	
	@FXML
    public void handleBack() {
		Screen menuScreen = new Screen(this.getS(), "Main Menu Screen", "view/mainMenu.fxml");
		MainMenuController mainMenuController = new MainMenuController(menuScreen.getStage());
		menuScreen.start(mainMenuController);
    }
	
	@FXML
    public void handleLevelOne() {
		System.out.println("go to level one");
		GameplayStage gs = new GameplayStage(this.getS());
		gs.setLevel(1);
		gs.start();
		
    }
	
	@FXML
    public void handleLevelTwo() {
        System.out.println("go to level two");
        GameplayStage gs = new GameplayStage(this.getS());
		gs.setLevel(2);
		gs.start();
    }
	
	@FXML
    public void handleLevelThree() {
		System.out.println("go to level three");
		GameplayStage gs = new GameplayStage(this.getS());
		gs.setLevel(3);
		gs.start();
    }
}
