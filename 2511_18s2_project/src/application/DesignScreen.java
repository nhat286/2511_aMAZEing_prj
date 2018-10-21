package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import eric.Bomb;
import eric.CoOrd;
import eric.Exit;
import eric.Maze;
import eric.PlaySystem;
import eric.SaveLoad;
import jae.Coward;
import jae.Hound;
import jae.Hunter;
import jae.Strategist;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import niriksha.Arrow;
import niriksha.Boulder;
import niriksha.Door;
import niriksha.FloorSwitch;
import niriksha.HoverPotion;
import niriksha.InvincibilityPotion;
import niriksha.Key;
import niriksha.Pit;
import niriksha.Sword;
import niriksha.Treasure;
import niriksha.Wall;

public class DesignScreen extends Application{
	
	BorderPane root = new BorderPane();
	Scene scene = new Scene(root);
	
	GridPane side = new GridPane();
	
	GridPane gridPane = new GridPane();  
	private PlaySystem ms;
	private ArrayList<String> mapString;
	private Stage stage;
	private int numCols = 10;
    private int numRows = 10;
    private TextField setSizeT;

    public DesignScreen() {
	}
    
	public DesignScreen(int size) {
		this.numCols = size;
		this.numRows = size;
	}
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setMinHeight(0);
		stage.setMinWidth(0);
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
        mapString = new ArrayList<String>();
        
        readSavedDesign();
        
