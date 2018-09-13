package kyle_maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import eric.Enemy;
import niriksha.Character;
import niriksha.Inventory;
import niriksha.Potions;
import niriksha.SpecialItems;
import niriksha.Weapon;

public class inventoryMenu implements Menu{
	private Character character;
	private Scanner sc;
	
	public inventoryMenu(Character cha,Scanner sc){
		this.character = cha;
		this.sc = sc;
	}

	@Override
	public void displayMenu() {
		Inventory inventory = character.getBag();
		String selected;

		ArrayList<Weapon> weaponList = inventory.getWeapon_list();
		ArrayList<Potions> potionList = inventory.getPotion_list();
		ArrayList<SpecialItems> itemList = inventory.getItem_list();
		ArrayList<String> currList = new ArrayList<String>();
		
		HashMap<String, Integer> weaponHash = new HashMap<>();
		HashMap<String, Integer> itemHash = new HashMap<>();
		HashMap<String, Integer> potionHash = new HashMap<>();
		
		Integer i;
		
		
		char inputType = '.';
		int inputIndex = '.';
		char inputAct = '.';
		while (inputType != 'q') {
			for (Weapon o : weaponList) {
				if (!weaponHash.containsKey(o.getType()))
					weaponHash.put(o.getType(), new Integer(0));
				weaponHash.put(o.getType(), new Integer(weaponHash.get(o.getType()).intValue() + 1));
			}
			
			for (Potions o : potionList) {
				if (!potionHash.containsKey(o.getType()))
					potionHash.put(o.getType(), new Integer(0));
				potionHash.put(o.getType(), new Integer(potionHash.get(o.getType()).intValue() + 1));
			}
			
			for (SpecialItems o : itemList) {
				if (!itemHash.containsKey(o.getType()))
					itemHash.put(o.getType(), new Integer(0));
				itemHash.put(o.getType(), new Integer(itemHash.get(o.getType()).intValue() + 1));
			}
			
			i = 0;
			System.out.println("You have Special Items: ");
			for (Entry e : itemHash.entrySet()) {
				System.out.println(i.toString()+". "+e.getKey().toString()+": "+e.getValue().toString());
				currList.add((String) e.getKey());
				i++;
			}
			System.out.println("------------------------------------------------------------");
			System.out.println("You have Weapons: ");
			for (Entry e : weaponHash.entrySet()) {
				System.out.println(i.toString()+". "+e.getKey().toString()+": "+e.getValue().toString());
				currList.add((String) e.getKey());
				i++;
			}
			System.out.println("------------------------------------------------------------");
			System.out.println("You have Potions: ");
			for (Entry e : potionHash.entrySet()) {
				System.out.println(i.toString()+". "+e.getKey().toString()+": "+e.getValue().toString());
				currList.add((String) e.getKey());
				i++;
			}
			
			itemHash.clear();
			weaponHash.clear();
			potionHash.clear();
			
			
			System.out.println("------------------------------------------------------------");
			System.out.println("To select a special item , input s");
			System.out.println("To select a weapon , input w");
			System.out.println("To select a potion , input p");
			System.out.println("------------------------------------------------------------");
			inputType = sc.next().charAt(0);
			switch (inputType) {
			case 'w':
				System.out.println("Input index");
				inputIndex = sc.nextInt();
				selected = currList.get(inputIndex);
				for(Weapon w : weaponList) {
					if(w.getType().equals(selected)) {
						System.out.println("e: Equip, d: drop");
						inputAct = sc.next().charAt(0);
						if(inputAct == 'e')
							character.useWeapon(w);
						if(inputAct == 'd')
							inventory.deleteWeapon(w);
						break;
					}
				}
				break;
			case 'p':
				System.out.println("Input index");
				inputIndex = sc.nextInt();
				selected = currList.get(inputIndex);
				for(Potions p : potionList) {
					if(p.getType().equals(selected)) {
						System.out.println("e: Use, d: drop");
						inputAct = sc.next().charAt(0);
						if(inputAct == 'e')
							character.usePotion(p);
						if(inputAct == 'd')
							inventory.deletePotion(p);
						break;
					}
				}
				break;
			case 's':
				System.out.println("Input index");
				inputIndex = sc.nextInt();
				selected = currList.get(inputIndex);
				for(SpecialItems s : itemList) {
					if(s.getType().equals(selected)) {
						System.out.println("e: Use, d: drop");
						inputAct = sc.next().charAt(0);
						if(inputAct == 'e')
							character.useSpecialisedItem();
						if(inputAct == 'd')
							inventory.deleteItem(s);;
						break;
					}
				}
				break;
			default:
				break;
			}
			currList.clear();
			
		}
		
	}
	
//	public void dropItem(SpecialItems i) {
//		Inventory inventory = character.getBag();
//		inventory.deleteItem(i);
//	}
//	
//	public void equipWeapon(Weapon w) {
//		character.useWeapon(w);
//	}
//	
//	public void useItem(SpecialItems i) {
//		character.useSpecialisedItem();
//	}
}
