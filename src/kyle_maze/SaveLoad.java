package kyle_maze;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eric.Maze;

public class SaveLoad implements Menu{
	
	/* (non-Javadoc)
	 * Advanced feature, unimplemented
	 * 
	 * @see kyle_maze.Menu#displayMenu()
	 */
	@Override
	public void display() {
		System.out.println("You can save/load your game here. There would be 3 slots available.");
		

	}

	public Maze loadGame(int id) {
		try {
	         FileInputStream fileIn = new FileInputStream("/tmp/" + id + ".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Maze load = (Maze) in.readObject();
	         in.close();
	         fileIn.close();
	         return load;
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Maze not found");
	         c.printStackTrace();
	         return null;
	      }
	}

	public void saveGame(Maze m, int id) {
		try {
	         FileOutputStream fileOut = new FileOutputStream("/tmp/" + id + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(m);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
}
