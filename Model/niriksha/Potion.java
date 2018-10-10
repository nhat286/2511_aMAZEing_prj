package niriksha;

import eric.CoOrd;

public abstract class Potion {
//	public enum action {
//		INVINCIBLE, HOVER, NOTHING;
//	}
	
	private CoOrd co_ord;
	private char icon;
	
	public Potion(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}

	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	/**
	 * Effect of the potion is induced
	 */
	//public abstract action potion_effect();
	public abstract void potion_effect(STATE current_state);
	
	/**
	 * Destroys potion by setting their coordinates outside of the maze
	 */
	public void destroyPotion() {
		this.setCoordinates(-1, -1);
	}
	
	/**
	 * Returns the type of potion 
	 * 
	 * @return type of potion
	 */
	public String getType() {
		return "Potion";
	}
	
	/**
	 * Creates a copy of the potion
	 * 
	 * @return copy of this potion
	 */
	public abstract Potion copy();
}
