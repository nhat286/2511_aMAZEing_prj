package application;

import java.awt.Insets;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import eric.PlaySystem;
import eric.SaveLoad;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SaveScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene ps;
	private Timeline gameLoop;
	private SaveLoad save_feature;
	
	public SaveScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop, SaveLoad save) {
		this.s = s;
		this.ms = ms;
		this.ps = playScene;
		this.gameLoop = gameLoop;
		this.save_feature = save;
	}
	public SaveScreen(Stage s, PlaySystem ms, Scene design, SaveLoad save) {
		this.s = s;
		this.ms = ms;
		this.ps = design;
		this.save_feature = save;
	}
	
	public void start() {
		VBox root = new VBox();
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		Scene saveScene = new Scene(root,ms.getMapSize()*32,ms.getMapSize()*32);
		s.setScene(saveScene);
		Button returnButton;
		if(gameLoop != null) {
			returnButton = new Button("Return to game");
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			        s.setScene(ps);
			        s.show();
			        gameLoop.play();
			    }
			});
		} else {
			returnButton = new Button("Return to design");
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			        s.setScene(ps);
			        s.show();
			    }
			});
		}
		
		String label = "";
		if (this.save_feature.getSavedSlots() > 0)
			label = "Slot 1: Has saved game";
		else
			label = "Slot 1: Empty";
		Button slot1 = new Button(label);
		slot1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	save_feature.saveGame(ms.getMaze(), 0);
		    	start();
		    }
		});
		
		if (this.save_feature.getSavedSlots() > 1)
			label = "Slot 2: Has saved game";
		else
			label = "Slot 2: Empty";
		Button slot2 = new Button(label);
		slot2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	save_feature.saveGame(ms.getMaze(), 1);
		    	start();
		    }
		});
		
		if (this.save_feature.getSavedSlots() > 2)
			label = "Slot 3: Has saved game";
		else
			label = "Slot 3: Empty";
		Button slot3 = new Button(label);
		slot3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	save_feature.saveGame(ms.getMaze(), 2);
		    	start();
		    }
		});
		
		root.getChildren().add( returnButton );
		root.getChildren().add( slot1 );
		root.getChildren().add( slot2 );
		root.getChildren().add( slot3 );
		
		returnButton.setStyle("-fx-font-size: 20;");
		slot1.setStyle("-fx-font-size: 20;");
		slot2.setStyle("-fx-font-size: 20;");
		slot3.setStyle("-fx-font-size: 20;");
		
		s.show();
	}
}
