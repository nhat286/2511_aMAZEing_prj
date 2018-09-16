package niriksha;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Potions {

	private boolean used;
	Timer time_limit;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
	}
	
	/*
	 * Allows character to become invincible to enemies and bombs
	 * 
	 * @post: the invincibility potion is put into effect for a time period
	 */
	public action potion_effect() {
		this.time_limit = new Timer();
		if (this.used == false) {
			this.used = true;
			this.time_limit.schedule(new timing(), 1000 * 40);
			this.setCoordinates(-1, -1);
			return action.INVINCIBLE;
		}
		
		return action.NOTHING;
	}
	
	class timing extends TimerTask {
		public void run() {
            time_limit.cancel();
        }
	}

	public boolean isUsed() {
		return used;
	}
	
	public Timer getTime_limit() {
		return this.time_limit;
	}
	
	/*
	 * Returns the type of potion 
	 * 
	 * @post type of potion
	 */
	@Override
	public String getType() {
		return "InvinciblePotion";
	}

}