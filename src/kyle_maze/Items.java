package kyle_maze;

public class Items implements Menu{

	private String[] ItemDescript;
	
	Items(){
		ItemDescript = new String [4];
		ItemDescript[0] = "Hover Potion: ...";
		ItemDescript[1] = "Invincible Potion: ...";
		ItemDescript[2] = "Bomb: ...";
		ItemDescript[3] = "???: ...";
	}
	
	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		
	}
}
