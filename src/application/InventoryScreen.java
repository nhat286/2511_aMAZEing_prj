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

public class InventoryScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene playScene;
	private Timeline gameLoop;
	ArrayList<Button> dropButtonList;
	ArrayList<Button> useButtonList;
	private Scene inventoryScene;
	
	public InventoryScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop) {
		this.s = s;
		this.ms = ms;
		this.playScene = playScene;
		this.gameLoop = gameLoop;
	}
	
	public void start() {
		GridPane root = new GridPane();
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(40);
		root.getColumnConstraints().addAll(column1);
		
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		root.getColumnConstraints().addAll(column2);
		
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(10);
		root.getColumnConstraints().addAll(column3);
		
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(15);
		root.getColumnConstraints().addAll(column4);
		
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(15);
		root.getColumnConstraints().addAll(column5);
		
		for(int j = 0;j<6;j++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100/6);
			root.getRowConstraints().addAll(row);
		}
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
//		root.setAlignment(Pos.CENTER);
//		root.setPadding(new Insets(10,10,10,10));
//		root.setSpacing(20);
		inventoryScene = new Scene(root,ms.getMapSize()*32, ms.getMapSize()*32);
		s.setScene(inventoryScene);
		//FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		//double buttonWidth;
		
		//addInventoryInfo(root);
		
		Button returnButton = new Button("Return to menu");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	m.start();
		    }
		});

		Character character = ms.getUser();
		Inventory inventory = character.getBag();

		ArrayList<Weapon> weaponList = inventory.getWeaponList();
		ArrayList<Potion> potionList = inventory.getPotionList();
		ArrayList<String> currList = new ArrayList<String>();
		
		HashMap<String, Integer> weaponHash = new HashMap<>();
		HashMap<String, Integer> potionHash = new HashMap<>();
		
		Integer i;
		
		for (Weapon o : weaponList) {
			if (!weaponHash.containsKey(o.getType())) {
				weaponHash.put(o.getType(), new Integer(0));
			}
			weaponHash.put(o.getType(), new Integer(weaponHash.get(o.getType()).intValue() + 1));
		}
		
		for (Potion o : potionList) {
			if (!potionHash.containsKey(o.getType())) {
				potionHash.put(o.getType(), new Integer(0));
			}
			potionHash.put(o.getType(), new Integer(potionHash.get(o.getType()).intValue() + 1));
		}
		
		dropButtonList = new ArrayList<Button>();
		useButtonList = new ArrayList<Button>();
		
		i = 0;
		//System.out.println("------------------------------------------------------------");
		//System.out.println("You have Weapons: ");
		for (Entry<String, Integer> e : weaponHash.entrySet()) {			
			//Add a label of item
			Label l = new Label(e.getKey().toString());
			l.setStyle("-fx-font-size: 20;");
			l.setTextFill(Color.rgb(255, 255, 255));
			root.add( l,0,currList.size() );	
			
			//Add image of item
			String str = e.getKey().toString() + ".png";
			ImageView iv = new ImageView(str);
			root.add( iv,1,currList.size() );	
			
			//Add quantity of item
			Label q = new Label(e.getValue().toString());
			q.setStyle("-fx-font-size: 20;");
			q.setTextFill(Color.rgb(255, 255, 255));
			root.add( q,2,currList.size() );
			
			//Add buttons for use and drop item
			Button bu = new Button("Equip");
			bu.setStyle("-fx-font-size: 10;");
			root.add( bu,3,currList.size() );
			bu.setUserData(e.getKey().toString());
			bu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
					for(Weapon w : weaponList) {
						if(w.getType().equals(bu.getUserData())) {
								character.equipWeapon(w);
								System.out.println("in equip");
							break;
						}
					}
			    }
			});
			useButtonList.add(bu);
			
			Button bd = new Button("Drop");
			bd.setStyle("-fx-font-size: 10;");
			root.add( bd,4,currList.size() );
			bd.setUserData(e.getKey().toString());
			bd.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
					for(Weapon w : weaponList) {
						if(w.getType().equals(bd.getUserData())) {
							w.destroyWeapon();
							character.getBag().deleteWeapon(w);
							System.out.println("in drop");
							InventoryScreen inv = new InventoryScreen(s, ms, playScene, gameLoop);
							inv.start();
							break;
						}
					}
			    }
			});
			dropButtonList.add(bd);
			
			currList.add((String) e.getKey());
			i++;
		}
		
		
		
		//System.out.println("------------------------------------------------------------");
		//System.out.println("You have Potions: ");
		for (Entry<String, Integer> e : potionHash.entrySet()) {
			//Add a label of item
			Label l = new Label(e.getKey().toString());
			l.setStyle("-fx-font-size: 20;");
			l.setTextFill(Color.rgb(255, 255, 255));
			root.add( l,0,currList.size() );	
			
			//Add image of item
			String str = e.getKey().toString() + ".png";
			ImageView iv = new ImageView(str);
			root.add( iv,1,currList.size() );	
			
			//Add quantity of item
			Label q = new Label(e.getValue().toString());
			q.setStyle("-fx-font-size: 20;");
			q.setTextFill(Color.rgb(255, 255, 255));
			root.add( q,2,currList.size() );
			
			//Add buttons for use and drop item
			Button bu = new Button("Use");
			bu.setStyle("-fx-font-size: 10;");
			root.add( bu,3,currList.size() );
			bu.setUserData(e.getKey().toString());
			bu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
					for(Potion p : potionList) {
						if(p.getType().equals(bu.getUserData())) {
							character.equipPotion(p);
							inventory.deletePotion(p);
							System.out.println("in use");
							InventoryScreen inv = new InventoryScreen(s, ms, playScene, gameLoop);
							inv.start();
							break;
						}
					}
			    }
			});
			useButtonList.add(bu);
			
			Button bd = new Button("Drop");
			bd.setStyle("-fx-font-size: 10;");
			root.add( bd,4,currList.size() );
			bd.setUserData(e.getKey().toString());
			bd.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
					for(Potion p : potionList) {
						if(p.getType().equals(bd.getUserData())) {
								character.getBag().deletePotion(p);
								System.out.println("in drop");
								InventoryScreen inv = new InventoryScreen(s, ms, playScene, gameLoop);
								inv.start();
							break;
						}
					}
			    }
			});
			dropButtonList.add(bd);
			
			currList.add((String) e.getKey());
			i++;
		}
		
		root.add(returnButton,0,5,5,1);	
		GridPane.setHalignment(returnButton, HPos.CENTER);
		returnButton.setStyle("-fx-font-size: 20;");
		
		s.show();
		
	}
	
	
}
