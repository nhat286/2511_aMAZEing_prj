package niriksha;

public class Door extends Obstacle {
	
	boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
	}
	
	public boolean isDoor_open() {
		return door_open;
	}

	public void openDoor() {
		this.door_open = true;
	}
	
	@Override
	public String getType() {
		return "Door";
	}

}
