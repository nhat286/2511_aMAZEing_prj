
package niriksha;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
	}
	
	/*
	 * Returns the type of obstacle 
	 * 
	 * @post type of obstacle
	 */
	@Override
	public String getType() {
		return "Wall";
	}

}