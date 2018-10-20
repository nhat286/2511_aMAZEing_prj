package controller;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Grid extends Application{
	
	BorderPane root = new BorderPane();
	Scene scene = new Scene(root);
	
	GridPane side = new GridPane();
	
	GridPane gridPane = new GridPane();  

	@Override
	public void start(Stage stage) throws Exception {
		
		root.setPadding(new Insets(10, 20, 10, 20));
		
		//side.setGridLinesVisible(true);
		side.setMinHeight(500);
		side.setMinWidth(200);
		side.setMaxHeight(500);
		side.setMaxWidth(200);
		side.setPadding(new Insets(10, 20, 20, 20));
		
		gridPane.setGridLinesVisible(true);
		gridPane.setMinWidth(600); 
		gridPane.setMinHeight(600);
		gridPane.setPadding(new Insets(10, 20, 20, 20));
		
		// setting up side
		final int side_col = 4;
        final int side_row = 13;
        for (int i = 0; i < side_col; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(50);
            side.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < side_row; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(50);
            side.getRowConstraints().add(rowConst);         
        }
		
		// setting up gridPane
        // max is 25*25
        // min is
		final int numCols = 25;
        final int numRows = 25;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(35);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(35);
            gridPane.getRowConstraints().add(rowConst);         
        }
			
        // ground
     		for (int i=0; i<numRows; i++) {
     			for (int j=0; j<numCols; j++) {
     				Image dirt = new Image(new FileInputStream("Images/dirt_0_new.png"), 34, 34, false, false);
     				ImageView ground = new ImageView(dirt);
     				gridPane.add(ground, j, i);
     			}
     		}
        
        // border around grid
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"), 34, 34, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, 0, i);
		}
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"), 34, 34, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, numRows-1, i);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"), 34, 34, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, i, 0);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"), 34, 34, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, i, numCols-1);
		}
		
		
		
		// character on the maze
		Image chara = new Image(new FileInputStream("Images/human_new.png"));
		ImageView character = new ImageView(chara);
		gridPane.add(character, 1, 1);
		
		
		// displaying images for drag/drop
		Text text1 = new Text("DESIGN YOUR OWN MAZE");
		side.add(text1, 0, 0);
		
		Text text2 = new Text("Weapons");
		side.add(text2, 0, 1);
		
		Image arrow1 = new Image(new FileInputStream("Images/arrow.png"));
		ImageView arrow = new ImageView(arrow1);
		side.add(arrow, 0, 2);
		
		Image bomb1 = new Image(new FileInputStream("Images/bomb_unlit.png"));
		ImageView bomb = new ImageView(bomb1);
		side.add(bomb, 1, 2);
		
		Image sword1 = new Image(new FileInputStream("Images/greatsword_1_new.png"));
		ImageView sword = new ImageView(sword1);
		side.add(sword, 2, 2);
		
		Text text3 = new Text("Potions");
		side.add(text3, 0, 3);
		
		Image hover1 = new Image(new FileInputStream("Images/bubbly.png"));
		ImageView hover = new ImageView(hover1);
		side.add(hover, 0, 4);
		
		Image invincibility1 = new Image(new FileInputStream("Images/brilliant_blue_new.png"));
		ImageView invincibility = new ImageView(invincibility1);
		side.add(invincibility, 1, 4);
		
		Text text4 = new Text("Enemies");
		side.add(text4, 0, 5);
		
		Image coward1 = new Image(new FileInputStream("Images/coward.png"));
		ImageView coward = new ImageView(coward1);
		side.add(coward, 0, 6);
		
		Image hunter1 = new Image(new FileInputStream("Images/hunter.png"));
		ImageView hunter = new ImageView(hunter1);
		side.add(hunter, 1, 6);
		
		Image strategist1 = new Image(new FileInputStream("Images/strategist.png"));
		ImageView strategist = new ImageView(strategist1);
		side.add(strategist, 2, 6);
		
		Image hound1 = new Image(new FileInputStream("Images/hound.png"));
		ImageView hound = new ImageView(hound1);
		side.add(hound, 3, 6);
		
		Text text5 = new Text("Miscellaneous Items");
		side.add(text5, 0, 7);
		
		Image boulder1 = new Image(new FileInputStream("Images/boulder.png"));
		ImageView boulder = new ImageView(boulder1);
		side.add(boulder, 0, 8);
		
		Image pit1 = new Image(new FileInputStream("Images/shaft.png"));
		ImageView pit = new ImageView(pit1);
		side.add(pit, 1, 8);
		
		Image floor_switch_1 = new Image(new FileInputStream("Images/pressure_plate.png"));
		ImageView floor_switch = new ImageView(floor_switch_1);
		side.add(floor_switch, 2, 8);
		
		Image wall1 = new Image(new FileInputStream("Images/brick_brown_0.png"));
		ImageView wall = new ImageView(wall1);
		side.add(wall, 0, 9);
		
		Image exit1 = new Image(new FileInputStream("Images/exit.png"));
		ImageView exit = new ImageView(exit1);
		side.add(exit, 1, 9);
		
		Image open_door_1 = new Image(new FileInputStream("Images/open_door.png"));
		ImageView open_door = new ImageView(open_door_1);
		side.add(open_door, 2, 9);
		
		Image close_door_1 = new Image(new FileInputStream("Images/closed_door.png"));
		ImageView close_door = new ImageView(close_door_1);
		side.add(close_door, 3, 9);
		
		Image key1 = new Image(new FileInputStream("Images/key.png"));
		ImageView key = new ImageView(key1);
		side.add(key, 0, 10);
		
		Image treasure1 = new Image(new FileInputStream("Images/gold_pile.png"));
		ImageView treasure = new ImageView(treasure1);
		side.add(treasure, 1, 10);
	         
		Button play = new Button("Play");
		side.add(play, 0, 12);
		
		
	    //Displaying the contents of the stage
		root.setLeft(side);
		root.setRight(gridPane);
	    stage.setTitle("Design Mode"); 
	    stage.setScene(scene);
	    stage.show();
	    
	    // drag and drop
	    
	    arrow.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = arrow.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(arrow.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    gridPane.setOnDragOver(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	            if (event.getDragboard().hasImage()) {
	            	System.out.println("DragOver");
	                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	            }
	            event.consume();
	        }
	    });
	    
	    gridPane.setOnDragEntered(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	           System.out.println("DragEntered");
	        }
	    });
	    
	    gridPane.setOnDragExited(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	        	System.out.println("DragExited");
	        }
	    });
	    
	    gridPane.setOnDragDropped(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	        	Image img = event.getDragboard().getImage();
	    		ImageView pic = new ImageView();
	    		pic.setImage(img);
	    		
	    		gridPane.add(pic, 5, 5);
	         }
	    });
	    
	    arrow.setOnDragDone(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	            if (event.getTransferMode() == TransferMode.MOVE) {
	            	System.out.println("DragDone");
	            }
	            event.consume();
	        }
	    });
				
	}
	
	/*public void handle(DragEvent event) {
	    //Data dropped
	    //If there is an image on the dragboard, read it and use it
	    Dragboard db = event.getDragboard();
	    boolean success = false;
	    Node node = event.getPickResult().getIntersectedNode();
	    if(node != gridPane && db.hasImage()){

	        Integer cIndex = gridPane.getColumnIndex(node);
	        Integer rIndex = gridPane.getRowIndex(node);
	        int x = cIndex == null ? 0 : cIndex;
	        int y = rIndex == null ? 0 : rIndex;
	        //target.setText(db.getImage()); --- must be changed to target.add(source, col, row)
	        //target.add(source, 5, 5, 1, 1);
	        //Places at 0,0 - will need to take coordinates once that is implemented
	        ImageView image = new ImageView(db.getImage());

	        // TODO: set image size; use correct column/row span
	        gridPane.add(image, x, y, 1, 1);
	        success = true;
	    }
	    //let the source know whether the image was successfully transferred and used
	    event.setDropCompleted(success);

	    event.consume();
	}*/
	
	public static void main(String args[]){ 
	      launch(args); 
	}
	
	
	

}
