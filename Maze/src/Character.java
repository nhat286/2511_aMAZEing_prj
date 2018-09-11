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
	public ArrayList<SpecialItems> si;
	 
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = '@';
		this.d = direction.DOWN;
		this.w = null;
		this.si = new ArrayList<SpecialItems>();
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public void move(String s, char object) {
		
		switch(s) {
		
			case "right":
				if (this.d == direction.RIGHT) {
					if (object == ' ') {
						co_ord.moveRight();
					}
				}
				else {
					this.d = direction.RIGHT;
				}
				break;
				
			case "left":
				if (this.d == direction.LEFT) {
					if (object == ' ') {
						co_ord.moveLeft();
					}
				}
				else {
					this.d = direction.LEFT;
				}
				break;
				
			case "up":
				if (this.d == direction.UP) {
					if (object == ' ') {
						co_ord.moveUp();
					}
				}
				else {
					this.d = direction.UP;
				}
				break;
				
			case "down":
				if (this.d == direction.DOWN) {
					if (object == ' ') {
						co_ord.moveDown();
					}
				}
				else {
					this.d = direction.DOWN;
				}
				break;
				
			default:	
				System.out.println("Invalid move\n");
		}
		
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
	}
	
	public void useWeapon(Weapon w) {
		this.w = w;
		w.weapon_action();
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

