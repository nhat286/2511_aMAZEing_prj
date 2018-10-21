package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class Door extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8171995417755992212L;
	private boolean door_open;
	
	public Door(int x, int y) {
		super(x, y, 'E');
		this.door_open = false;
		this.setSprite(new Sprite(new Image("closed_door.png"), this.getCoordinates()));
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Door(int x, int y, int i) {
		super(x, y, 'E');
		this.door_open = false;
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
	 * for testing purposes only
	 * @param i
	 */
	public void openDoor(int i) {
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
	
	@Override
	public void setCoordinates(int x, int y) {
		super.setCoordinates(x, y);
		this.setSprite(new Sprite(new Image("closed_door.png"), this.getCoordinates()));
	}
	
	@Override
	public void updateImage() {
		if (this.isDoorOpen())
			this.getSprite().setImage(new Image("open_door.png"));
		else
			this.getSprite().setImage(new Image("closed_door.png"));
	}
}
