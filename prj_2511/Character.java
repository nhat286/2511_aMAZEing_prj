package prj_2511;

public class Character {
	
	// don't know how to store coordinates yet
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	 
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = '@';
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public void move() {
		/*if (blocked path) {
			// do nothing
		}
		else {
			
		}*/
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
	}
	
	public void useWeapon(Weapon w) {
		
	}
	
	public void pickUpSpecialisedItem(SpecialItems i) {
		this.bag.addItem(i);
	}
	
	public void useSpecialisedItem(SpecialItems i) {
		
	}
	
	public void destroy_character(Character player) {
		player = null;
	}

}
