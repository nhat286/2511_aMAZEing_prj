package niriksha_refactored;

import eric.CoOrd;

public abstract class Potion {
	
	private CoOrd co_ord;
	private char icon;
	
	public Potion(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}
	
	public abstract void potion_effect(STATE current_state);
	
	public void destroyPotion() {
		this.setCoordinates(-1, -1);
	}
	
	// getter and setter methods 
	
	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public char getIcon() {
		return this.icon;
	}

}
