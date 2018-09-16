package kyle_maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import niriksha.Character;
import niriksha.Inventory;
import niriksha.Potions;
import niriksha.Weapon;

public class InventoryMenu implements Menu{
	
	private Character character;
	private Scanner sc;
	
	public InventoryMenu(Character cha,Scanner sc){
		this.character = cha;
		this.sc = sc;
	}
	
	/**
	 * Prints the menu of the game
	 * 
	 * @return prints the information of the Inventory and 
	 * 		   allows the player to use items available in their inventory
	 */
	@Override
	public void displayMenu() {
		
		Inventory inventory = character.getBag();
		String selected;

		ArrayList<Weapon> weaponList = inventory.getWeaponList();
		ArrayList<Potions> potionList = inventory.getPotionList();
		ArrayList<String> currList = new ArrayList<String>();
		
		HashMap<String, Integer> weaponHash = new HashMap<>();
		HashMap<String, Integer> potionHash = new HashMap<>();
		
		Integer i;
		
		char inputType = '.';
		int inputIndex = '.';
		char inputAct = '.';
		
		while (inputType != 'q') {
			
			for (Weapon o : weaponList) {
				if (!weaponHash.containsKey(o.getType())) {
					weaponHash.put(o.getType(), new Integer(0));
				}
				weaponHash.put(o.getType(), new Integer(weaponHash.get(o.getType()).intValue() + 1));
			}
			
			for (Potions o : potionList) {
				if (!potionHash.containsKey(o.getType())) {
					potionHash.put(o.getType(), new Integer(0));
				}
				potionHash.put(o.getType(), new Integer(potionHash.get(o.getType()).intValue() + 1));
			}
			
			i = 0;
			System.out.println("------------------------------------------------------------");
			System.out.println("You have Weapons: ");
			for (Entry<String, Integer> e : weaponHash.entrySet()) {
				System.out.println(i.toString()+". "+e.getKey().toString()+": "+e.getValue().toString());
				currList.add((String) e.getKey());
				i++;
			}
			
			System.out.println("------------------------------------------------------------");
			System.out.println("You have Potions: ");
			for (Entry<String, Integer> e : potionHash.entrySet()) {
				System.out.println(i.toString()+". "+e.getKey().toString()+": "+e.getValue().toString());
				currList.add((String) e.getKey());
				i++;
			}
			
			System.out.println("currList has:");
			for (String s : currList)
				System.out.print(" " + s);
			System.out.print("\n");
			
			System.out.println("------------------------------------------------------------");
			System.out.println("To select a weapon , input w");
			System.out.println("To select a potion , input p");
			System.out.println("------------------------------------------------------------");
			
			inputType = sc.next().charAt(0);
			
			switch (inputType) {
			case 'w':
				System.out.println("Input index");
				inputIndex = sc.nextInt();
				selected = currList.get(inputIndex);
				
				if (inputIndex >= weaponHash.size()) {
					System.out.println("Invalid index, can't find weapon!");
					break;
				}
				
				for(Weapon w : weaponList) {
					if(w.getType().equals(selected)) {
						System.out.println("e: Equip, d: drop");
						inputAct = sc.next().charAt(0);
						if(inputAct == 'e')
							character.equipWeapon(selected);
						if(inputAct == 'd')
							inventory.deleteWeapon(w);
						break;
					}
				}
				
				break;
				
			case 'p':
				System.out.println("Input index");
				inputIndex = sc.nextInt();
				
				if (inputIndex < weaponHash.size() || inputIndex >= weaponHash.size() + potionHash.size()) {
					System.out.println("Invalid index, can't find potion!");
					break;
				}
				
				selected = currList.get(inputIndex);
				
				for(Potions p : potionList) {
					if(p.getType().equals(selected)) {
						System.out.println("e: Use, d: drop");
						inputAct = sc.next().charAt(0);
						if(inputAct == 'e')
							character.equipPotion(selected);
						if(inputAct == 'd')
							inventory.deletePotion(p);
						break;
					}
				}
				
				break;
				
			default:
				break;
			}
			
			weaponHash.clear();
			potionHash.clear();
			currList.clear();
			
		}
		
	}
	
}