package niriksha;

import eric.CoOrd;

public class Character {
	
	// don't know how to store coordinates yet
	private CoOrd co_ord;
	private Inventory bag;
	private char icon;
	private int direction;
	 
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		this.direction = 3;
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}

	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public void move(String movement, Object o, int border) {
		/*if (movement.equals("left")) {
			if (this.direction != 4) this.direction = 4;
			else this.co_ord.moveLeft();
		} else if (movement.equals("right")) {
			if (this.direction != 2) this.direction = 2;
			else this.co_ord.moveRight();
		} else if (movement.equals("up")) {
			if (this.direction != 1) this.direction = 1;
			else this.co_ord.moveUp();
		} else if (movement.equals("down")) {
			if (this.direction != 3) this.direction = 3;
			else this.co_ord.moveDown();
		}*/
		if (movement.equals("left")) {
			if (this.icon != '<') this.icon = '<';
			else this.co_ord.moveLeft();
		} else if (movement.equals("right")) {
			if (this.icon != '>') this.icon = '>';
			else this.co_ord.moveRight(border);
		} else if (movement.equals("up")) {
			if (this.icon != '^') this.icon = '^';
			else this.co_ord.moveUp();
		} else if (movement.equals("down")) {
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
		w.setCoordinates(-2, -2);
	}
	
	public void useWeapon(Object o) {
		
	}
	
	public void pickUpSpecialisedItem(SpecialItems i) {
		this.bag.addItem(i);
		i.setCoordinates(-2, -2);
	}
	
	public void useSpecialisedItem() {
		
	}
	
	public void destroy_character(Character player) {
		player = null;
	}

}
