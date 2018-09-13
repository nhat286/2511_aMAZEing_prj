package jae;

import eric.CoOrd;

public abstract class Obstacles {
	
	protected CoOrd co_ord;
	private char icon;
	
	public Obstacles(int x, int y, char c) {
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
	
	public String getType() {
		return "Obstacles";
	}
}
