package niriksha;

import eric.CoOrd;
import eric.Enemy;
import eric.Obstacle;

public abstract class SpecialItems {
	
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
	
	public void special_effect() {
		
	}
	
	public abstract int special_effect(Weapon w);
	public abstract int special_effect(Obstacle o);
	public abstract int special_effect(Enemy e);
}
