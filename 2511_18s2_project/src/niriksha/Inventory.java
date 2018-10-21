package niriksha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6407195838124302149L;
	private ArrayList<Weapon> weapon_list;
	private ArrayList<Potion> potion_list;
	
	public Inventory() {
		this.weapon_list = new ArrayList<Weapon>();
		this.potion_list = new ArrayList<Potion>();
	}
	
	public ArrayList<Weapon> getWeaponList() {
		return this.weapon_list;
	}
	
	public ArrayList<Potion> getPotionList() {
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
	
	public void addWeapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void addPotion(Potion p) {
		this.potion_list.add(p);
	}
	
	public void deleteWeapon(Weapon w) {
		this.weapon_list.remove(w);
	}
	
	public void deletePotion(Potion p) {
		this.potion_list.remove(p);
	}
}

