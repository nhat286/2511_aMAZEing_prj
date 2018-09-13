
package niriksha;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Potions {

	private boolean used;
	Timer time_limit;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
		this.time_limit = new Timer();
	}
	
	// only destroy enemy if potion hasn't been used
	public action potion_effect() {
		
		if (this.used == false) {
			this.time_limit.schedule(new timing(), 1000*10);
			this.used = true;
			return action.INVINCIBLE;
		}
		
		return action.NOTHING;
	}
	
	// start/stop timer
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
	
	
	/*@Override
	public int special_effect(Object o) {
		action = 2;
		return action;
	}
	
	public int special_effect(Enemy e) {
		e.destroyEnemy();
		action = 1;
		return action;
	}
	
	public int special_effect(Weapon w) {
		if (((w.getClass()).toString()).equals("Bomb")) {
			action = 0;
			return action;
		}
		action = 2;
		return action;
	}*/
	
	@Override
	public String getType() {
		return "InvinciblePotion";
	}

}

