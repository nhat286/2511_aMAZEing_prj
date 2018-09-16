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
	
	/**
	 * Allows character to become invincible to enemies and bombs
	 * 
	 * @return invincible action, which implies the invincibility potion is put into effect for a time period
	 */
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
	public Potions copy() {
		return new InvincibilityPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}