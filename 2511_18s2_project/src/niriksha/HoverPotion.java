package niriksha;

public class HoverPotion extends Potion {
	
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
	public STATE potion_effect(Character c) {
		return (STATE) new HoverCharacter(c);
	}

	public boolean isUsed() {
		return used;
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
	
	/**
	 * Creates a copy of this hover potion
	 * 
	 * @return copy of this hover potion
	 */
	@Override
	public Potion copy() {
		return new HoverPotion(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
