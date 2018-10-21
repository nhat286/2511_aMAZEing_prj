package kyle_maze;

import java.io.IOException;

//import eric.MazeSystem;

public interface Menu {

	public void displayMenu();
	
	/**
	 * pause here uses a scanner reading to perform pause
	 * 
	 * @throws IOException
	 */
	public default void pause1Sec() throws IOException {
		System.out.println("Press any key to continue");
		System.in.read();
	}
	
//	public default void pauseGame(MazeSystem ms) {
//		ms.setPause(1);
//	}
}
