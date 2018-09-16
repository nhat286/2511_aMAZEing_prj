package kyle_maze;

import java.io.IOException;

public class ItemStat implements Menu{

	private String[] ItemDescript;
	
	public ItemStat(){
		ItemDescript = new String [4];
		ItemDescript[0] = "Hover Potion: ...";
		ItemDescript[1] = "Invincible Potion: ...";
		ItemDescript[2] = "Bomb: ...";
		ItemDescript[3] = "Key: ...";
	}
	
	/**
	 * Prints the Item statistics
	 */
	@Override
	public void displayMenu() {
		try {
			pause1Sec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}