package niriksha;

public class FloorSwitch extends Obstacle {
	
	boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
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
