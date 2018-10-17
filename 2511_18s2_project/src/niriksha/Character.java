package niriksha;

//import java.util.ArrayList;

import application.Sprite;
import eric.CoOrd;
import eric.DIRECTION;
import javafx.scene.image.Image;

public class Character {
	
	private STATE current_state;
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	private DIRECTION d;
	private Weapon equip_weapon;
	private Key holding_key;
	private Sprite sprite;
	private Sprite direction;
	
	/**
	 * Constructor to instantiate a new character object, by default player always face downwards
	 * @param x valid x co-ordinate of the character on the map
	 * @param y valid y co-ordinate of the character on the map
	 */
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		this.d = DIRECTION.DOWN;
		this.equip_weapon = null;
		this.holding_key = null;
		this.sprite = new Sprite(new Image("human_new.png"), co_ord);
		this.direction = new Sprite(new Image("dir_south_1.png"), new CoOrd(x, y + 1));
	}
	
	public void setState(STATE value) {  
        this.current_state = value;  
    }
	
	public STATE getState() {
		return this.current_state;
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public Key getHoldingKey() {
		return this.holding_key;
	}
	
	public void setHoldingKey(Key k) {
		this.holding_key = k;
	}
	public boolean hasKey() {
		return this.holding_key != null;
	}
	
	/**
	 * Prompts movement of the character based on different scenarios
	 * 
	 * @param direction of movement, object in front of character, 
	 * 		  the icon of the object & the border of the maze
	 * @return character moves, pushes boulder, hovers, dies, or does nothing
	 */
	public ACTION move(char direction, char type, Object object, int border) {
		return current_state.move(direction, type, object, border);
	}
	
	/**
	 * Moves the character based on the situation
	 * 
	 * @param direction of movement and the border of the maze
	 * @return the character moves or changes direction it's facing
	 */
	public void moveCoOrd(char movement, int border) {
		if (movement == '<') {
			if (this.icon != '<') { this.icon = '<'; this.d = DIRECTION.LEFT; }
			else this.co_ord.moveLeft();
		} else if (movement == '>') {
			if (this.icon != '>') { this.icon = '>'; this.d = DIRECTION.RIGHT; }
			else this.co_ord.moveRight(border);
		} else if (movement == '^') {
			if (this.icon != '^') { this.icon = '^'; this.d = DIRECTION.UP; }
			else this.co_ord.moveUp();
		} else if (movement == 'v') {
			if (this.icon != 'v') { this.icon = 'v'; this.d = DIRECTION.DOWN; }
			else this.co_ord.moveDown(border);
		}
		this.sprite.setPosition(this.co_ord.getX() * 32, this.co_ord.getY() * 32);
	}
	
	public Sprite getDirection() {
		switch (this.d) {
		case UP:
			this.direction.setImage(new Image("dir_north_1.png"));
			this.direction.setPosition(this.co_ord.getX() * 32, (this.co_ord.getY() - 1) * 32);
			break;
		case DOWN:
			this.direction.setImage(new Image("dir_south_1.png"));
			this.direction.setPosition(this.co_ord.getX() * 32, (this.co_ord.getY() + 1) * 32);
			break;
		case LEFT:
			this.direction.setImage(new Image("dir_west_1.png"));
			this.direction.setPosition((this.co_ord.getX() - 1) * 32, this.co_ord.getY() * 32);
			break;
		case RIGHT:
			this.direction.setImage(new Image("dir_east_1.png"));
			this.direction.setPosition((this.co_ord.getX() + 1) * 32, this.co_ord.getY() * 32);
			break;
		}
		return this.direction;
	}
	
	/**
	 * Determines the coordinate of the location right in front of the character
	 * 
	 * @return returns the coordinate of the position in front of character
	 */
	public CoOrd getInfront() {
		CoOrd co = new CoOrd(this.co_ord.getX(), this.co_ord.getY());
//		if (this.icon == '^') co.setXY(co.getX() - 1, co.getY());
//		else if (this.icon == 'v') co.setXY(co.getX() + 1, co.getY());
//		else if (this.icon == '<') co.setXY(co.getX(), co.getY() - 1);
//		else if (this.icon == '>') co.setXY(co.getX(), co.getY() + 1);
		
		if (this.icon == '<') co.setXY(co.getX() - 1, co.getY());
		else if (this.icon == '>') co.setXY(co.getX() + 1, co.getY());
		else if (this.icon == '^') co.setXY(co.getX(), co.getY() - 1);
		else if (this.icon == 'v') co.setXY(co.getX(), co.getY() + 1);
		return co;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	/**
	 * Adds the weapon picked-up by the character into the inventory, and remove its appearance on the map
	 * 
	 * @param the weapon picked-up
	 */
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
		w.setCoordinates(-2, -2);
	}
	
	public Weapon getEquippedWeapon() {
		return this.equip_weapon;
	}
	
	/**
	 * Equips the character with a weapon available in its inventory
	 * 
	 * @param weapon name
	 */
	public void equipWeapon(Weapon w) {
		if (this.equip_weapon == null) {
			this.equip_weapon = w;
		}
	}
	
	/**
	 * Calls the action of the weapon held by the character, and remove it from user's
	 * 		equip slot if it's arrow or bomb or sword that has no durability
	 * 
	 * @param Object to be checked when performing weapon action
	 */
	public void useWeapon(Object object) {
		if (this.equip_weapon == null)
			return;
		this.equip_weapon.weapon_action(object);
		if (this.equip_weapon instanceof Sword) {
			if (((Sword) this.equip_weapon).getDurability() == 0)
				this.removeEquipped();
		} else {
			this.removeEquipped();
		}
		
	}
	
	/**
	 * Adds the potion picked-up by the character into the inventory, and remove its appearance on the map
	 * 
	 * @param the potion picked-up
	 */
	public void pickUpPotion(Potion p) {
		this.bag.addPotion(p);
		p.setCoordinates(-2, -2);
	}
	
	/**
	 * Equips the character with a potion available in its inventory
	 * 
	 * @param potion name
	 */
	public void equipPotion(Potion p) {
		this.current_state = p.potionEffect(this);
	}
	
	public boolean weaponEquipped() {
		return this.equip_weapon != null;
	}
	
	/**
	 * Removes the weapon from the character after its fully used
	 * 
	 */
	public void removeEquipped() {
		this.equip_weapon = null;
	}
	
	/**
	 * Destroys character (set character to null)
	 * 
	 */
	public void destroyCharacter(Character player) {
		player = null;
	}
	
	public Inventory getBag() {
		return this.bag;
	}
	
	public int getVelocity() {
		return this.co_ord.getVelocity();
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
