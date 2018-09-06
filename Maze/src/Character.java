
public class Character {
	
	// don't know how to store coordinates yet
	int[][] coordinates = new int[1][2];
	 
	public Character(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
	}
	
	public int[][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
	}
	
	public void move() {
		/*if (blocked path) {
			// do nothing
		}
		else {
			
		}*/
	}
	
	public void pick_up_weapon(Weapon w) {
		
	}
	
	public void stop_using_weapon(Weapon w) {
		
	}
	
	public void pick_up_specialised_weapon(Weapon w) {
		
	}
	
	public void stop_using_specialised_weapon(Weapon w) {
		
	}
	
	public void destroy_character() {
		
	}

}
