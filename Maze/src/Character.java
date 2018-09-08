
public class Character {
	
	CoOrd coordinates; 
	 
	public Character(int x, int y) {
		this.coordinates = new CoOrd(x, y);
	}
	
	public CoOrd get_character_coordinates() {
		return this.coordinates;
	}
	
	public void set_character_coordinates(int x, int y) {
		this.coordinates.setXY(x, y);;
	}

	public void move(int x, int y) {
		
		if (x >= 0) {
			this.coordinates.moveRight();
		}
		else {
			this.coordinates.moveLeft();
		}
		
		if (y>=0) {
			this.coordinates.moveUp();
		}
		else {
			this.coordinates.moveDown();
		}
		
	}
	
	public void pick_up_weapon(Inventory i, Weapon w) {
		i.add_weapon(w);
	}
	
	/*public void stop_using_weapon(Weapon w) {
		not necessary as to use for arrow
		for sword - the func in sword class takes care of it
	}*/
	
	public void pick_up_specialised_item(Inventory i, SpecialItems si) {
		i.add_special_item(si);
	}
	
	/*public void stop_using_specialised_weapon(Weapon w) {
		not necessary unless player has control over when to 
		use/stop using hover potion multiple times
	}*/
	
	public void destroy_character(Character player) {
		player = null;
	}

}
