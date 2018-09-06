
public class Bomb extends SpecialItems {
	
	boolean lit;
	int burn_time; // change to time type?
	
	public Bomb(int x, int y) {
		super(x, y);
	}
	
	public boolean isLit() {
		return lit;
	}

	public int getBurn_time() {
		return burn_time;
	}

	public void special_effect() {
		
	}
	
}
