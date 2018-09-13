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
	
	inventoryMenu(Character cha,Scanner sc){
		this.character = cha;
		this.sc = sc;
	}

	@Override
	public void displayMenu() {
		Inventory inventory = character.getBag();
		HashMap<SpecialItems, Integer> itemHash = inventory.getItemListHash();
		HashMap<Weapon,Integer> weaponHash = inventory.getWeaponListHash();
		HashMap<Potions,Integer> potionHash = inventory.getPotionListHash();

		
		int i = 0;
		
		System.out.println("You have Special Items: ");
		for (Entry e : itemHash.entrySet()) {
			System.out.println(e.getKey().toString()+": "+e.getValue().toString());
		}
		
		ArrayList<Weapon> weaponListTmp = new ArrayList<Weapon>();
		System.out.println("You have Weapons: ");
		for (Entry e : weaponHash.entrySet()) {
			System.out.println(i+' '+e.getKey().toString()+": "+e.getValue().toString());
			weaponListTmp.add((Weapon) e);
		}
		
		i = 0;
		ArrayList<Potions> itemListTmp = new ArrayList<Potions>();
		System.out.println("You have Items: ");
		for (Entry e : potionHash.entrySet()) {
			System.out.println(i+' '+e.getKey().toString()+": "+e.getValue().toString());
			itemListTmp.add((Potions) e);
		}
		
		System.out.println("To select an item , input i");
		System.out.println("To select a weapon , input w");
		char inputType = '.';
		char inputIndex = '.';
		char inputAct = '.';
		while (inputType != 'q') {
			inputType = sc.next().charAt(0);
			switch (inputType) {
			case 'w':
				System.out.println("Input index");
				inputIndex = sc.next().charAt(0);
				Weapon w = weaponListTmp.get(inputIndex);
				System.out.println("e: Equip, d: drop");
				inputAct = sc.next().charAt(0);
				if(inputAct == 'e')
					character.useWeapon(w);
				if(inputAct == 'd')
					inventory.deleteWeapon(w);
				break;
			case 'i':
				System.out.println("Input index");
				inputIndex = sc.next().charAt(0);
				Potions s = itemListTmp.get(inputIndex);
				System.out.println("e: Use, d: drop");
				inputAct = sc.next().charAt(0);
				if(inputAct == 'e')
					character.usePotion(s);
				if(inputAct == 'e')
					inventory.deletePotion(s);
				break;
			default:
				break;
			}
			
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
