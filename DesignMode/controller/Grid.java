package controller;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
		//gridPane.setPadding(new Insets(20, 20, 20, 20));
		
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
		final int numCols = 10;
        final int numRows = 10;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(32);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(32);
            gridPane.getRowConstraints().add(rowConst);         
        }
        
        gridPane.setMinWidth(32*numCols); 
		gridPane.setMinHeight(32*numRows);
		gridPane.setMaxWidth(32*numCols); 
		gridPane.setMaxHeight(32*numRows);
			
        // ground
     		for (int i=0; i<numRows; i++) {
     			for (int j=0; j<numCols; j++) {
     				Image dirt = new Image(new FileInputStream("Images/dirt_0_new.png"));//, 29.5, 29.5, false, false);
     				ImageView ground = new ImageView(dirt);
     				gridPane.add(ground, j, i);
     			}
     		}
        
        // border around grid
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"));//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, 0, i);
		}
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"));//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, numRows-1, i);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"));//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, i, 0);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image(new FileInputStream("Images/brick_brown_0.png"));//, 29.5, 29.5, false, false);
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
		
		gridPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("(x: " + event.getX() + ", y: " + event.getY() + ")");
			}
		});
		
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
	    
	    bomb.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = bomb.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(bomb.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    sword.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = sword.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(sword.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    hover.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = hover.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(hover.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    invincibility.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = invincibility.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(invincibility.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    coward.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = coward.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(coward.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    hunter.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = hunter.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(hunter.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    strategist.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = strategist.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(strategist.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    hound.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = hound.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(hound.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    boulder.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = boulder.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(boulder.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    pit.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = pit.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(pit.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    floor_switch.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = floor_switch.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(floor_switch.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    wall.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = wall.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(wall.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    exit.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = exit.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(exit.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    open_door.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = open_door.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(open_door.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    close_door.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = close_door.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(close_door.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    
	    key.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = key.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(key.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    treasure.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	        	System.out.println("DragDetected");
	            Dragboard db = treasure.startDragAndDrop(TransferMode.ANY);
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(treasure.getImage());
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
	    		
	    		//if (getNodeByRowColumnIndex((int) event.getY()/32, (int) event.getX()/32, gridPane) != null) {
	    			gridPane.add(pic, (int)(event.getX()/32), (int)event.getY()/32);
	    		//}
	    		
	         }
	    });
				
	}
	
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	
	public static void main(String args[]){ 
	      launch(args); 
	}

}

/*arrow.setOnDragDone(new EventHandler<DragEvent>() {
public void handle(DragEvent event) {
    if (event.getTransferMode() == TransferMode.MOVE) {
    	System.out.println("DragDone");
    }
    event.consume();
}
});*/
}
