package gameBackend;

public class FloorSwitch extends Obstacle {
	
	boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
	}
	
	/**
	 * Activates trigger
	 * 
	 * @return sets the trigger as true
	 */
	public void activateTrigger() {
		this.trigger = true;
	}
	
	/**
	 * Deactivates trigger
	 * 
	 * @return sets the trigger as false
	 */
	public void deactivateTrigger() {
		this.trigger = false;
	}
	
	public boolean triggered() {
		return this.trigger;
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
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	public String getType() {
		return "FloorSwitch";
	}

}