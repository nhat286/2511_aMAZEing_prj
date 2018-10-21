package niriksha_refactored;

import eric.CoOrd;

public class Treasure {
	
	private CoOrd co_ord;
	private char icon;
	private boolean picked_up;
	
	public Treasure(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '$';
		this.picked_up = false;
	}

	public void pickUp() {
		this.picked_up = true;
		this.removeTreasure();
	}
	
	public void removeTreasure() {
		this.setCoordinates(-1, -1);
	}
	
	// getter and setter methods
	
	public CoOrd getCoord() {
		return co_ord;
	}
	
	public char getIcon() {
		return icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public boolean isPickedUp() {
		return picked_up;
	}

}
