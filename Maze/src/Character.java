
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
	
	public void pick_up_weapon(Weapon w) {
		// check what type of item it is
		// add item to the inventory
	}
	
	public void stop_using_weapon(Weapon w) {
		
	}
	
	public void pick_up_specialised_weapon(Weapon w) {
		
	}
	
	public void stop_using_specialised_weapon(Weapon w) {
		
	}
	
	public void destroy_character(Character player) {
		player = null;
	}

}
