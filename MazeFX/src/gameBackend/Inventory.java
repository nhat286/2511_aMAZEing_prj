package gameBackend;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	private ArrayList<Weapon> weapon_list;
	private ArrayList<Potions> potion_list;
	
	public Inventory() {
		this.weapon_list = new ArrayList<Weapon>();
		this.potion_list = new ArrayList<Potions>();
	}
	
	public ArrayList<Weapon> getWeaponList() {
		return this.weapon_list;
	}
	
	public ArrayList<Potions> getPotionList() {
		return this.potion_list;
	}
	
	/**
	 * Creates hashmap of the arraylist of weapons
	 * 
	 * @return the hashmap is returned
	 */
	public HashMap<String, Integer> getWeaponHashmap() {
		HashMap<String, Integer> map = new HashMap<>();
		for (Weapon i : this.weapon_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	/**
	 * Creates hashmap of the arraylist of potions
	 * 
	 * @return the hashmap is returned
	 */
	public HashMap<String, Integer> getPotionHashmap() {
		HashMap<String, Integer> map = new HashMap<>();
		for (Potions i : this.potion_list) {
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
	
	public Potions getPotion(String item) {
		for (Potions p : this.potion_list) {
			if (p.getType().equals(item))
				return p;
		}
		return null;
	}
	
	public void addWeapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void addPotion(Potions p) {
		this.potion_list.add(p);
	}
	
	public void deleteWeapon(Weapon w) {
		this.weapon_list.remove(w);
	}
	
	public void deletePotion(Potions p) {
		this.potion_list.remove(p);
	}
}