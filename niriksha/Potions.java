package niriksha;

import eric.CoOrd;

public abstract class Potions {
	
	enum action {
		INVINCIBLE, HOVER, NOTHING;
	}
	
	private CoOrd co_ord;
	private char icon;
	
	public Potions(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}

	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	/**
	 * Effect of the potion is induced
	 */
	public abstract action potion_effect();
	
	/**
	 * Destroys potion by setting their coordinates outside of the maze
	 */
	public void destroyPotion() {
		this.setCoordinates(-1, -1);
	}
	
	/**
	 * Creates a copy of the potion
	 * 
	 * @return copy of this potion
	 */
	public abstract Potions copy();
	
	/**
	 * Returns the type of potion 
	 * 
	 * @return type of potion
	 */
	public String getType() {
		return "Potion";
	}
}