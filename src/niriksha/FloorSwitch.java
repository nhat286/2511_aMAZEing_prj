package niriksha;

public class FloorSwitch extends Obstacle {
	
	boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
	}
	
	public void activateTrigger() {
		this.trigger = true;
	}
	
	public void deactivateTrigger() {
		this.trigger = false;
	}
	
	public boolean triggered() {
		return this.trigger;
	}

}
