import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	ArrayList<SpecialItems> item_list;
	ArrayList<Weapon> weapon_list;
	
	public Inventory() {
		this.item_list = new ArrayList<SpecialItems>();
		this.weapon_list = new ArrayList<Weapon>();
	}
	
	public HashMap<SpecialItems, Integer> get_item_list() {
		HashMap<SpecialItems, Integer> map = new HashMap<>();
		int i=0;
		for (SpecialItems s: item_list) {
			map.put(s, i);
			i++;
		}
		return map;
	}
	
	public HashMap<Weapon, Integer> get_weapon_list() {
		HashMap<Weapon, Integer> map = new HashMap<>();
		int i=0;
		for (Weapon s: weapon_list) {
			map.put(s, i);
			i++;
		}
		return map;
	}
	
	public int no_total_items() {
		return this.item_list.size() + this.weapon_list.size();
	}
	
	public int no_special_items() {
		return this.item_list.size();
	}
	
	public int no_weapon() {
		return this.weapon_list.size();
	}
	
	public void add_special_item(SpecialItems i) {
		this.item_list.add(i);
	}
	
	public void add_weapon(Weapon w) {
		this.weapon_list.add(w);
	}
	
	public void remove_special_item(SpecialItems i) {
		this.item_list.remove(i);
	}
	
	public void remove_weapon(Weapon w) {
		this.weapon_list.remove(w);
	}
}
