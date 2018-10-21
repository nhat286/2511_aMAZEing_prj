package application;

import eric.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class GameplayStage {
	
	private Stage s;
	
	private static ArrayList<String> input;
	private static double refreshTime = 0.016;
	private Map<Integer, Runnable> lv;
	private int maze_lv;
	private Timeline gameLoop;
	private PlaySystem ms;
	private boolean load_game = false;
	
	public GameplayStage(Stage s) {
		this.s = s;
	}
	
	public GameplayStage(Stage s, PlaySystem ms) {
		this.s = s;
		this.ms = ms;
	}
	
	public void setLevel(int lv) {
		this.maze_lv = lv;
		//this.makeLevelHashmap(ms);
		//this.lv.get(this.maze_lv).run();
	}
	
	public void loadGame(PlaySystem p) {
		this.ms = p;
		this.load_game = true;
	}
	
	public void makeLevelHashmap(PlaySystem ms) {
		this.lv = new HashMap<>();
		lv.put(1, () -> ms.level1Initiate());
		lv.put(2, () -> ms.level2Initiate());
		lv.put(3, () -> ms.level3Initiate());
	}
	
	public void start(){
		
		if (!this.load_game&&this.ms==null) {
			this.ms = new PlaySystem();
			this.makeLevelHashmap(ms);
			this.lv.get(this.maze_lv).run();
		}
		int mazeSize = ms.getMapSize();
		
		Button menuButton = new Button("Menu");
		menuButton.setDefaultButton(false);
		
		Canvas canvas = new Canvas(mazeSize*32, mazeSize*32);
		VBox die_prompt = getDiePrompt();
		VBox win_prompt = getWinPrompt();
		
		Pane root = new Pane(canvas, menuButton, die_prompt, win_prompt);
		Scene playScene = new Scene(root, mazeSize*32, mazeSize*32);
		this.s.setScene(playScene);
		
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    	gameLoop.stop();
		        m.start();
		    }
		});
		//menuButton.setStyle("-fx-background-color: transparent;");
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
		        "-fx-background-position: center center; " +
		        "-fx-background-repeat: repeat;");
		
		this.gameLoop = new Timeline();
		this.gameLoop.setCycleCount( Timeline.INDEFINITE );
		
		KeyFrame kf = new KeyFrame(
		    Duration.seconds(refreshTime),
		    new EventHandler<ActionEvent>()
		    {
		        public void handle(ActionEvent ae)
		        {
		        	switch (ms.gameLoop(input, gc, refreshTime)) {
		    			case LOSE:
		            		System.out.println("Oh no you died. Restarting...");
		            		gameLoop.stop();
		            		die_prompt.setVisible(true);
		            		break;
		    			case WIN:
		    				System.out.println("Yay you win!!!");
		    				gameLoop.stop();
		    				win_prompt.setVisible(true);
		    				return;
		    			case QUIT:
		    				System.out.println("Exiting ...");
		    				gameLoop.stop();
		    				homeMenu(s);
		    				return;
		    			case PAUSE:
		    				MenuScreen m = new MenuScreen(s,ms,playScene,gameLoop);
		    				gameLoop.stop();
		    		        m.start();
		    			default:
							break;
		        	}
		        }
		    });
		
		prepareActionHandlers(playScene);
		
		this.gameLoop.getKeyFrames().add( kf );
		
		this.gameLoop.play();
		
		//this.s.setMinHeight(s.getHeight());
		//this.s.setMinWidth(s.getWidth());
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
	
	private VBox getDiePrompt() {
		VBox die_prompt = new VBox();
		die_prompt.setAlignment(Pos.CENTER);
		die_prompt.setSpacing(20);
		
		Button quitButton = new Button("Back to Home Menu");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	homeMenu(s);
		    }
		});
		Button restartButton = new Button("Restart Level");
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	die_prompt.setVisible(false);
		    	lv.get(maze_lv).run();
		    	start();
		    }
		});
		die_prompt.getChildren().add(quitButton);
		die_prompt.getChildren().add(restartButton);
		
		quitButton.setStyle("-fx-font-size: 20;");
		restartButton.setStyle("-fx-font-size: 20;");
		
		die_prompt.setVisible(false);
		
		return die_prompt;
	}
	
	private VBox getWinPrompt() {
		VBox win_prompt = new VBox();
		win_prompt.setAlignment(Pos.CENTER);
		win_prompt.setSpacing(20);
		
		Button quitButton = new Button("Back to Home Menu");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	homeMenu(s);
		    }
		});
		Button nextButton = new Button("Next Level");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	win_prompt.setVisible(false);
		    	maze_lv++;
		    	start();
		    }
		});
		win_prompt.getChildren().add(quitButton);
		win_prompt.getChildren().add(nextButton);
		
		quitButton.setStyle("-fx-font-size: 20;");
		nextButton.setStyle("-fx-font-size: 20;");
		
		win_prompt.setVisible(false);
		
		return win_prompt;
	}
	
	private static void homeMenu(Stage s) {
		Screen menuScreen = new Screen(s, "Main Menu Screen", "view/mainMenu.fxml");
		menuScreen.start(new MainMenuController(s));
	}
}
