package niriksha;

import java.util.ArrayList;

import eric.CoOrd;

public class Character {
	
	private STATES state;
	
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	public Weapon equip_weapon;
	public ArrayList<Potions> active_potions;
	public Key holding_key;
	
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		this.equip_weapon = null;
		this.active_potions = new ArrayList<Potions>();
		this.holding_key = null;
		state = NormalCharacter.GetInstance();
	}
	
	public void State(STATES value) {  
        this.state = value;  
    }  
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public Key getHolding_key() {
		return holding_key;
	}
	
	public void setHolding_key(Key k) {
		this.holding_key = k;
	}
	
	public boolean hasKey() {
		if (this.holding_key == null) {
			return false;
		}
		return true;
	}

	/**
	 * Prompts movement of the character based on different scenarios
	 * 
	 * @param direction of movement, object in front of character, 
	 * 		  the icon of the object & the border of the maze
	 * @return character moves, pushes boulder, hovers, dies, or does nothing
	 */
	public ACTION move(char direction, char type, Object object, int border) {
		return state.move(direction, type, object, border);
	}
	
	/**
	 * Moves the character based on the situation
	 * 
	 * @param direction of movement and the border of the maze
	 * @return the character moves or changes direction it's facing
	 */
	public void moveCoOrd(char movement, int border) {
		state.moveCoOrd(movement, border);
	}
	
	/**
	 * Determines the coordinate of the location right in front of the character
	 * 
	 * @return returns the coordinate of the position in front of character
	 */
	public CoOrd getInfront() {
		CoOrd co = new CoOrd(this.co_ord.getX(), this.co_ord.getY());
		
		if (this.icon == '^') co.setXY(co.getX() - 1, co.getY());
		else if (this.icon == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (this.icon == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (this.icon == '>') co.setXY(co.getX(), co.getY() + 1);
		
		return co;
	}
	
	/**
	 * Adds the weapon picked-up by the character into the inventory
	 * 
	 * @param the weapon picked-up
	 * @return weapon added to the character's inventory
	 */
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
		w.setCoordinates(-2, -2);
	}
	
	/**
	 * Equips the character with a weapon available in its inventory
	 * 
	 * @param weapon name
	 * @return the weapon is ready to be used by the character
	 */
	public void equipWeapon(String item) {
		if (this.equip_weapon == null) {
			this.equip_weapon = this.bag.getWeapon(item);
		}
	}
	
	/**
	 * Calls the action of the weapon held by the character
	 * 
	 * @param Object 
	 * @return the weapon action is called 
	 */
	public void useWeapon(Object object) {
		if (this.equip_weapon == null) return;
		
		this.equip_weapon.weapon_action(object);
		
		if (this.equip_weapon instanceof Sword) {
			if (((Sword) this.equip_weapon).getDurability() == 0)
				removeEquipped();
		} 
		else {
			this.removeEquipped();
		}
	}
	
	/**
	 * Adds the potion picked-up by the character into the inventory
	 * 
	 * @param the potion picked-up
	 * @return potion added to the character's inventory
	 */
	public void pickUpPotion(Potions p) {
		this.bag.addPotion(p);
		p.setCoordinates(-2, -2);
	}
	
	/**
	 * Equips the character with a potion available in its inventory
	 * 
	 * @param potion name
	 * @return the potion is ready to be used by the character
	 */
	public void equipPotion(String item) {
		state = SpecialEffectCharacter.GetInstance();
		SpecialEffectCharacter.GetInstance().equipPotion(item);
	}
	
	
	public boolean weaponEquipped() {
		return this.equip_weapon != null;
	}
	
	/**
	 * Removes the weapon from the character after its fully used
	 * 
	 * @return equip_weapon is reset to null
	 */
	public void removeEquipped() {
		this.equip_weapon = null;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public Inventory getBag() {
		return this.bag;
	}
	
	/**
	 * Destroys character
	 * 
	 * @return the character is set to null
	 */
	public void destroy_character(Character player) {
		player = null;
	}

}