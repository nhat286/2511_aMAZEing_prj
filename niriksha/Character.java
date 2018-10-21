package niriksha_refactored;

import eric.CoOrd;

public class Character {

	private STATE current_state;
	protected static CoOrd co_ord;
	private Inventory bag;
	private char icon;
	public Weapon equip_weapon; 
	public Key holding_key;
	
	public Character(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.bag = new Inventory();
		this.icon = 'v';
		this.equip_weapon = null;
		this.holding_key = null;
		current_state = NormalCharacter.GetInstance();
	}
	
	public void State(STATE value) {  
        this.current_state = value;  
    } 
	
	public ACTION move(char direction, char type, Object object, int border) {
		return current_state.move(direction, type, object, border);
	}
	
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
	
	public CoOrd getInfront() {
		CoOrd co = new CoOrd(this.co_ord.getX(), this.co_ord.getY());
		
		if (this.icon == '^') co.setXY(co.getX() - 1, co.getY());
		else if (this.icon == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (this.icon == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (this.icon == '>') co.setXY(co.getX(), co.getY() + 1);
		
		return co;
	}
	
	public void destroy_character(Character player) {
		player = null;
	}
	
	// dealing with weapons
	
	public void pickUpWeapon(Weapon w) {
		this.bag.addWeapon(w);
		w.setCoordinates(-2, -2);
	}
	
	public void equipWeapon(String item) {
		if (this.equip_weapon == null) {
			this.equip_weapon = this.bag.getWeapon(item);
		}
	}
	
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
	
	public void removeEquipped() {
		this.equip_weapon = null;
	}
	
	// dealing with potions
	
	public void pickUpPotion(Potion p) {
		this.bag.addPotion(p);
		p.setCoordinates(-2, -2);
	}
	
	public void equipPotion(Potion p) {
		p.potion_effect(this.current_state);
	}
	
	// getter & setter methods below 
	
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
	
	public boolean weaponEquipped() {
		return this.equip_weapon != null;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public Inventory getBag() {
		return this.bag;
	}
	
}
