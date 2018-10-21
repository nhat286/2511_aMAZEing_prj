package niriksha_refactored;

import eric.CoOrd;

public abstract class Weapon {
	
	private CoOrd getCoordinates;
	private boolean picked_up;
	private char direction;
	
	public Weapon(int x, int y, char c) {
		this.getCoordinates = new CoOrd(x, y);
		this.picked_up = false;
		this.direction = c;
	}
	
	public abstract ACTION weapon_action(Object object);
	
	public void destroyWeapon() {
		this.setCoordinates(-1, -1);
	}
	
	// getter and setter methods

	public CoOrd getCoordinates() {
		return getCoordinates;
	}
	
	public void setCoordinates(int x, int y) {
		this.getCoordinates.setXY(x, y);
	}
	
	public char getIcon() {
		return this.direction;
	}
	
	public CoOrd getCo_ord() {
		return getCoordinates;
	}

	public boolean isPicked_up() {
		return picked_up;
	}

}
