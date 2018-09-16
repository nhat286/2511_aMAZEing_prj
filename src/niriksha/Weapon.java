
package niriksha;

import eric.CoOrd;

public abstract class Weapon {
	
	public enum action {
		DESTROY, DIE, NOTHING, BOMB_DESTROY;
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
	
	public void destroyWeapon() {//Weapon w) {
		//w = null;
		this.setCoordinates(-1, -1);
	}
	
	public String getType() {
		return "Weapon";
	}
	
	public abstract Weapon copy();
}
