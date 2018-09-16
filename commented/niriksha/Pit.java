package niriksha;

public class Pit extends Obstacle {
	
	public Pit(int x, int y) {
		super(x, y, 'B');
	}
	
	/*
	 * Returns the type of obstacle 
	 * 
	 * @post type of obstacle
	 */
	public String getType() {
		return "Pit";
	}

}
