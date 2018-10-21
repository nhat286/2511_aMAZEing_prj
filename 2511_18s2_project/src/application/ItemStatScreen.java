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

public class ItemStatScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene playScene;
	private Timeline gameLoop;
	private Scene itemStatScene;
	private String[] ItemDescript;
	private GridPane root;
	
	public ItemStatScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop) {
		this.s = s;
		this.ms = ms;
		this.playScene = playScene;
		this.gameLoop = gameLoop;
		ItemDescript = new String [7];
		ItemDescript[0] = "Hover Potion: "
				+ "This potion could make you hovering until you are dead or you win the game."
				+ "It is useful to hover through pits.";
		ItemDescript[1] = "Invincible Potion: "
				+ "This potion could make you invincible and destroy any enemies upon collision. "
				+ "Although enemies are tend to run away from you. "
				+ "The effect of the invincible potion is limited to 10 secs.";
		ItemDescript[2] = "Bomb: "
				+ "The bomb could be lit up and drop to the ground."
				+ "After a period of time it will explode and destruct everything nearby.";
		ItemDescript[3] = "Key: "
				+ "There would be up to 3 keys in any maze to be found, each key belongs to a specific door."
				+ "Once the key has been used to sucessfully open a door, it would be destroyed.";
		ItemDescript[4] = "Treasure: "
				+ "There might be Treasures in some mazes and collecting them all might leads to win.";
		ItemDescript[5] = "Sword: "
				+ "Could be used to fight close ranged enemy.";
		ItemDescript[6] = "Arrow: "
				+ "Could be used to fight ranged enemy.";
	}
	
	public void start() {
		root = new GridPane();
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		root.getColumnConstraints().addAll(column1);
		
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		root.getColumnConstraints().addAll(column2);
		
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(10);
		root.getColumnConstraints().addAll(column3);
		
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(50);
		root.getColumnConstraints().addAll(column4);
			 
		for(int j = 0;j<9;j++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100/9);
			root.getRowConstraints().addAll(row);
		}
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");

		itemStatScene = new Scene(root,1000, 600);
		s.setScene(itemStatScene);

		Button returnButton = new Button("Return to menu");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	m.start();
		    }
		});

		HashMap<String, Integer> potionHash = ms.getMaze().potionStat();
		HashMap<String, Integer> weaponHash = ms.getMaze().weaponStat();
		
		int currList = 1;
		ImageView i;
		Label l;
		//Add description
		l = new Label("The followed items might be found in the maze to assist you: ");
		l.setStyle("-fx-font-size: 18;");
		l.setWrapText(true);
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,0,0,4,1 );
		GridPane.setHalignment(l, HPos.CENTER);
		
		System.out.println("The followed items might be found in the maze to assist you:");
		for (Entry<String, Integer> e : potionHash.entrySet()) {
			switch(e.getKey().toString()) {
			case "HoverPotion":
				addItemLabel(currList, e, "HoverPotion.png", 0);
				currList++;
				break;
			case "InvincibilityPotion":
				addItemLabel(currList, e, "InvincibilityPotion.png", 1);
				currList++;
				break;
			default:
				break;
			}
		}
		
		for (Entry<String, Integer> e : weaponHash.entrySet()) {
			switch(e.getKey().toString()) {
			case "Sword":
				addItemLabel(currList, e, "Sword.png", 5);
				currList++;
				break;
			case "Arrow":
				addItemLabel(currList, e, "Arrow.png", 6);
				currList++;
				break;
			case "Bomb":
				addItemLabel(currList, e, "Bomb.png", 2);
				currList++;
				break;
			default:
				break;
			}
		}
		
		//Add keys
		if(!ms.getMaze().getKeys().isEmpty()) {
			addItemLabel(currList, "Key", ms.getMaze().getKeys().size(),  "Key.png", 3);
			currList++;
		}
		
		//Add Treasures
		if(!ms.getMaze().getLoots().isEmpty()) {
			addItemLabel(currList, "Treasure", ms.getMaze().getLoots().size(),  "Treasure.png", 4);
			currList++;
		}
		
		root.add(returnButton,0,8,4,1);	
		returnButton.setStyle("-fx-font-size: 20;");
		GridPane.setHalignment(returnButton, HPos.CENTER);
		s.show();
	}
	
	private void addItemLabel(int currList, Entry<String, Integer> e,String image, int index) {
		Label l;
		ImageView i;
		//Add label
		l = new Label(e.getKey().toString());
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 20;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,0,currList );
		//Add image
		i = new ImageView(image);
		root.add(i, 1, currList);
		//Add number
		l = new Label(e.getValue().toString());
		l.setStyle("-fx-font-size: 20;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,2,currList );
		//Add Description
		l = new Label(ItemDescript[index]);
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 14;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,3,currList );
	}
	
	private void addItemLabel(int currList,String name, Integer number,String image, int index) {
		Label l;
		ImageView i;
		//Add label
		l = new Label(name);
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 20;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,0,currList );
		//Add image
		i = new ImageView(image);
		root.add(i, 1, currList);
		//Add number
		l = new Label(number.toString());
		l.setStyle("-fx-font-size: 20;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,2,currList );
		//Add Description
		l = new Label(ItemDescript[index]);
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 14;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,3,currList );
	}
}

