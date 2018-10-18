package application;

import eric.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;


public class GameplayStage {
	
	private Stage s;
	
	private static ArrayList<String> input;
	private double refreshTime;
	private Map<Integer, Runnable> lv;
	private int maze_lv;
	private Timeline gameLoop;
	
	public GameplayStage(Stage s, double t, int lv) {
		this.s = s;
		this.refreshTime = t;
		this.maze_lv = lv;
	}
	
	public void makeLevelHashmap(MazeSystem ms) {
		this.lv = new HashMap<>();
		lv.put(1, () -> ms.level1Initiate());
		lv.put(2, () -> ms.level2Initiate());
		lv.put(3, () -> ms.level3Initiate());
	}
	
	public void start() {
		Pane root = new Pane();
		Scene playScene = new Scene(root);
		s.setScene(playScene);
		
		MazeSystem ms = new MazeSystem();
		this.makeLevelHashmap(ms);
		this.lv.get(this.maze_lv).run();
		int mazeSize = ms.getMapSize();
		
		Canvas canvas = new Canvas(mazeSize*32, mazeSize*32);
		Button menuButton = new Button("Menu");
		menuButton.setDefaultButton(false);
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	gameLoop.stop();
		        m.start();
		    }
		});
		//menuButton.setStyle("-fx-background-color: transparent;");
		
		root.getChildren().add( canvas );
		root.getChildren().add( menuButton );
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
		
		gameLoop = new Timeline();
		gameLoop.setCycleCount( Timeline.INDEFINITE );
		
		KeyFrame kf = new KeyFrame(
		    Duration.seconds(refreshTime),
		    new EventHandler<ActionEvent>()
		    {
		        public void handle(ActionEvent ae)
		        {
		        	switch (ms.gameLoop(input, gc, refreshTime)) {
		    			case LOSE:
		            		System.out.println("Oh no you died. Restarting...");
		    				lv.get(maze_lv).run();
		            		break;
		    			case WIN:
		    				System.out.println("Yay you win!!!");
		    				return;
		    			case QUIT:
		    				System.out.println("Exiting ...");
		    				return;
						default:
							break;
		        	}
		        }
		    });
		
		prepareActionHandlers(playScene);
		
		gameLoop.getKeyFrames().add( kf );
		
		gameLoop.play();
		
		s.show();
		s.setMinHeight(s.getHeight());
		s.setMinWidth(s.getWidth());
	}
	
	private static void prepareActionHandlers(Scene playScene)
    {
        // use a set so duplicates are not possible
		input = new ArrayList<String>();
        playScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
            	String keyInput = event.getCode().toString();
            	if(!input.contains(keyInput))
            		input.add(keyInput);
            }
        });
        playScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
            	String keyInput = event.getCode().toString();
                input.remove(keyInput);
            }
        });
    }
	
	public Stage getStage() {
		return this.s;
	}

}

