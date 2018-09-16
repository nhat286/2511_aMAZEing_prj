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
	
	public abstract action potion_effect();
	
	public void destroyPotion(Potions p) {
		p = null;
	}
	
	public String getType() {
		return "Potion";
	}
}