package kyle_maze;

import niriksha.Character;
import niriksha.Inventory;
import niriksha.SpecialItems;
import niriksha.Weapon;

public class InventoryMenu implements Menu{
	private Inventory inventory;
	private Character character;
	
	InventoryMenu(Inventory inv,Character cha){
		inventory = inv;
		character = cha;
	}

	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub
		
	}
	
	public void getInventory() {
		
	}
	
	public void dropItem(SpecialItems i) {
		inventory.deleteItem(i);
	}
	
	public void equipWeapon(Weapon w, Object o) {
		character.useWeapon(w, o);
	}
	
	public void useItem(SpecialItems i) {
		character.useSpecialisedItem(i);
	}
}
