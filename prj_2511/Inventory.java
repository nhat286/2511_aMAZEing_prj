package prj_2511;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	private ArrayList<SpecialItems> item_list;
	private ArrayList<Weapon> weapon_list;
	
	public Inventory() {
		this.item_list = new ArrayList<SpecialItems>();
		this.weapon_list = new ArrayList<Weapon>();
	}
	
	public HashMap<SpecialItems, Integer> getItemList() {
		HashMap<SpecialItems, Integer> map = new HashMap<>();
		int i=0;
		for (SpecialItems s: item_list) {
			map.put(s, i);
			i++;
		}
		return map;
	}
	
	public HashMap<Weapon, Integer> getWeaponList() {
		HashMap<Weapon, Integer> map = new HashMap<>();
		int i=0;
		for (Weapon s: weapon_list) {
			map.put(s, i);
			i++;
		}
		return map;
	}
	
	public int getTotalItems() {
		return this.item_list.size() + this.weapon_list.size();
	}
	
	public int getNoSpecialItems() {
		return this.item_list.size();
	}
	
	public int getNoWeapons() {
		return this.weapon_list.size();
	}
	
	public void addItem(SpecialItems i) {
		this.item_list.add(i);
	}
	
	public void addWeapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void deleteItem(SpecialItems i) {
		this.item_list.remove(i);
	}
	
	public void deleteWeapon(Weapon w) {
		this.weapon_list.remove(w);
	}
}