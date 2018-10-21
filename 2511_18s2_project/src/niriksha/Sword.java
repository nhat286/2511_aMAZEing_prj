package niriksha;

import application.Sprite;
import jae.Enemy;
import javafx.scene.image.Image;

public class Sword extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117935552171454648L;
	private int durability;

	public Sword(int x, int y) {
		super(x, y, 'T');
		this.durability = 5;
		this.setSprite(new Sprite(new Image("Sword.png"), this.getCoordinates()));
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Sword(int x, int y, int i) {
		super(x, y, 'T');
		this.durability = 5;
	}
	
	/**
	 * Decreases durability of the sword
	 * 
	 */
	private void decreaseDurability() {
		this.durability = this.durability - 1;
	}
	
	/**
	 * Enemy is destroyed with the sword if it is right in front of the character 
	 * 
	 * @param object in front of character
	 * @return destroy enemy or character dies if durability of sword is zero or nothing happens
	 */
	@Override
	public ACTION weapon_action(Object object) {
		
		if (this.durability > 0 && object instanceof Enemy) {
			decreaseDurability();
			((Enemy) object).enemyDies();
			if (this.durability == 0)
				this.destroyWeapon();
			return ACTION.DESTROY;
		}
		
		else if (this.durability == 0 && object instanceof Enemy) {
			return ACTION.DIE;
		}
		
		return ACTION.NOTHING;
	}

	public int getDurability() {
		return this.durability;
	}
	
	/**
	 * Returns the type of weapon 
	 * 
	 * @return type of weapon
	 */
	@Override
	public String getType() {
		return "Sword";
	}
	
	/**
	 * Creates a copy of this sword
	 * 
	 * @return copy of this sword
	 */
	@Override
	public Weapon copy() {
		return new Sword(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("Sword.png"));
	}

}
