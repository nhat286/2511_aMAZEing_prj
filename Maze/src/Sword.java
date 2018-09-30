package niriksha;

import jae.Enemy;

public class Sword extends Weapon {

	private int durability;

	public Sword(int x, int y) {
		super(x, y, 'T');
		this.durability = 5;
	}
	
	/**
	 * Decreases durability of the sword
	 * 
	 * @return decrease_durability is lowered by 1
	 */
	private void decrease_durability() {
		this.durability = this.durability - 1;
	}
	
	/**
	 * Enemy is destroyed with the sword if it is right in front of the character 
	 * 
	 * @param object in front of character
	 * @return destroy enemy or character dies if durability of sword is zero or nothing happens
	 */
	@Override
	public action weapon_action(Object object) {
		
		if (this.durability > 0 && object instanceof Enemy) {
			decrease_durability();
			((Enemy) object).enemyDies();
			if (this.durability == 0)
				this.setCoordinates(-1, -1);
			return action.DESTROY;
		}
		
		else if (this.durability == 0 && object instanceof Enemy) {
			return action.DIE;
		}
		
		// decrease as sword is used even if it did nothing;
		decrease_durability();
		return action.NOTHING;
	}

	public int getDurability() {
		return durability;
	}
	
	/**
	 * Creates a copy of this sword
	 * 
	 * @return copy of this sword
	 */
	@Override
	public Weapon copy() {
		return new Sword(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	/**
	 * Returns the type of weapon 
	 * 
	 * @return type of weapon
	 */
	@Override
	public String getType() {
		return "Sword";
	}

}