
public abstract class Weapon {
	
	// don't know how to store coordinates yet
	int[][] coordinates = new int[1][2];
	boolean picked_up;
		 
	public Weapon(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
		this.picked_up = true; 
	}

	public int[][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
	}
	
	public void action() {
		
	}

}
