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
