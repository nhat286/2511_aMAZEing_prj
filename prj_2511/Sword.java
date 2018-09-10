package prj_2511;

public class Sword extends Weapon {
	
	private int action;
	// 0 == don't destroy
	// 1 == destroy

	private int durability;

	public Sword(int x, int y) {
		super(x, y, 'T');
		this.durability = 5;
	}
	
	private void decrease_durability() {
		this.durability = this.durability - 1;
	}
	
	@Override
	public int action(Enemy e) {
		if (durability>=0) {
			e.destroyEnemy();
			decrease_durability();
			action = 1;
		}
		else {
			action = 0;
		}
		return action;
	}

}