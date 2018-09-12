package niriksha;

import java.util.ArrayList;

import eric.CoOrd;

public class Character {
	
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	private direction d;
	private enum direction {UP, RIGHT, DOWN, LEFT}; 
	
	public Weapon w;
	public ArrayList<Potions> p;
	public ArrayList<SpecialItems> si;
	
	enum action {
		PUSH_BOULDER, PICK_UP_WEAPON, PICK_UP_POTION, PICK_UP_ITEM,
		MOVE, HOVER, DIE, DESTROY, 
		GAME_COMPLETE, NOTHING;
	}
	 
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = '@';
		this.d = direction.DOWN;
		this.w = null;
		this.p = new ArrayList<Potions>();
		this.si = new ArrayList<SpecialItems>();
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public action move(String s, char object) {
		
		switch(object) {
		
			// nothing in front
			case ' ':
				move_chara(s);
				return action.MOVE;
			
			// A is enemy
			case 'A':
				for (int i=0; i<p.size(); i++) {
					if ((p.get(i).getClass().toString()).equals("InvincibiltyPotion")) {
						usePotion(p.get(i));
						return action.DESTROY;
					}
				}
				if (w != null) {
					useWeapon(w, object);
					return action.DIE;
				}
				else {
					destroy_character(this);
					return action.DIE;
				}
			
			// B is pit
			case 'B':
				int flag = -1;
				for (int i=0; i<p.size(); i++) {
					if ((p.get(i).getClass().toString()).equals("HoverPotion")) {
						flag = 0;
						move_chara(s);
					}
				}
				if (flag == -1) {
					destroy_character(this);
					return action.DIE;
				}
				else {
					return action.HOVER;
				}
				
			// C is wall
			case 'C':
				return action.NOTHING;
				
			// D is boulder
			case 'D':
				return action.PUSH_BOULDER;
			
			// E is door
			case 'E':
				// door open then move else don't move?
				return action.NOTHING;
			
			// F is exit
			case 'F':
				move_chara(s);
				return action.GAME_COMPLETE;
					
			// G is weapon	
			case 'G':
				return action.PICK_UP_WEAPON;
				
			// H is potion
			case 'H':
				return action.PICK_UP_POTION;
				
			case 'I':
				return action.PICK_UP_ITEM; 
			
			default:
				System.out.println("Invalid move\n");
				return action.NOTHING;
		}
	}
	
	public void move_chara(String s) {
		
		if (s == "right") {
			if (this.d == direction.RIGHT) {
				co_ord.moveRight();
			}
			else {
				this.d = direction.RIGHT;
			}
		}
		else if (s == "left") {
			if (this.d == direction.LEFT) {
				co_ord.moveRight();
			}
			else {
				this.d = direction.LEFT;
			}
		}
		else if (s == "up") {
			if (this.d == direction.UP) {
				co_ord.moveRight();
			}
			else {
				this.d = direction.UP;
			}
		}
		else if (s == "down") {
			if (this.d == direction.DOWN) {
				co_ord.moveRight();
			}
			else {
				this.d = direction.DOWN;
			}
		}
		else {
			// do nothing
			System.out.println("Invalid move\n");
		}
		
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
	}
	
	public void useWeapon(Weapon w, char object) {
		if (this.w == null) {
			this.w = w;
			w.weapon_action(object);
			this.w = null;
		}
	}
	
	public void pickUpPotion(Potions p) {
		this.bag.addPotion(p);
	}
	
	public void usePotion(Potions p) {
		if (!this.p.contains(p)) {
			this.p.add(p);
			p.potion_effect();
		}
	}
	
	public void pickUpSpecialisedItem(SpecialItems i) {
		this.bag.addItem(i);
	}
	
	public void useSpecialisedItem(SpecialItems i) {
		this.si.add(i);
		i.special_effect();
	}
	
	public void destroy_character(Character player) {
		player = null;
	}

}


