package application;

import java.util.HashMap;
import java.util.Map.Entry;

import eric.PlaySystem;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EnemyStatsScreen {
	private Stage s;
	private PlaySystem ms;
	private Scene playScene;
	private Timeline gameLoop;
	private Scene enemyStatsScene;
	private String[] EnemyDescript;
	private GridPane root;
	
	public EnemyStatsScreen(Stage s, PlaySystem ms, Scene playScene, Timeline gameLoop) {
		this.s = s;
		this.ms = ms;
		this.playScene = playScene;
		this.gameLoop = gameLoop;
		EnemyDescript = new String [4];
		EnemyDescript[0] = "Hunter: "
				+ "The hunter constantly moves toward you,"
				+ " stopping if it cannot move any closer. ";
		EnemyDescript[1] = "Hound: "
				+ "The hound will assist the hunter to catch you "
				+ "by besieging you with the hunter on the opposite side of you.";
		EnemyDescript[2] = "Coward: "
				+ "The coward behaves like the hunter when far away from you,"
				+ " but runs away when you are close to it.";
		EnemyDescript[3] = "Strategist: "
				+ "The Strategist could read your mind "
				+ "and move towards where you want to go.";
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
			 
		for(int j = 0;j<6;j++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(100/6);
			root.getRowConstraints().addAll(row);
		}
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");

		enemyStatsScene = new Scene(root,ms.getMapSize()*32, ms.getMapSize()*32);
		s.setScene(enemyStatsScene);

		Button returnButton = new Button("Return to menu");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	m.start();
		    }
		});

		HashMap<String, Integer> enemyHash = ms.getMaze().enemyStat();
		if (enemyHash.isEmpty()) {
			Label l = new Label("Any collision with enemies would cause death. "
					+ "There is no enemy in this current maze.");
			l.setStyle("-fx-font-size: 18;");
			l.setWrapText(true);
			l.setTextFill(Color.rgb(255, 255, 255));
			root.add( l,0,0,4,1 );
			GridPane.setHalignment(l, HPos.CENTER);
		} else {
			
			int currList = 1;
			Label l;
			//Add description
			l = new Label("Any collision with enemies would cause death. "
					+ "There are these types of enemy in current maze: ");
			l.setStyle("-fx-font-size: 18;");
			l.setWrapText(true);
			l.setTextFill(Color.rgb(255, 255, 255));
			root.add( l,0,0,4,1 );
			GridPane.setHalignment(l, HPos.CENTER);
			
			for (Entry<String, Integer> e : enemyHash.entrySet()) {
				switch(e.getKey().toString()) {
				case "Hunter":
					addEnemyLabel(currList, e, "hunter.png", 0);
					currList++;
					break;
				case "Hound":
					addEnemyLabel(currList, e, "hound.png", 1);
					currList++;
					break;
				case "Coward":
					addEnemyLabel(currList, e, "coward.png", 2);
					currList++;
					break;
				case "Strategist":
					addEnemyLabel(currList, e, "strategist.png", 3);
					currList++;
					break;
				default:
					break;
				}
			}
		}
		
		root.add(returnButton,0,5,4,1);	
		returnButton.setStyle("-fx-font-size: 20;");
		GridPane.setHalignment(returnButton, HPos.CENTER);
		s.show();
	}
	
	private void addEnemyLabel(int currList, Entry<String, Integer> e,String image, int index) {
		Label l;
		ImageView i;
		//Add label
		l = new Label(e.getKey().toString());
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
		l = new Label(EnemyDescript[index]);
		l.setWrapText(true);
		l.setStyle("-fx-font-size: 14;");
		l.setTextFill(Color.rgb(255, 255, 255));
		root.add( l,3,currList );
	}
	
}

