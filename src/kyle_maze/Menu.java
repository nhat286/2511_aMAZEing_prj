package kyle_maze;

import eric.MazeSystem;

public interface Menu {

	void displayMenu();
	
	default void pauseGame(MazeSystem ms) {
		ms.setPause(1);
	}
}
