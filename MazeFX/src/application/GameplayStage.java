package application;
	
import java.util.ArrayList;
import gameBackend.*;
import gameBackend.Character;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;


public class GameplayStage extends Application {
	
	static ArrayList<String> input;
	static Scene playScene;
	static GraphicsContext gc;
	
	@Override
	public void start(Stage playStage) {
		Pane root = new Pane();
        playScene = new Scene(root);
        playStage.setScene(playScene);
        
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        
        gc = canvas.getGraphicsContext2D();
        
        Character player = new Character(1, 1, 100);
        Sprite s = new Sprite();
        s.setImage(new Image( "human_new.png" ));
        s.setPosition(0,0);
        player.setSprite(s);
        
        root.setStyle("-fx-background-image: url(\"dirt_0_new.png\"); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: repeat;");
        
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
//        final long timeStart = System.currentTimeMillis();
        long lastNanoTime = System.nanoTime();
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.016),
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    //double t = (System.nanoTime() - lastNanoTime) / 1000000000.0; 

                	// Clear the canvas
                    gc.clearRect(0, 0, 512,512);
                    
                    player.getSprite().setVelocity(0,0);
                    if (input.contains("LEFT"))
                        player.getSprite().addVelocity(-player.getVelocity(),0);
                    else if (input.contains("RIGHT"))
                        player.getSprite().addVelocity(player.getVelocity(),0);
                    else if (input.contains("UP"))
                        player.getSprite().addVelocity(0,-player.getVelocity());
                    else if (input.contains("DOWN"))
                        player.getSprite().addVelocity(0,player.getVelocity());
        	        player.getSprite().update(0.016);
        	        
        	        
        	        
                    // background image clears canvas

                    player.getSprite().render(gc);
                    
                }
            });
        
        prepareActionHandlers();
        
        gameLoop.getKeyFrames().add( kf );
        
        gameLoop.play();
        
        playStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static void prepareActionHandlers()
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
	
}
