
public class Sword extends Weapon {

	int durability;

	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}
	
	private void decrease_durability() {
		this.durability = this.durability - 1;
	}
	
	@Override
	public int action() {
		if (durability>=0) {
			/* if (enemy.getX() == Weapon.getX() + 1) {
			 * 	destroy enemy; 
			 * }
			 */
			decrease_durability();
		}
		return this.durability;
	}

}
