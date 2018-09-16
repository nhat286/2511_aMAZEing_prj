package niriksha;

import eric.CoOrd;

public class Key {
	 
	private CoOrd co_ord;
	private char icon;
	Door door;
	
	public Key(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '&';
		this.door = null;
	}

	// check if door is the one assigned to this key - if it is open the door 
	public boolean checkDoor(Door d) {
		if (this.door != null && this.door.equals(d)) {
			d.openDoor();
			this.setCoordinates(-1, -1);
			return true;
		}
		return false;
	}
	
	public void linkDoor(Door d) {
		this.door = d;
	}
	
	public Door getDoorLinked() {
		return this.door;
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
	
	public void pickUp() {
		this.setCoordinates(-2, -2);
	}
	
	public String getType() {
		return "Key";
	}
	
	public Key copy() {
		return new Key(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
