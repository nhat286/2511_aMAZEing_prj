package eric;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveLoad {
	
	private int log_count;
	
	public SaveLoad() {
		try {
			FileInputStream fileIn = new FileInputStream("log/stat.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.log_count = in.readInt();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			this.log_count = 0;
			this.updateStat();
		}
	}
	
	public SaveLoad(String string) {
		;
	}

	public int getSavedSlots() {
		return this.log_count;
	}

	public Maze loadGame(int id) {
		try {
	         FileInputStream fileIn = new FileInputStream("log/" + id + ".ser");
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
	         FileOutputStream fileOut = new FileOutputStream("log/" + id + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(m);
	         out.close();
	         fileOut.close();
	         if (this.log_count < 3) {
	        	 this.log_count = id + 1;
	        	 this.updateStat();
	         }
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	private void updateStat() {
		try {
			FileOutputStream fileOut = new FileOutputStream("log/stat.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeInt(this.log_count);
			out.close();
			fileOut.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Can't make new file");
		}
	}
	
	public Maze loadDesign() {
		try {
	         FileInputStream fileIn = new FileInputStream("design/design.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Maze load = (Maze) in.readObject();
	         in.close();
	         fileIn.close();
	         return load;
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveDesign(Maze mz) {
		try {
	         FileOutputStream fileOut = new FileOutputStream("design/design.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(mz);
	         out.close();
	         fileOut.close();       
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
}
