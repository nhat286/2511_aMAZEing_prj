package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class FloorSwitch extends Obstacle {
	
	private boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
		this.setSprite(new Sprite(new Image("pressure_plate.png"), this.getCoordinates()));
	}
	
	/**
	 * Activates floorswitch
	 * 
	 */
	public void activateTrigger() {
		this.trigger = true;
	}
	
	/**
	 * Deactivates trigger
	 * 
	 */
	public void deactivateTrigger() {
		this.trigger = false;
	}
	
	public boolean triggered() {
		return this.trigger;
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "FloorSwitch";
	}
	
	/**
	 * Creates a copy of this floorswitch
	 * 
	 * @return copy of this floorswitch
	 */
	@Override
	public Obstacle copy() {
		return new FloorSwitch(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
