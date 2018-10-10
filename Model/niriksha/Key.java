package niriksha;

import eric.CoOrd;

public class Key {
	 
	private CoOrd co_ord;
	private char icon;
	private Door door;
	
	public Key(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '&';
		this.door = null;
	}

	/**
	 * Determines if the door is assigned to be opened by this key
	 * 
	 * @param Door
	 * @return true if door is opened and is assigned to this key, false otherwise
	 */ 
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
	/**
	 * Pickup which remove its appearance from the map
	 */
	public void pickUp() {
		this.setCoordinates(-2, -2);
	}
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Key";
	}
	
	/**
	 * Creates a copy of this key
	 * 
	 * @return copy of this key
	 */
	public Key copy() {
		return new Key(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
