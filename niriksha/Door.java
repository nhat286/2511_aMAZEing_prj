package niriksha_refactored;

public class Door extends Obstacle {

	boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
	}
	
	public void openDoor() {
		this.door_open = true;
	}
	
	public boolean isDoor_open() {
		return door_open;
	}

}
