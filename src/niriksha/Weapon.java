
package niriksha;

import eric.CoOrd;

public abstract class Weapon {
	
	enum action {
		DESTROY, DIE, NOTHING;
	}
	
	private CoOrd co_ord;
	private boolean picked_up;
	private char icon;
		 
	public Weapon(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.picked_up = false;
		this.icon = c;
	}

	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}

	public CoOrd getCo_ord() {
		return co_ord;
	}

	public boolean isPicked_up() {
		return picked_up;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public abstract action weapon_action(Object object);
	
	public void destroyWeapon(Weapon w) {
		w = null;
	}
	
	public String getType() {
		return "Weapon";
	}
}
