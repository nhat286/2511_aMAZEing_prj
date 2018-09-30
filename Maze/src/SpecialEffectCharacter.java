package niriksha;

import java.util.ArrayList;

import eric.CoOrd;
import jae.Enemy;

public class SpecialEffectCharacter implements STATES {
	
	private static SpecialEffectCharacter instance = new SpecialEffectCharacter();
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	public Weapon equip_weapon;
	public ArrayList<Potions> active_potions;
	public Key holding_key;
	
	public SpecialEffectCharacter() {}
	
	public static SpecialEffectCharacter GetInstance() {
		return instance;
	}

	/**
	 * Prompts movement of the character based on different scenarios
	 * 
	 * @param direction of movement, object in front of character, 
	 * 		  the icon of the object & the border of the maze
	 * @return character moves, pushes boulder, hovers, dies, or does nothing
	 */
	@Override
	public ACTION move(char direction, char type, Object object, int border) {
		
		// if the character can move
		if (direction == this.icon) {
			
			switch(type) {
			
				case 'H':
				case 'S':
				case 'D':
				case 'C':
					((Enemy) object).enemyDies();
					return ACTION.DESTROY;
					
					
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
	@Override
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
	
	@Override
	public ArrayList<Potions> getActivePotion() {
		return this.active_potions;
	}
	
	/**
	 * Destroys character
	 * 
	 * @return the character is set to null
	 */
	public void destroy_character(SpecialEffectCharacter player) {
		player = null;
	}


}
