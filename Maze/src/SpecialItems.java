
package niriksha;

import eric.CoOrd;

public abstract class SpecialItems {
	
	enum action {
		OPEN, N_OPEN, PICKUP, N_PICKUP, NOTHING;
	}
	
	private CoOrd co_ord;
	private char icon;
	
	public SpecialItems(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}

	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public abstract void special_effect();

}

