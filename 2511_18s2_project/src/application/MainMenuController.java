package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class MainMenuController extends Controller{
	
	@FXML
	private Label Dungeon_Master;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button loadButton;
	
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
		Screen levelScreen = new Screen(this.getS(), "Level Screen", "view/levelScreen.fxml");
		LevelScreenController levelController = new LevelScreenController(this.getS());
		levelScreen.start(levelController);
    }
	
	@FXML
    public void handleLoadButton() {
        Screen loadScreen = new Screen(this.getS(), "Load Screen", "view/loadSlots.fxml");
        LoadScreenController loadController = new LoadScreenController(this.getS());
        loadScreen.start(loadController);
    }
	
	@FXML
    public void handleDesignButton() {
		DesignScreen ds = new DesignScreen();
		ds.start(this.getS());
    }
}
