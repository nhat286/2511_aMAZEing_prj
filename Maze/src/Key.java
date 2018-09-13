package niriksha;

import eric.CoOrd;
import jae.Door;

public class Key {
	 
	private CoOrd co_ord;
	private char icon;
	Door door;
	
	public Key(int x, int y, char c, Door d) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
		this.door = d;
	}

	// check if door is the one assigned to this key - if it is open the door 
	public boolean checkDoor(Door d) {
		if (this.door.equals(d)) {
			d.openDoor();
			return true;
		}
		return false;
	}
	
	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public String getType() {
		return "Key";
	}

}
