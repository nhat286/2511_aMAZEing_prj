package niriksha;

import java.util.Timer;
import java.util.TimerTask;
import jae.Enemy;

public class InvincibilityPotion extends Potions implements State {

	private boolean used;
	private int turn_left;
	Timer invinciblity_timer;
	//Object o;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
		this.turn_left = 5;
	}
	
	/**
	 * Allows character to become invincible to enemies and bombs
	 * 
	 * @return invincible action, which implies the invincibility potion is put into effect for a time period
	 */
	/*public action potion_effect() {
		if (this.used == false) {
			this.used = true;
			return action.INVINCIBLE;
		} else if (this.turn_left > 0) {
			this.countDown();
			return action.INVINCIBLE;
		} else {
			this.destroyPotion();
			return action.NOTHING;
		}
	}*/
	
	@Override
	public ACTION potion_effect() {
		if (this.used == false) {
			this.invinciblity_timer.schedule(new timing(), 1000*10);
			invinciblity_timer.cancel();
			this.used = true;
			return ACTION.INVINCIBLE;
		}
		
		return ACTION.NOTHING;
	}
	
	// start/stop timer
	class timing extends TimerTask {
		public void run() {
			// return action.
        }
	}

	public boolean isUsed() {
		return used;
	}
	
	/**
	 * Decrement turns left until effect wears off
	 */
	public void countDown() {
		this.turn_left--;
	}
	
	/**
	 * 
	 * @return turns left until effect wears off
	 */
	public int turnsRemaining() {
		return this.turn_left;
	}
	
	/**
	 * Creates a copy of this invincibility potion
	 * 
	 * @return copy of this invincibility potion
	 */
	@Override
	public Potions copy() {
		return new InvincibilityPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	/**
	 * Returns the type of potion 
	 * 
	 * @return type of potion
	 */
	@Override
	public String getType() {
		return "InvincibilityPotion";
	}

}