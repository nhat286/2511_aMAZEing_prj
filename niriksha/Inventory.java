package niriksha_refactored;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	private ArrayList<Weapon> weapon_list;
	private ArrayList<Potion> potion_list;
	
	public Inventory() {
		this.weapon_list = new ArrayList<Weapon>();
		this.potion_list = new ArrayList<Potion>();
	}

	// dealing with weapons 
	
	public void addWeapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void deleteWeapon(Weapon w) {
		this.weapon_list.remove(w);
	}
	
	// dealing with potions
	
	public void addPotion(Potion p) {
		this.potion_list.add(p);
	}
	
	public void deletePotion(Potion p) {
		this.potion_list.remove(p);
	}

	// getter and setter methods
	
	public ArrayList<Weapon> getWeaponList() {
		return this.weapon_list;
	}
	
	public ArrayList<Potion> getPotionList() {
		return this.potion_list;
	}
	
	public HashMap<String, Integer> getWeaponHashmap() {
		HashMap<String, Integer> map = new HashMap<>();
		for (Weapon i : this.weapon_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	public HashMap<String, Integer> getPotionHashmap() {
		HashMap<String, Integer> map = new HashMap<>();
		for (Potion i : this.potion_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	public int getNoWeapons() {
		return this.weapon_list.size();
	}
	
	public int getNoPotions() {
		return this.potion_list.size();
	}
	
	public Weapon getWeapon(String item) {
		for (Weapon w : this.weapon_list) {
			if (w.getType().equals(item))
				return w;
		}
		return null;
	}
	
	public Potion getPotion(String item) {
		for (Potion p : this.potion_list) {
			if (p.getType().equals(item))
				return p;
		}
		return null;
	}


}
