package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import view.GameplayStage;	this is causing an error but should be used later
import javafx.event.EventHandler;

public class levelScreenController {
	
	 Stage s;
	 
	 @FXML
	 private Label Level_Selection;
	 
	 @FXML
	 private Button levelOneButton;
	 
	 @FXML
	 private Button levelTwoButton;
	 
	 @FXML
	 private Button levelThreeButton;
	 
	 
	public levelScreenController(Stage s) {
		this.s = s;
	}
	
	@FXML
    public void handleLevelOne() {
		System.out.println("go to level one");
		
    }
	
	@FXML
    public void handleLevelTwo() {
        System.out.println("go to level two");
    }
	
	@FXML
    public void handleLevelThree() {
		System.out.println("go to level 3");
    }
}
