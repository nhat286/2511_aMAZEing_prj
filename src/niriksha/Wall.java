package niriksha;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Wall";
	}
	
	/**
	 * Creates a copy of the wall
	 * 
	 * @return copy of the wall
	 */
	@Override
	public Obstacle copy() {
		return new Wall(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
