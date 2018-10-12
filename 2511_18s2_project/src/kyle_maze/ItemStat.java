package kyle_maze;

import java.util.HashMap;
import java.util.Map.Entry;

import eric.Maze;

public class ItemStat implements Menu{

	private String[] ItemDescript;
	private HashMap<String, Integer> potionHash;
	private HashMap<String, Integer> weaponHash;
	
	/**
	 * Initiate the item descriptions
	 */
	public ItemStat(Maze maze){
		ItemDescript = new String [7];
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
		ItemDescript[5] = "Sword: "
				+ "Could be used to fight close ranged enemy.";
		ItemDescript[6] = "Arrow: "
				+ "Could be used to fight ranged enemy.";
		potionHash = maze.potionStat();
		weaponHash = maze.weaponStat();
	}
	
	/* (non-Javadoc)
	 * Display item descriptions
	 * 
	 * @see kyle_maze.Menu#displayMenu()
	 */
	/**
	 * Prints the Item statistics
	 */
	@Override
	public void display() {
		System.out.println("The followed items might be found in the maze to assist you:");
		for (Entry<String, Integer> e : potionHash.entrySet()) {
			switch(e.getKey().toString()) {
			case "Hover Potion":
				System.out.println("------------------------------------------------------------");
				System.out.println(ItemDescript[0]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Invincible Potion":
				System.out.println("------------------------------------------------------------");
				System.out.println(ItemDescript[1]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			default:
				break;
			}
		}
		
		for (Entry<String, Integer> e : weaponHash.entrySet()) {
			switch(e.getKey().toString()) {
			case "Sword":
				System.out.println("------------------------------------------------------------");
				System.out.println(ItemDescript[5]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Arrow":
				System.out.println("------------------------------------------------------------");
				System.out.println(ItemDescript[6]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Bomb":
				System.out.println("------------------------------------------------------------");
				System.out.println(ItemDescript[2]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			default:
				break;
			}
		}

		System.out.println(ItemDescript[3]);
		System.out.println(ItemDescript[4]);
		
	}
}
