package niriksha;

import java.util.ArrayList;

import eric.CoOrd;
import jae.Enemy;

public class Character {
	
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
		
		// if the character can move
		if (direction == this.icon) {
			
			switch(type) {
			
				case 'H':
				case 'S':
				case 'D':
				case 'C':
					for (int i=0; i < active_potions.size(); i++) {
						if (active_potions.get(i).getType().equals("InvincibiltyPotion")
								&& ((InvincibilityPotion) active_potions.get(i)).turnsRemaining() > 0) {
							((Enemy) object).enemyDies();
							return ACTION.DESTROY;
						}
					}
					destroy_character(this);
					return ACTION.DIE;
					
				// B is pit
				case 'B':
					int flag = -1;
					for (int i=0; i < active_potions.size(); i++) {
						if (active_potions.get(i).getType().equals("HoverPotion")) {
							flag = 0;
							moveCoOrd(direction, border);
						}
					}
					if (flag == -1) {
						destroy_character(this);
						return ACTION.DIE;
					}
					else {
						return ACTION.HOVER;
					}
					
				// C is wall
				case '#':
					return ACTION.NOTHING;
					
				// O is boulder
				case 'O':
					return ACTION.PUSH_BOULDER;
				
				// E is door
				case 'E':
					if (((Door) object).isDoor_open()) {
						moveCoOrd(direction, border);
						return ACTION.MOVE;
					}
					else {
						if (holding_key != null && holding_key.checkDoor((Door) object)) {
							return ACTION.MOVE;
						}
						else {
							return ACTION.NOTHING;
						}
					}
					
				// F is exit
				case 'F':
					moveCoOrd(direction, border);
					return ACTION.GAME_COMPLETE;
					
				// G is treasure
				case 'G':
					((Treasure) object).pickUp();
					moveCoOrd(direction, border);
					return ACTION.MOVE;
					
				default:
					moveCoOrd(direction, border);
					return ACTION.MOVE;
			}
		} 
		
		// only changes direction
		else {
			moveCoOrd(direction, border);
			return ACTION.NOTHING;
		}
	}
	
	/**
	 * Moves the character based on the situation
	 * 
	 * @param direction of movement and the border of the maze
	 * @return the character moves or changes direction it's facing
	 */
	public void moveCoOrd(char movement, int border) {
		
		if (movement == '<') {
			if (this.icon != '<') this.icon = '<';
			else this.co_ord.moveLeft();
		} 
		
		else if (movement == '>') {
			if (this.icon != '>') this.icon = '>';
			else this.co_ord.moveRight(border);
		} 
		
		else if (movement == '^') {
			if (this.icon != '^') this.icon = '^';
			else this.co_ord.moveUp();
		} 
		
		else if (movement == 'v') {
			if (this.icon != 'v') this.icon = 'v';
			else this.co_ord.moveDown(border);
		}
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
		int index = -1;
		
		for (Potions p : this.active_potions) {
			if (p.getType().equals(item)) {
				index = this.active_potions.indexOf(p);
			}
		}
		
		if (index == -1) {
			Potions p = this.bag.getPotion(item);
			this.active_potions.add(p);
			this.bag.deletePotion(p);
			this.usePotion(p);
		}
	}
	
	/**
	 * Calls the action of the potion equipped by the character
	 * 
	 * @param Potion 
	 * @return the effect of potion is called 
	 */
	private void usePotion(Potions p) {
		p.potion_effect();
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
	
	public ArrayList<Potions> getActivePotion() {
		return this.active_potions;
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