        ms = new PlaySystem();
        ms.start(numCols, Maze.RUNNER);
        
        
        
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
     				Image dirt = new Image("dirt_0_new.png");//, 29.5, 29.5, false, false);
     				ImageView ground = new ImageView(dirt);
     				gridPane.add(ground, j, i);
     			}
     		}
        
        // border around grid
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image("brick_brown_0.png");//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, 0, i);
		}
		for (int i=0; i<numRows; i++) {
			Image border_wall = new Image("brick_brown_0.png");//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, numRows-1, i);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image("brick_brown_0.png");//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, i, 0);
		}
		for (int i=0; i<numCols; i++) {
			Image border_wall = new Image("brick_brown_0.png");//, 29.5, 29.5, false, false);
			ImageView border = new ImageView(border_wall);
			gridPane.add(border, i, numCols-1);
		}
		
		addSavedDesign();
		
		// character on the maze
		Image chara = new Image("human_new.png");
		ImageView character = new ImageView(chara);
		gridPane.add(character, 1, 1);
		
		
		// displaying images for drag/drop
		Text text1 = new Text("DESIGN YOUR OWN MAZE");
		side.add(text1, 0, 0);
		
		Text text2 = new Text("Weapons");
		side.add(text2, 0, 1);
		
		Image arrow1 = new Image("Arrow.png");
		ImageView arrow = new ImageView(arrow1);
		side.add(arrow, 0, 2);
		
		Image bomb1 = new Image("Bomb.png");
		ImageView bomb = new ImageView(bomb1);
		side.add(bomb, 1, 2);
		
		Image sword1 = new Image("Sword.png");
		ImageView sword = new ImageView(sword1);
		side.add(sword, 2, 2);
		
		Text text3 = new Text("Potions");
		side.add(text3, 0, 3);
		
		Image hover1 = new Image("HoverPotion.png");
		ImageView hover = new ImageView(hover1);
		side.add(hover, 0, 4);
		
		Image invincibility1 = new Image("InvincibilityPotion.png");
		ImageView invincibility = new ImageView(invincibility1);
		side.add(invincibility, 1, 4);
		
		Text text4 = new Text("Enemies");
		side.add(text4, 0, 5);
		
		Image coward1 = new Image("coward.png");
		ImageView coward = new ImageView(coward1);
		side.add(coward, 0, 6);
		
		Image hunter1 = new Image("hunter.png");
		ImageView hunter = new ImageView(hunter1);
		side.add(hunter, 1, 6);
		
		Image strategist1 = new Image("strategist.png");
		ImageView strategist = new ImageView(strategist1);
		side.add(strategist, 2, 6);
		
		Image hound1 = new Image("hound.png");
		ImageView hound = new ImageView(hound1);
		side.add(hound, 3, 6);
		
		Text text5 = new Text("Miscellaneous Items");
		side.add(text5, 0, 7);
		
		Image boulder1 = new Image("boulder.png");
		ImageView boulder = new ImageView(boulder1);
		side.add(boulder, 0, 8);
		
		Image pit1 = new Image("shaft.png");
		ImageView pit = new ImageView(pit1);
		side.add(pit, 1, 8);
		
		Image floor_switch_1 = new Image("pressure_plate.png");
		ImageView floor_switch = new ImageView(floor_switch_1);
		side.add(floor_switch, 2, 8);
		
		Image wall1 = new Image("brick_brown_0.png");
		ImageView wall = new ImageView(wall1);
		side.add(wall, 0, 9);
		
		Image exit1 = new Image("exit.png");
		ImageView exit = new ImageView(exit1);
		side.add(exit, 1, 9);
		
		Image open_door_1 = new Image("open_door.png");
		ImageView open_door = new ImageView(open_door_1);
		side.add(open_door, 2, 9);
		
		Image close_door_1 = new Image("closed_door.png");
		ImageView close_door = new ImageView(close_door_1);
		side.add(close_door, 3, 9);
		
		Image key1 = new Image("Key.png");
		ImageView key = new ImageView(key1);
		side.add(key, 0, 10);
		
		Image treasure1 = new Image("Treasure.png");
		ImageView treasure = new ImageView(treasure1);
		side.add(treasure, 1, 10);
	       
		setSizeT = new TextField();
		Button play = new Button("Play");
		side.add(setSizeT, 0, 12,2,1);
		side.add(play, 3, 12);
		
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	ms.initializeDesign(mapString);
		    	mapString.add(((Integer)numCols).toString());
		    	//GameplayStage gs = new GameplayStage(stage,ms);
		    	SaveLoad sl = new SaveLoad("design");
		    	sl.saveDesign(mapString);
		    	GameplayStage gs = new GameplayStage(stage);
		    	gs.designMaze(ms);
		    	gs.start();
		    }
		});
		
		setSizeT.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	int size = Integer.parseInt(setSizeT.getText());
		    	
		    	if(size <=0 || size > 25) return;
		    	DesignScreen g = new DesignScreen(size);
		    	mapString = new ArrayList<String>();
		    	mapString.add(((Integer)size).toString());
		    	SaveLoad sl = new SaveLoad("design");
		    	sl.saveDesign(mapString);
		    	try {
					g.start(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		gridPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//System.out.println("(x: " + event.getX() + ", y: " + event.getY() + ")");
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
	            content.putString("Arrow");
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
	            content.putString("Bomb");
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
	            content.putString("Sword");
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
	            content.putString("HoverPotion");
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
	            content.putString("InvincibilityPotion");
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
	            content.putString("Coward");
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
	            content.putString("Hunter");
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
	            content.putString("Strategist");
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
	            content.putString("Hound");
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
	            content.putString("Boulder");
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
	            content.putString("Pit");
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
	            content.putString("FloorSwitch");
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
	            content.putString("Wall");
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
	            content.putString("Exit");
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
	            content.putString("OpenDoor");
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
	            content.putString("CloseDoor");
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
	            content.putString("Key");
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
	            content.putString("Treasure");
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
	    		mapString.add(event.getDragboard().getString()+" "+(int)event.getX()/32+" "+(int)event.getY()/32);
	    		
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
	
	private void readSavedDesign() {
		SaveLoad sl = new SaveLoad("design");
		// Load string map
		mapString = sl.loadDesign();
		if(mapString == null) return;
		// get the size
		numCols = Integer.parseInt(mapString.get(mapString.size()-1)) ;
		numRows = numCols;
		mapString.remove(mapString.size()-1);
		
	}
	
	private void addSavedDesign() {
		// get the entities
		ImageView imv;
		for (String str : mapString) {
			String[] substr = str.split(" ");
			switch(substr[0]) {
			case "Arrow":
				imv = new ImageView("Arrow.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Bomb":
				imv = new ImageView("Bomb.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Sword":
				imv = new ImageView("Sword.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "HoverPotion":
				imv = new ImageView("HoverPotion.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "InvincibilityPotion":
				imv = new ImageView("InvincibilityPotion.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Coward":
				imv = new ImageView("Coward.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Hunter":
				imv = new ImageView("Hunter.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Strategist":
				imv = new ImageView("Strategist.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Hound":
				imv = new ImageView("Hound.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Boulder":
				imv = new ImageView("Boulder.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break; 
			case "Pit":
				imv = new ImageView("shaft.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "FloorSwitch":
				imv = new ImageView("pressure_plate.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Wall":
				imv = new ImageView("brick_brown_0.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Exit":
				imv = new ImageView("exit.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "CloseDoor":
				imv = new ImageView("closed_door.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "OpenDoor":
				imv = new ImageView("open_door.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Key":
				imv = new ImageView("key.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			case "Treasure":
				imv = new ImageView("treasure.png");
				gridPane.add(imv, Integer.parseInt(substr[1]), Integer.parseInt(substr[2]));
				break;
			default:
				break;
			}
		}
	}

}


