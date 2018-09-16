package niriksha;

import eric.CoOrd;

public class Key {
	 
	private CoOrd co_ord;
	private char icon;
	Door door;
	
	public Key(int x, int y, Door d) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '&';
		this.door = d;
	}

	/*
	 * Determines if the door is assigned to be opened by this key
	 * 
	 * @param: Door
	 * @post: door is opened if its assigned to this key
	 */
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
	
	/*
	 * Returns the type of object 
	 * 
	 * @post type of object
	 */
	public String getType() {
		return "Key";
	}

}