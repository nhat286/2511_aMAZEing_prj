package kyle_maze;

import java.io.IOException;

import eric.Maze;

public class SaveLoad implements Menu{
	Maze currentMaze;
	
	public SaveLoad(Maze maze){
		currentMaze = maze;
	}
	
	/* (non-Javadoc)
	 * Advanced feature, unimplemented
	 * 
	 * @see kyle_maze.Menu#displayMenu()
	 */
	@Override
	public void displayMenu() {
		System.out.println("You can save/load your game here. There would be 3 slots available.");
		
		
		// TODO Auto-generated method stub
		try {
			pause1Sec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void LoadGame() {
		
	}

	public void SaveGame() {
		
	}
	
}
