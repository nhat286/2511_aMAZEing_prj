package kyle_maze;

import prj_2511.Inventory;
import prj_2511.SpecialItems;
import prj_2511.Weapon;
import prj_2511.Character;

public class inventoryMenu implements Menu{
	private Inventory inventory;
	private Character character;
	
	inventoryMenu(Inventory inv,Character cha){
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
	
	public void equipWeapon(Weapon w) {
		character.useWeapon(w);
	}
	
	public void useItem(SpecialItems i) {
		character.useSpecialisedItem(i);
	}
}
