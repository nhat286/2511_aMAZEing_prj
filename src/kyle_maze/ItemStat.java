package kyle_maze;

import java.io.IOException;

public class ItemStat implements Menu{

	private String[] ItemDescript;
	
	public ItemStat(){
		ItemDescript = new String [4];
		ItemDescript[0] = "Hover Potion: can make you fly (over pits only though)";
		ItemDescript[1] = "Invincible Potion: nothing can kill you (except pits) for 5 seconds";
		ItemDescript[2] = "Bomb: explosion that destroys enemies and boulders (and maybe you too) above, below, right, left to it after 3 seconds being lit";
		ItemDescript[3] = "Key: to unlock a specific door";
	}
	
	/**
	 * Prints the Item statistics
	 */
	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		try {
			pause1Sec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
