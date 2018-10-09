
package gameBackend;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
	}
	
	/**
	 * Creates a copy of the weapon
	 * 
	 * @return copy of the weapon
	 */
	@Override
	public Obstacle copy() {
		return new Wall(this.getCoordinates().getX(), this.getCoordinates().getY());
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

}