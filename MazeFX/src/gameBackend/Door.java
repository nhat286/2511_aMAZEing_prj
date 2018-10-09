package gameBackend;

public class Door extends Obstacle {
	
	boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
	}
	
	public boolean isDoor_open() {
		return door_open;
	}

	/**
	 * Opens door
	 * 
	 * @return sets the door_open as true
	 */
	public void openDoor() {
		this.door_open = true;
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
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	public String getType() {
		return "Door";
	}

}
