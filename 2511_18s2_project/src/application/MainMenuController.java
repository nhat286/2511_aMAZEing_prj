package application;

import eric.PlaySystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kyle_maze.SaveLoad;
import javafx.event.EventHandler;

public class MainMenuController extends Controller{
	
	private SaveLoad load;
	
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
		this.load = new SaveLoad();
	}
	
	@FXML
    public void handleStartButton() {
		System.out.println("clicked start");
		GameplayStage gs = new GameplayStage(this.getS());
		gs.setLevel(1);
		gs.start();
    }
	
	@FXML
    public void handleLoadButton() {
        System.out.println("clicked load");
        PlaySystem ps = new PlaySystem();
        ps.setMaze(load.loadGame(0));
        GameplayStage gs = new GameplayStage(this.getS());
        gs.loadGame(ps);
        gs.start();
    }
	
	@FXML
    public void handleDesignButton() {
		System.out.println("clicked design");
    }
}
