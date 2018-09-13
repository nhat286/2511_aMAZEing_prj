package jae;

public class Door extends Obstacle {
	
	boolean door_open;
	
	public boolean isDoor_open() {
		return door_open;
	}

	public void openDoor() {
		this.door_open = true;
	}

}
