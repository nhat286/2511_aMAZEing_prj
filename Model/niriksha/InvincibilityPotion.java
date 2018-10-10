package niriksha;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Potion {
	
	private boolean used;
	private Timer invinciblity_timer;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
	}

	@Override
	public void potion_effect(STATE current_state) {
		
		STATE copy = current_state;
		
		if (this.used == false) {
			
			if (copy == HoverCharacter.GetInstance()) {
				current_state = HoverInvincibleCharacter.GetInstance();
			}
			else {
				current_state = InvincibleCharacter.GetInstance();
			}
			
			this.invinciblity_timer.schedule(new timing(), 1000*10);
			
			invinciblity_timer.cancel();
			
			this.used = true;
			
			current_state = copy;
		}
		
	}
	
	class timing extends TimerTask {
		public void run() {
			// do nothing
        }
	}
	
	// getter and setter methods 
	
	public boolean isUsed() {
		return used;
	}
	
	@Override
	public Potion copy() {
		return new InvincibilityPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
