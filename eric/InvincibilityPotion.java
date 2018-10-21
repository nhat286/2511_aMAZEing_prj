package eric;

import niriksha.Potions;

public class InvincibilityPotion extends Potions {

	private boolean used;
	private int turn_left;

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.used = false;
		this.turn_left = 5;
	}
	
	// only destroy enemy if potion hasn't been used
	public action potion_effect() {
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
	}

	public boolean isUsed() {
		return used;
	}
	
	public void countDown() {
		this.turn_left--;
	}

	public int turnsRemaining() {
		return this.turn_left;
	}
	
	@Override
	public String getType() {
		return "InvincibilityPotion";
	}
	
	@Override
	public Potions copy() {
		return new InvincibilityPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}