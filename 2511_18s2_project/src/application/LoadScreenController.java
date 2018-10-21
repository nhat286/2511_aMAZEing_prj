package application;

import eric.SaveLoad;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoadScreenController extends Controller {

	private int saved_slots;
	
	@FXML
	private Button back;
	@FXML
	private Button slot1;
	@FXML
	private Button slot2;
	@FXML
	private Button slot3;
	
	public LoadScreenController(Stage s) {
		super(s);
		this.saved_slots = (new SaveLoad()).getSavedSlots();
	}
	
	public void initialize() {
		switch (this.saved_slots) {
		case 0:
			this.slot1.setText("Slot 1: Empty");
			this.slot2.setText("Slot 2: Empty");
			this.slot3.setText("Slot 3: Empty");
			break;
		case 1:
			this.slot1.setText("Slot 1: Has saved game");
			this.slot2.setText("Slot 2: Empty");
			this.slot3.setText("Slot 3: Empty");
			break;
		case 2:
			this.slot1.setText("Slot 1: Has saved game");
			this.slot2.setText("Slot 2: Has saved game");
			this.slot3.setText("Slot 3: Empty");
			break;
		case 3:
			this.slot1.setText("Slot 1: Has saved game");
			this.slot2.setText("Slot 2: Has saved game");
			this.slot3.setText("Slot 3: Has saved game");
			break;
		}
	}
	
	@FXML
    public void handleBack() {
		Screen menuScreen = new Screen(this.getS(), "Main Menu Screen", "view/mainMenu.fxml");
		MainMenuController mainMenuController = new MainMenuController(menuScreen.getStage());
		menuScreen.start(mainMenuController);
    }
	
	@FXML
    public void handleSlotOne() {
		if (this.saved_slots > 0) {
			GameplayStage gs = new GameplayStage(this.getS());
	        gs.loadGame(0);
	        gs.start();
		}
    }
	
	@FXML
    public void handleSlotTwo() {
		if (this.saved_slots > 1) {
			GameplayStage gs = new GameplayStage(this.getS());
	        gs.loadGame(1);
	        gs.start();
		}
    }
	
	@FXML
    public void handleSlotThree() {
		if (this.saved_slots > 0) {
			GameplayStage gs = new GameplayStage(this.getS());
	        gs.loadGame(2);
	        gs.start();
		}
    }

}
