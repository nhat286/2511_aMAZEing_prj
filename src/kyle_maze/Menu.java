package kyle_maze;

public interface Menu {
	default void display() {
		pauseGame();
		displayMenu();
	}
	void displayMenu();
	void pauseGame();
}
