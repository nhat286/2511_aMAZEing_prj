package niriksha;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Potion {
	
	private boolean used;
	private Timer invincibility_timer;
	private STATE old_state;
	private Character current;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
	}

	@Override
	public STATE potion_effect(Character c) {
		
		this.current = c;
		this.old_state = c.getState();
		
		if (this.used == false) {
			if (c.getState() instanceof HoverCharacter) {
				c.setState((STATE) new HoverInvincibleCharacter(c));
			}
			else {
				c.setState((STATE) new InvincibleCharacter(c));
			}
			this.invincibility_timer = new Timer();
			this.invincibility_timer.schedule(new timing(), 1000*10);
			if (this.used)
				this.invincibility_timer.purge();
		}
		return c.getState();
		
	}
	
	class timing extends TimerTask {
		public void run() {
			used = true;
			invincibility_timer.cancel();
			current.setState(old_state);
        }
	}
	
	// getter and setter methods 
	
	public boolean isUsed() {
		return used;
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
	
	/**
	 * Creates a copy of this invincibility potion
	 * 
	 * @return copy of this invincibility potion
	 */
	@Override
	public Potion copy() {
		return new InvincibilityPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
