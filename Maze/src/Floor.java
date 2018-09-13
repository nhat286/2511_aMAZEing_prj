package jae;

public class FloorSwitch extends Obstacles {
	
	boolean trigger;
	
	public FloorSwitch(int x, int y) {
		super(x, y, 'I');
		this.trigger = false;
	}
	
	public void activateTrigger() {
		this.trigger = true;
	}

}
