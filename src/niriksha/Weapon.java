
package niriksha;

import eric.CoOrd;

public abstract class Weapon {
	
	enum action {
		DESTROY, DIE, NOTHING;
	}
	
	private CoOrd getCoordinates;
	private boolean picked_up;
	private char direction;
		 
	public Weapon(int x, int y, char c) {
		this.getCoordinates = new CoOrd(x, y);
		this.picked_up = false;
		this.direction = c;
	}

	public CoOrd getCoordinates() {
		return getCoordinates;
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

	public void setCoordinates(int x, int y) {
		this.getCoordinates.setXY(x, y);
	}
	
	public abstract action weapon_action(Object object);
	
	public void destroyWeapon(Weapon w) {
		w = null;
	}
	
	public String getType() {
		return "Weapon";
	}
}
