package niriksha;

import prj_2511.CoOrd;
import prj_2511.Enemy;

public abstract class Weapon {
	
	// don't know how to store coordinates yet
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

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public abstract int action(Enemy e);
}
