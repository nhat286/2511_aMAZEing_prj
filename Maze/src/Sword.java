package niriksha;

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
	public action weapon_action(char object) {
		
		if (this.durability >= 0 && object == 'A') {
			decrease_durability();
			return action.DESTROY;
		}
		
		else if (this.durability == 0 && object == 'A') {
			return action.DIE;
		}
		
		// decrease as sword is used even if it did nothing;
		decrease_durability();
		return action.NOTHING;
	}
	
	public void destroy_sword(Weapon w) {
		w = null;
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
