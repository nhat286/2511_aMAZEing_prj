package niriksha;

public class Door extends Obstacle {
	
	boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
	}
	
	public boolean isDoor_open() {
		return door_open;
	}
	
	/**
	 * Method to change state of door to open
	 */
	public void openDoor() {
		this.door_open = true;
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Door";
	}
	
	/**
	 * Creates a copy of this door
	 * 
	 * @return copy of this doorr
	 */
	@Override
	public Obstacle copy() {
		return new Door(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
