package kyle_maze;

import java.io.IOException;

import eric.Maze;

public class SaveLoad implements Menu{
	Maze currentMaze;
	
	public SaveLoad(Maze maze){
		currentMaze = maze;
	}
	
	@Override
	public void displayMenu() {
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