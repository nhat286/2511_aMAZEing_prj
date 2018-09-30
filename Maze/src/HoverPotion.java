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
	public ACTION potion_effect() {
		
		if (this.used == false) {
			this.used = true;
			return ACTION.HOVER;
		}
		
		return ACTION.NOTHING;
	}

	public boolean isUsed() {
		return used;
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

	
	/**
	 * Returns the type of potion 
	 * 
	 * @return type of potion
	 */
	@Override
	public String getType() {
		return "HoverPotion";
	}
}