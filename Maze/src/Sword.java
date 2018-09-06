
public class Sword extends Weapon {

	int durability;

	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}
	
	public void decrease_durability() {
		this.durability = this.durability - 1;
	}
	
	public void action() {
		
	}

}
