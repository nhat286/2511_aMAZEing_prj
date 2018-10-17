package view;

import eric.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.MainMenuController;
import application.Screen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class GameplayStage {
	
	private Stage s;
	
	private static ArrayList<String> input;
	private static double refreshTime = 0.016;
	private Map<Integer, Runnable> lv;
	private int maze_lv;
	
	public GameplayStage(Stage s, int lv) {
		this.s = s;
		this.maze_lv = lv;
	}
	
	public void makeLevelHashmap(MazeSystem ms) {
		this.lv = new HashMap<>();
		lv.put(1, () -> ms.level1Initiate());
		lv.put(2, () -> ms.level2Initiate());
		lv.put(3, () -> ms.level3Initiate());
	}
	
	public void start(){
		
		MazeSystem ms = new MazeSystem();
		this.makeLevelHashmap(ms);
		this.lv.get(this.maze_lv).run();
		int mazeSize = ms.getMapSize();
		
		Canvas canvas = new Canvas(mazeSize*32, mazeSize*32);
		
		Parent root = new Pane(canvas);
		Scene playScene = new Scene(root);
		this.s.setScene(playScene);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
		
		Timeline gameLoop = new Timeline();
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
		    				gameLoop.stop();
		    				return;
		    			case QUIT:
		    				System.out.println("Exiting ...");
		    				gameLoop.stop();
		    				Screen menuScreen = new Screen(s, "Main Menu Screen", "view/mainMenu.fxml");
		    				menuScreen.start(new MainMenuController(s));
		    				return;
						default:
							break;
		        	}
		        }
		    });
		
		prepareActionHandlers(playScene);
		
		gameLoop.getKeyFrames().add( kf );
		
		gameLoop.play();
		
		this.s.setMinHeight(s.getHeight());
		this.s.setMinWidth(s.getWidth());
		this.s.show();
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
