package niriksha;

public class HoverPotion extends Potions {
	
	private boolean used;
	
	public HoverPotion(int x, int y) {
		super(x, y, '~');
		this.used = false;
	}
	
	/*
	 * Allows character to hover over pits
	 * 
	 * @post: hover if potion available for use else no effect takes place
	 */
	@Override
	public action potion_effect() {
		
		if (this.used == false) {
			this.used = true;
			return action.HOVER;
		}
		
		return action.NOTHING;
	}

	public boolean isUsed() {
		return used;
	}
	
	/*
	 * Returns the type of potion 
	 * 
	 * @post type of potion
	 */
	@Override
	public String getType() {
		return "HoverPotion";
	}
}