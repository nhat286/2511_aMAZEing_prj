package kyle_maze;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import eric.Maze;

public class ItemStat implements Menu{

	private String[] ItemDescript;
	
	public ItemStat(){
		ItemDescript = new String [4];
		ItemDescript[0] = "Hover Potion: "
				+ "This potion could make you hovering until you are dead or you win the game."
				+ "It is useful to hover through pits.";
		ItemDescript[1] = "Invincible Potion: "
				+ "This potion could make you invincible and destroy any enemies upon collision. "
				+ "Although enemies are tend to run away from you. "
				+ "The effect of the invincible potion is limited to 10 secs.";
		ItemDescript[2] = "Bomb: "
				+ "The bomb could be lit up and drop to the ground."
				+ "After a period of time it will explode and destruct everything nearby.";
		ItemDescript[3] = "Key: "
				+ "There would be up to 3 keys in any maze to be found, each key belongs to a specific door."
				+ "Once the key has been used to sucessfully open a door, it would be destroyed.";
		ItemDescript[4] = "Treasure: "
				+ "There might be Treasures in some mazes and collecting them all might leads to win.";
	}
	
	@Override
	public void displayMenu() {
		System.out.println("The followed items might be found in the maze to assist you:");
		System.out.println(ItemDescript[0]);
		System.out.println(ItemDescript[1]);
		System.out.println(ItemDescript[2]);
		System.out.println(ItemDescript[3]);
		System.out.println(ItemDescript[4]);
		
		
		// TODO Auto-generated method stub
		try {
			pause1Sec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
