package niriksha;

import java.util.ArrayList;
import java.util.HashMap;

import jae.Enemy;

public class Inventory {
	
	private ArrayList<SpecialItems> item_list;
	private ArrayList<Weapon> weapon_list;
	private ArrayList<Potions> potion_list;
	
	public Inventory() {
		this.item_list = new ArrayList<SpecialItems>();
		this.weapon_list = new ArrayList<Weapon>();
		this.potion_list = new ArrayList<Potions>();
	}
	
	public HashMap<String, Integer> getItemList() {
		HashMap<String, Integer> map = new HashMap<>();
		/*int i=0;
		for (SpecialItems s: item_list) {
			map.put(s, i);
			i++;
		}*/
		for (SpecialItems i : this.item_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	public HashMap<String, Integer> getWeaponList() {
		HashMap<String, Integer> map = new HashMap<>();
		/*int i=0;
		for (Weapon s: weapon_list) {
			map.put(s, i);
			i++;
		}*/
		for (Weapon i : this.weapon_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	public HashMap<String, Integer> getPotionList() {
		HashMap<String, Integer> map = new HashMap<>();
		/*int i=0;
		for (Potions s: potion_list) {
			map.put(s, i);
			i++;
		}*/
		for (Potions i : this.potion_list) {
			if (!map.containsKey(i.getType()))
				map.put(i.getType(), new Integer(0));
			map.put(i.getType(), new Integer(map.get(i.getType()).intValue() + 1));
		}
		return map;
	}
	
	public int getTotalItems() {
		return this.item_list.size() + this.weapon_list.size() + this.potion_list.size();
	}
	
	public int getNoSpecialItems() {
		return this.item_list.size();
	}
	
	public int getNoWeapons() {
		return this.weapon_list.size();
	}
	
	public int getNoPotions() {
		return this.potion_list.size();
	}
	
	public void addItem(SpecialItems i) {
		this.item_list.add(i);
	}
	
	public void addWeapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void addPotion(Potions p) {
		this.potion_list.add(p);
	}
	
	public void deleteItem(SpecialItems i) {
		this.item_list.remove(i);
	}
	
	public void deleteWeapon(Weapon w) {
		this.weapon_list.remove(w);
	}
	
	public void deletePotion(Potions p) {
		this.potion_list.remove(p);
	}
}

