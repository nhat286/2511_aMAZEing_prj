package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class HoverPotion extends Potion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1562680067914774885L;
	private boolean used;
	
	public HoverPotion(int x, int y) {
		super(x, y, '~');
		this.used = false;
		this.setSprite(new Sprite(new Image("bubbly.png"), this.getCoordinates()));
	}
	
	/**
	 * Allows character to hover over pits
	 * 
	 * @return hover if potion available for use else no effect takes place
	 */
	@Override
	public STATE potionEffect(Character c) {
		this.destroyPotion();
		if (c.getState() instanceof InvincibleCharacter) {
			return (STATE) new HoverInvincibleCharacter(c);
		}
		else {
			return (STATE) new HoverCharacter(c);
		}
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

	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("bubbly.png"));
	}
}
