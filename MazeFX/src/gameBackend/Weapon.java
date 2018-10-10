package gameBackend;

public abstract class Weapon {
	
	public enum action {
		DESTROY, DIE, NOTHING, BOMB_DESTROY;
	}
	
	private CoOrd getCoordinates;
	private boolean picked_up;
	private char direction;
		 
	public Weapon(int x, int y, char c) {
		this.getCoordinates = new CoOrd(x, y,0);
		this.picked_up = false;
		this.direction = c;
	}
	
	/**
	 * Creates a copy of the weapon
	 * 
	 * @return copy of the weapon
	 */
	public abstract Weapon copy();

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
	
	/**
	 * Action of the weapon is called
	 * 
	 * @param object
	 */
	public abstract action weapon_action(Object object);
	
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
}