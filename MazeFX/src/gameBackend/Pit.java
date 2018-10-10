package gameBackend;

public class Pit extends Obstacle {
	
	public Pit(int x, int y) {
		super(x, y, 'B');
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
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	public String getType() {
		return "Pit";
	}

}
