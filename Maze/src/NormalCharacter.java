package niriksha;

import java.util.ArrayList;

import eric.CoOrd;

public class NormalCharacter implements STATES {
	
	 private static NormalCharacter instance = new NormalCharacter();
	 private CoOrd co_ord;
	 private char icon;
	 public Weapon equip_weapon;
	 public Key holding_key;
	 
	 public NormalCharacter() {
	 }
		
	 public static NormalCharacter GetInstance() {
		 return instance; 
	 } 

	@Override
	public ACTION move(char direction, char type, Object object, int border) {
		
		// if the character can move
		if (direction == this.icon) {
			
			switch(type) {
				
				// hunter, strategist, hound, coward
				case 'H':
				case 'S':
				case 'D':
				case 'C':
					destroy_character(this);
					return ACTION.DIE;
					
					
				// pit
				case 'B':
					return ACTION.DIE;
					
				// wall
				case '#':
					return ACTION.NOTHING;
					
				// boulder
				case 'O':
					return ACTION.PUSH_BOULDER;
				
				// door
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

	@Override
	public ArrayList<Potions> getActivePotion() {
		return null;
	}
	
	public void destroy_character(NormalCharacter player) {
		player = null;
	}
}