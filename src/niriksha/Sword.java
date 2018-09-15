package niriksha;

import jae.Enemy;

public class Sword extends Weapon {

	private int durability;

	public Sword(int x, int y) {
		super(x, y, 'T');
		this.durability = 5;
	}
	
	private void decrease_durability() {
		this.durability = this.durability - 1;
	}
	
	@Override
	public ACTION weapon_action(Object object) {
		
		if (this.durability > 0 && object instanceof Enemy) {
			decrease_durability();
			((Enemy) object).enemyDies();
			if (this.durability == 0)
				this.setCoordinates(-1, -1);
			return ACTION.DESTROY;
		}
		
		else if (this.durability == 0 && object instanceof Enemy) {
			return ACTION.DIE;
		}
		
		// decrease as sword is used even if it did nothing;
		decrease_durability();
		return ACTION.NOTHING;
	}

	public int getDurability() {
		return durability;
	}
	
	
	/*@Override
	public int action() {
		if (durability>=0) {
			e.destroyEnemy();
			decrease_durability();
			action = 1;
		}
		else {
			action = 0;
		}
		return action;
	}*/

}
