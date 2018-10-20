package niriksha;

import java.util.Timer;
import java.util.TimerTask;

import application.Sprite;
import javafx.scene.image.Image;

public class InvincibilityPotion extends Potion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7626372691307995377L;
	private boolean used;
	private Timer invincibility_timer;
	private STATE old_state;
	private Character current;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
		this.setSprite(new Sprite(new Image("InvincibilityPotion.png"), this.getCoordinates()));
	}

	@Override
	public STATE potionEffect(Character c) {
		
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
			if (current.getState() instanceof HoverInvincibleCharacter)
				current.setState(new HoverCharacter(current));
			else
				current.setState(old_state);
			destroyPotion();
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

	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("InvincibilityPotion.png"));
	}

}
