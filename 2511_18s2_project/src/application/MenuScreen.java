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

public class MenuScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene ps;
	private Timeline gameLoop;
	
	public MenuScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop) {
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
		s.setMinHeight(600);
		s.setMinWidth(600);
		s.setScene(menuScene);
		//FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		//double buttonWidth;
		
		SaveLoad sl = new SaveLoad();
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
		       EnemyStatsScreen es = new EnemyStatsScreen(s, ms, ps, gameLoop);
		       es.start();
		    }
		});
		
		Button itemButton = new Button("Item Status");
		itemButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	ItemStatScreen is = new ItemStatScreen(s, ms, ps, gameLoop);
			    is.start();
		    }
		});
		
		Button rulesButton = new Button("Rules");
		rulesButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	RulesScreen rs = new RulesScreen(s, ms, ps, gameLoop);
			    rs.start();
		    }
		});
		
		Button saveButton = new Button("Save Current Level");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		       SaveScreen ss = new SaveScreen(s, ms, ps, gameLoop, sl);
		       ss.start();
		    }
		});
		
		Button quitButton = new Button("Back to Home Menu");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Screen menuScreen = new Screen(s, "Main Menu Screen", "view/mainMenu.fxml");
				menuScreen.start(new MainMenuController(s));
		    }
		});
		
		
		root.getChildren().add( returnButton );
		root.getChildren().add( inventoryButton );
		root.getChildren().add( enemyButton );
		root.getChildren().add( itemButton );
		root.getChildren().add( rulesButton );
		root.getChildren().add( saveButton );
		root.getChildren().add( quitButton );
		
		returnButton.setStyle("-fx-font-size: 20;");
		inventoryButton.setStyle("-fx-font-size: 20;");
		enemyButton.setStyle("-fx-font-size: 20;");
		itemButton.setStyle("-fx-font-size: 20;");
		rulesButton.setStyle("-fx-font-size: 20;");
		saveButton.setStyle("-fx-font-size: 20;");
		quitButton.setStyle("-fx-font-size: 20;");
		
		
		s.show();
	}
}
