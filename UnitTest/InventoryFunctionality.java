package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import niriksha.Arrow;
import niriksha.Character;
import niriksha.HoverPotion;
import niriksha.Inventory;
import niriksha.InvincibilityPotion;
import niriksha.Potions;
import niriksha.Sword;
import niriksha.Weapon;

public class InventoryFunctionality {
	
	static Inventory bag = new Inventory();
	static Character user = new Character(0, 0);
	static Arrow a1 = new Arrow(1, 1, user);
	static Arrow a2 = new Arrow(2, 1, user);
	static Sword s1 = new Sword(1, 2);
	static Sword s2 = new Sword(2, 2);
	static HoverPotion h1 = new HoverPotion(1,3);
	static HoverPotion h2 = new HoverPotion(3,1);
	static InvincibilityPotion i1 = new InvincibilityPotion(1,4);
	static InvincibilityPotion i2 = new InvincibilityPotion(4,1);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bag.addPotion(h1);
		bag.addPotion(h2);
		bag.addPotion(i1);
		
		bag.addWeapon(a1);
		bag.addWeapon(a2);
		bag.addWeapon(s1);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetWeaponList() {
		ArrayList<Weapon> a = new ArrayList<Weapon>();
		a.add(a1);
		a.add(a2);
		a.add(s1);
		
		assertEquals(bag.getWeaponList(), a);
	}
	
	@Test
	void testgetPotionList() {
		ArrayList<Potions> a = new ArrayList<Potions>();
		a.add(h1);
		a.add(h2);
		a.add(i1);
		
		assertEquals(bag.getPotionList(), a);
	}
	
	@Test
	void testgetWeaponHashmap() {
		// to create a hashmap wont i be basically using the function?
	}
	
	@Test
	void testgetPotionHashmap() {
		
	}
	
	@Test
	void testgetNoWeapons() {
		assertEquals(bag.getNoWeapons(), 3);
	}
	
	@Test
	void testgetNoPotions() {
		assertEquals(bag.getNoPotions(), 3);
	}
	
	@Test
	void testgetWeapon() {
		assertEquals(bag.getWeapon("Sword"), s1);
		assertEquals(bag.getWeapon("Bomb"), null);
	}
	
	@Test
	void testgetPotion() {
		assertEquals(bag.getPotion("HoverPotion"), h1);
	}
	
	@Test
	void testaddWeapon() {
		bag.addWeapon(s2);
		assertEquals(bag.getNoWeapons(), 4);
		bag.deleteWeapon(s2);
	}
	
	@Test
	void testaddPotion() {
		bag.addPotion(i2);
		assertEquals(bag.getNoPotions(), 4);
		bag.deletePotion(i2);
	}
	
	@Test
	void testdeleteWeapon() {
		bag.deleteWeapon(s1);
		assertEquals(bag.getNoWeapons(), 2);
		bag.addWeapon(s1);
	}
	
	@Test
	void testdeletePotion() {
		bag.deletePotion(i1);
		assertEquals(bag.getNoPotions(), 2);
		bag.addPotion(i1);
	}
}
