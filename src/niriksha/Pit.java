package niriksha;

public class Pit extends Obstacle {
	
	public Pit(int x, int y) {
		super(x, y, 'B');
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Pit";
	}
	
	/**
	 * Creates a copy of this pit
	 * 
	 * @return copy of this pit
	 */
	@Override
	public Obstacle copy() {
		return new Pit(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
