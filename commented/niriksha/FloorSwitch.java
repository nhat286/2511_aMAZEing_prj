package niriksha;

public class FloorSwitch extends Obstacle {
	
	boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
	}
	
	/*
	 * Activates trigger
	 * 
	 * @post: sets the trigger as true
	 */
	public void activateTrigger() {
		this.trigger = true;
	}
	
	/*
	 * Deactivates trigger
	 * 
	 * @post: sets the trigger as false
	 */
	public void deactivateTrigger() {
		this.trigger = false;
	}
	
	public boolean triggered() {
		return this.trigger;
	}
	
	/*
	 * Returns the type of obstacle 
	 * 
	 * @post type of obstacle
	 */
	public String getType() {
		return "FloorSwitch";
	}

}