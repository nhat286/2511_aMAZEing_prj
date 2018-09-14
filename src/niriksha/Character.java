package niriksha;

import java.util.ArrayList;

import eric.CoOrd;
import jae.Enemy;

public class Character {
	
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	//private direction d;
	//private enum direction {UP, RIGHT, DOWN, LEFT}; 
	public Weapon equip_weapon;
	public ArrayList<Potions> active_potions;
	public Key holding_key;
	
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		//this.d = direction.DOWN;
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
	
	public ACTION move(char direction, char type, Object object, int border) {
		// if the character actually moves
		if (direction == this.icon) {
			switch(type) {
				case 'H':
				case 'S':
				case 'D':
				case 'C':
					for (int i=0; i < active_potions.size(); i++) {
						if (active_potions.get(i).getType().equals("InvincibiltyPotion")) {
							usePotion(active_potions.get(i));
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
		} else {
			// only changes direction
			moveCoOrd(direction, border);
			return ACTION.NOTHING;
		}
	}
	
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
	
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
	}
	
	public void equipWeapon(int index) {
		if (this.equip_weapon == null) {
			this.equip_weapon = this.bag.getWeapon(index);
		}
	}
	
	public int useWeapon(Object object) {
		if (this.equip_weapon != null)
			this.equip_weapon.weapon_action(object);
		if (this.equip_weapon instanceof Bomb)
			return 1;
		return 0;
		//this.equip_weapon = null;
	}
	
	public void pickUpPotion(Potions p) {
		this.bag.addPotion(p);
	}
	
	public void equipPotion(int index) {
		if (!this.active_potions.contains(this.bag.getPotion(index))) {
			this.active_potions.add(this.bag.getPotion(index));
			this.usePotion(this.bag.getPotion(index));
		}
	}
	
	private void usePotion(Potions p) {
		p.potion_effect();
	}
	
	public boolean weaponEquipped() {
		return this.equip_weapon != null;
	}
	
	public void removeEquipped() {
		this.equip_weapon = null;
	}
	
	public void destroy_character(Character player) {
		player = null;
	}
	
	public Inventory getBag() {
		return this.bag;
	}

}
