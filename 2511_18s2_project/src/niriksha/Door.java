package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class Door extends Obstacle {
	
	private boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
		this.door_open = false;
		this.setSprite(new Sprite(new Image("closed_door.png"), this.getCoordinates()));
	}
	
	public boolean isDoorOpen() {
		return this.door_open;
	}
	
	/**
	 * Method to change state of door to open
	 */
	public void openDoor() {
		this.door_open = true;
		this.setSprite(new Sprite(new Image("open_door.png"), this.getCoordinates()));
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
