import java.util.Timer;

public class InvincibilityPotion extends SpecialItems {
	
	Timer time_limit; // change to time type?

	public InvincibilityPotion(int x, int y) {
		super(x, y);
		this.time_limit = new Timer();
	}

	public Timer getTime_limit() {
		return this.time_limit;
	}
	
	public void special_effect() {
		
	}

}
