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
		try {
			pause1Sec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void LoadGame() {
		
	}

	public void SaveGame() {
		
	}
	
}