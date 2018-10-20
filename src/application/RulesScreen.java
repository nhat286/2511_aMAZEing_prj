package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import eric.PlaySystem;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import niriksha.Inventory;
import niriksha.Potion;
import niriksha.Weapon;
import niriksha.Character;

public class RulesScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene playScene;
	private Timeline gameLoop;
	private Scene rulesScene;
	private String[] rules;
	private GridPane root;
	private int goal;
	
	public RulesScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop) {
		this.s = s;
		this.ms = ms;
		this.playScene = playScene;
		this.gameLoop = gameLoop;
		rules = new String [5];
		rules[0] = "Find the exit door.";
		rules[1] = "Collect all treasures.";
		rules[2] = "Kill all enemies.";
		rules[3] = "Find keys to open locked doors.";
		rules[4] = "Solve the puzzles.";
		this.goal = ms.getMaze().getWinCond();
	}
	
	public void start() {
		root = new GridPane();
				 
		for(int j = 0;j<6;j++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100/6);
			root.getRowConstraints().addAll(row);
		}
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");

		rulesScene = new Scene(root,ms.getMapSize()*32, ms.getMapSize()*32);
		s.setScene(rulesScene);

		Button returnButton = new Button("Return to menu");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	m.start();
		    }
		});
		
		Label l;
		//Add description
		l = new Label("To win this game, you need to: ");
		l.setStyle("-fx-font-size: 18;");
		l.setWrapText(true);
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,0,0,4,1 );
		GridPane.setHalignment(l, HPos.CENTER);
		
		int currList = 1;
		
		if ((goal & 0b00001) !=0)
			addRule(0,currList++);
		if ((goal & 0b00010) !=0)
			addRule(1,currList++);
		if ((goal & 0b00100) !=0)
			addRule(2,currList++);
		if ((goal & 0b01000) !=0)
			addRule(3,currList++);
		if ((goal & 0b10000) !=0)
			addRule(4,currList++);
		
		

		root.add(returnButton,0,5,1,1);	
		returnButton.setStyle("-fx-font-size: 20;");
		GridPane.setHalignment(returnButton, HPos.CENTER);
		s.show();
	}
	
	private void addRule(int index, int currList) {
		Label l;
		//Add label
		l = new Label(rules[index]);
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 14;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,0,currList);
	}
	
}

