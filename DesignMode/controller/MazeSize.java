package design_mode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MazeSize extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		/*private void createMap(){
	        gridMap = new GridMapT(rowSize, colSize);
	    }*/
		
		Text grid_row = new Text("Enter the size of the row");
		Text grid_col = new Text("Enter the size of the col");
		
		TextField row = new TextField();
		TextField col = new TextField();
		
		Button create_maze = new Button("Create Maze");
		
		//Creating a Grid Pane 
	      GridPane gridPane = new GridPane();    
	      
	      //Setting size for the pane  
	      gridPane.setMinSize(400, 200); 
	       
	      //Setting the padding  
	      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	      //Setting the vertical and horizontal gaps between the columns 
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	      
	      //Setting the Grid alignment 
	      gridPane.setAlignment(Pos.CENTER); 
	       
	      //Arranging all the nodes in the grid 
	      gridPane.add(grid_row, 0, 0); 
	      gridPane.add(row, 1, 0); 
	      gridPane.add(grid_col, 0, 1);       
	      gridPane.add(col, 1, 1); 
	      gridPane.add(create_maze, 0, 2);
	      
	      Scene scene = new Scene(gridPane);
	      
	      stage.setTitle("Design Mode"); 
	      
	      stage.setScene(scene); 
	         
	      //Displaying the contents of the stage 
	      stage.show(); 
	      
	}
	
	public static void main(String args[]){ 
	      launch(args); 
	}
	

}
