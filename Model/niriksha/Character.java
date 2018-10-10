package niriksha;

import java.util.ArrayList;

import eric.CoOrd;

public class Character {
	
	private STATE current_state;
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	//private direction d;
	//private enum direction {UP, RIGHT, DOWN, LEFT}; 
	public Weapon equip_weapon;
	public ArrayList<Potion> active_potions;
	public Key holding_key;
	
	/**
	 * Constructor to instantiate a new character object, by default player always face downwards
	 * @param x valid x co-ordinate of the character on the map
	 * @param y valid y co-ordinate of the character on the map
	 */
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		//this.d = direction.DOWN;
		this.equip_weapon = null;
		this.active_potions = new ArrayList<Potion>();
		this.holding_key = null;
		this.current_state = NormalCharacter.GetInstance();
	}
	
	public void setState(STATE value) {  
        this.current_state = value;  
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
//	public ACTION move(char direction, char type, Object object, int border) {
//		// if the character actually moves then check the object in front
//		if (direction == this.icon) {
//			switch(type) {
//				// Enemies	
//				case 'H':
//				case 'S':
//				case 'D':
//				case 'C':
//					for (int i=0; i < active_potions.size(); i++) {
//						if (active_potions.get(i).getType().equals("InvincibiltyPotion")
//								&& ((InvincibilityPotion) active_potions.get(i)).turnsRemaining() > 0) {
//							((Enemy) object).enemyDies();
//							return ACTION.DESTROY;
//						}
//					}
//					destroy_character(this);
//					return ACTION.DIE;
//				// B is pit
//				case 'B':
//					int flag = -1;
//					for (int i=0; i < active_potions.size(); i++) {
//						if (active_potions.get(i).getType().equals("HoverPotion")) {
//							flag = 0;
//							moveCoOrd(direction, border);
//						}
//					}
//					if (flag == -1) {
//						destroy_character(this);
//						return ACTION.DIE;
//					}
//					else {
//						return ACTION.HOVER;
//					}
//					
//				// # is wall
//				case '#':
//					return ACTION.NOTHING;
//					
//				// O is boulder
//				case 'O':
//					return ACTION.PUSH_BOULDER;
//				
//				// E is door
//				case 'E':
//					if (((Door) object).isDoor_open()) {
//						moveCoOrd(direction, border);
//						return ACTION.MOVE;
//					}
//					else {
//						if (holding_key != null && holding_key.checkDoor((Door) object)) {
//							return ACTION.MOVE;
//						}
//						else {
//							return ACTION.NOTHING;
//						}
//					}
//				// F is exit
//				case 'F':
//					moveCoOrd(direction, border);
//					return ACTION.GAME_COMPLETE;
//				// $ is treasure
//				case '$':
//					((Treasure) object).pickUp();
//					moveCoOrd(direction, border);
//					return ACTION.MOVE;
//				// Other cases (empty space, item drops, weapon drops, keys, treasures)
//				default:
//					moveCoOrd(direction, border);
//					return ACTION.MOVE;
//			}
//		} else {
//			// only changes direction
//			moveCoOrd(direction, border);
//			return ACTION.NOTHING;
//		}
//	}
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
			if (this.icon != '<') this.icon = '<';
			else this.co_ord.moveLeft();
		} else if (movement == '>') {
			if (this.icon != '>') this.icon = '>';
			else this.co_ord.moveRight(border);
		} else if (movement == '^') {
			if (this.icon != '^') this.icon = '^';
			else this.co_ord.moveUp();
		} else if (movement == 'v') {
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
	
	/**
	 * Equips the character with a weapon available in its inventory
	 * 
	 * @param weapon name
	 */
	public void equipWeapon(String item) {
		if (this.equip_weapon == null) {
			this.equip_weapon = this.bag.getWeapon(item);
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
//	public void equipPotion(String item) {
//		int index = -1;
//		for (Potions p : this.active_potions) {
//			if (p.getType().equals(item)) {
//				index = this.active_potions.indexOf(p);
//			}
//		}
//		if (index == -1) {
//			Potions p = this.bag.getPotion(item);
//			this.active_potions.add(p);
//			this.bag.deletePotion(p);
//			this.usePotion(p);
//		}
//	}
	public void equipPotion(Potion p) {
		p.potion_effect(this.current_state);
	}
	
	/**
	 * Calls the action of the potion equipped by the character
	 * 
	 * @param Potion 
	 */
//	private void usePotion(Potion p) {
//		p.potion_effect();
//	}
	
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
	
	/**
	 * Get potions that are being used by character
	 * @return list of active potions being used by character
	 */
	public ArrayList<Potion> getActivePotion() {
		return this.active_potions;
	}
	
	public Inventory getBag() {
		return this.bag;
	}

}
