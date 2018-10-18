package application;

import java.awt.Insets;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import eric.MazeSystem;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen {
	private Stage s;
	private MazeSystem ms;
	private Scene ps;
	private Timeline gameLoop;
	
	public MenuScreen(Stage s, MazeSystem ms, Scene playScene, Timeline gameLoop) {
		this.s = s;
		this.ms = ms;
		this.ps = playScene;
		this.gameLoop = gameLoop;
	}
	
	public void start() {
		VBox root = new VBox();
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
		root.setAlignment(Pos.CENTER);
		//root.setPadding(new Insets(10,10,10,10));;
		root.setSpacing(20);
		Scene menuScene = new Scene(root,ms.getMapSize()*32,ms.getMapSize()*32);
		s.setScene(menuScene);
		//FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		//double buttonWidth;
		
		Button returnButton = new Button("Return to game");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        s.setScene(ps);
		        s.show();
		        gameLoop.play();
		    }
		});
		
		
		Button inventoryButton = new Button("Inventory");
		inventoryButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	InventoryScreen inv = new InventoryScreen(s, ms, ps, gameLoop);
		        inv.start();
		    }
		});
		
		Button enemyButton = new Button("Enemy Status");
		enemyButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		       s.setScene(ps);
		       s.show();
		    }
		});
		
		Button itemButton = new Button("Item Status");
		itemButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		       s.setScene(ps);
		       s.show();
		    }
		});
		
		Button rulesButton = new Button("Rules");
		rulesButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		       s.setScene(ps);
		       s.show();
		    }
		});
		
		
		root.getChildren().add( returnButton );
		root.getChildren().add( inventoryButton );
		root.getChildren().add( enemyButton );
		root.getChildren().add( itemButton );
		root.getChildren().add( rulesButton );
		
		returnButton.setStyle("-fx-font-size: 20;");
		inventoryButton.setStyle("-fx-font-size: 20;");
		enemyButton.setStyle("-fx-font-size: 20;");
		itemButton.setStyle("-fx-font-size: 20;");
		rulesButton.setStyle("-fx-font-size: 20;");
		
		
		s.show();
	}
}
