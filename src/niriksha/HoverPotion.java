package niriksha;

public class HoverPotion extends Potions {
	
	private boolean used;
	
	public HoverPotion(int x, int y) {
		super(x, y, '~');
		this.used = false;
	}
	
	/**
	 * Allows character to hover over pits
	 * 
	 * @return hover if potion available for use else no effect takes place
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
	
	
	/*@Override
	public int special_effect(Obstacle o) {
		
		if ((o.getType()).equals("Pit")) {
			action = 1;
		}
		else if (o.getType().equals("Wall")) {
			action = 2;
		}
		else if (o.getType().equals("Boulder")) {
			action = 2;
		}
		return action;
	}

	@Override
	public int special_effect(Weapon w) {
		// do nothing
		return 0;
	}

	@Override
	public int special_effect(Enemy e) {
		// do nothing
		return 0;
	}
	*/
	
	/**
	 * Returns the type of potion 
	 * 
	 * @return type of potion
	 */
	@Override
	public String getType() {
		return "HoverPotion";
	}
	
	/**
	 * Creates a copy of this hover potion
	 * 
	 * @return copy of this hover potion
	 */
	@Override
	public Potions copy() {
		return new HoverPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
