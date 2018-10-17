
package niriksha;

import application.Sprite;
import eric.CoOrd;

public abstract class Weapon {
	
	private CoOrd getCoordinates;
	private boolean picked_up;
	private char direction;
	private Sprite sprite;
		 
	public Weapon(int x, int y, char c) {
		this.getCoordinates = new CoOrd(x, y);
		this.picked_up = false;
		this.direction = c;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}

	public CoOrd getCoordinates() {
		return getCoordinates;
	}
	
	public char getIcon() {
		return this.direction;
	}

	public boolean isPicked_up() {
		return this.picked_up;
	}

	public void setCoordinates(int x, int y) {
		this.getCoordinates.setXY(x, y);
	}
	
	/**
	 * Action of the weapon is called
	 * 
	 * @param object
	 */
	public abstract ACTION weapon_action(Object object);
	
	/**
	 * Destroys weapons by setting their coordinates outside of the maze
	 */
	public void destroyWeapon() {
		this.setCoordinates(-1, -1);
	}
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Weapon";
	}
	
	/**
	 * Creates a copy of the weapon
	 * 
	 * @return copy of the weapon
	 */
	public abstract Weapon copy();
}
