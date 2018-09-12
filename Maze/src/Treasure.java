package niriksha;

public class Treasure extends SpecialItems {
	
	boolean picked_up;

	public Treasure(int x, int y, char c) {
		super(x, y, c);
		this.picked_up = false;
	}

	@Override
	public void special_effect() {
		this.picked_up = true;
	}

}
