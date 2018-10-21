package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Coward;
import niriksha.ACTION;
import niriksha.Character;
import niriksha.HoverPotion;
import niriksha.InvincibilityPotion;
import niriksha.Key;
import niriksha.Pit;
import niriksha.Potion;
import niriksha.Sword;

public class CharacterFunctionality {
	
	static Character user = new Character(0, 0, 0);
	static Character user2 = new Character(1, 0, 0);
	static Character user3 = new Character(5, 0, 0);
	static Character user4 = new Character(6, 0, 0);
	static Character user5 = new Character(7, 0, 0);
	static Character user6 = new Character(8, 0, 0);
	static Key key = new Key(4, 4, 0);
	static CoOrd currPos = new CoOrd(0, 1);
	static Coward c = new Coward(currPos, 0);
	static Sword s = new Sword(9, 10, 0);
	static HoverPotion hp = new HoverPotion(10, 10, 0);
	static InvincibilityPotion ip = new InvincibilityPotion(10, 10, 0);
	static Pit p = new Pit(2,0, 0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		user.setCoordinates(0, 0);
		user2.setCoordinates(1, 0);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(1,0);
		assertEquals(user2.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,1);
		user2.setCoordinates(1, 1);
		assertEquals(user2.getCoordinates(), co_ord);
		user2.setCoordinates(1, 0);
	}
	
	@Test
	void testgetHolding_key() {
		assertEquals(user2.getHoldingKey(), null);
	}
	
	@Test
	void testsetHolding_key() {
		user2.setHoldingKey(key);
		assertEquals(user2.getHoldingKey(), key);
		user2.setHoldingKey(null);
	}
	
	@Test
	void testhasKey() {
		assertEquals(user2.hasKey(), false);
	}
	
	@Test
	void testmove() throws Exception {
		setUpBeforeClass();
		assertEquals(user.move('>', 'C', c, 10), ACTION.NOTHING);
		assertEquals(user.move('>', 'C', c, 10), ACTION.DIE);
		user2.pickUpPotion(hp);
		user2.equipPotion(hp);
		assertEquals(user2.move('v', 'B', p, 10), ACTION.MOVE);
		assertEquals(user2.move('v', ' ', null, 10), ACTION.MOVE);
		user2.setCoordinates(1, 0);
	}
	
	@Test
	void testmoveCoOrd() {
		CoOrd co_ord = new CoOrd(3,3);
		user2.setCoordinates(3, 2);
		user2.moveCoOrd('v', 10, 0);
		assertEquals(user2.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetInfront() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(3,3);
		user2.setCoordinates(3, 2);
		assertEquals(user2.getInfront(), co_ord);
	}
	
	@Test
	void testpickUpWeapon() {
		user3 = new Character(6, 0, 0);
		user3.pickUpWeapon(s);
		assertEquals(user3.getBag().getNoWeapons(), 1);
		user3.removeEquipped();
	}
	
	@Test
	void testequipWeapon() {
		user3.pickUpWeapon(s);
		user3.equipWeapon(s);
		assertEquals(user3.weaponEquipped(), true);
		user3.removeEquipped();
	}
	
	@Test
	void testuseWeapon() {
		user3.pickUpWeapon(s);
		user3.equipWeapon(s);
//		assertEquals(user3.useWeapon(c), 1);
	}
	
	@Test
	void testpickUpPotion() {
		user3.pickUpPotion(ip);
		assertEquals(user3.getBag().getNoPotions(), 1);
	}
	
	@Test
	void testequipPotion() {
		user4.pickUpPotion(hp);
		user4.equipPotion(hp);
		assertEquals(user4.getState(), 1);
	}
	
	@Test
	void testweaponEquipped() {
		user4.pickUpWeapon(s);
		user4.equipWeapon(s);
		assertEquals(user4.weaponEquipped(), true);
		user4.removeEquipped();
		
	}
	
	@Test
	void testremoveEquipped() {
		user4.pickUpWeapon(s);
		user4.equipWeapon(s);
		assertEquals(user4.weaponEquipped(), true);
		user4.removeEquipped();
		assertEquals(user4.weaponEquipped(), false);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(user4.getIcon(), 'v');
	}
	
}
