package application;

import eric.*;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class GameplayStage {
	
	private Stage s;
	
	private static ArrayList<String> input;
	//static Scene playScene;
	//private static GraphicsContext gc;
	private double refreshTime;
	
	public GameplayStage(Stage s, double t) {
		this.s = s;
		this.refreshTime = t;
	}
	
	public void start() {
		Pane root = new Pane();
		Scene playScene = new Scene(root);
		s.setScene(playScene);
		
		MazeSystem ms = new MazeSystem();
		//ms.level1Initiate();
		ms.level2Initiate();
		//ms.leve3Initiate();
		int mazeSize = ms.getMapSize();
		
		Canvas canvas = new Canvas(mazeSize*32, mazeSize*32);
		root.getChildren().add( canvas );
		
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
		    				//ms.level1Initiate();
		            		ms.level2Initiate();
		    				//ms.leve3Initiate();
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
