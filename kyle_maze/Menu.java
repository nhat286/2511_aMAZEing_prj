package kyle_maze;

import java.io.IOException;

public interface Menu {

	public void displayMenu();
	
	public default void pause1Sec() throws IOException {
		System.out.println("Press any key to continue");
		System.in.read();
	}
	
}