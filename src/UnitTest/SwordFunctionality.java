package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Enemy;
import jae.Hunter;
import niriksha.ACTION;
import niriksha.Sword;
import niriksha.Weapon;

class SwordFunctionality {
	
	
	@Test
	void testgetCoordinates() {
		Sword sword = new Sword(3, 3);
		CoOrd co_ord = new CoOrd(3, 3);
		assertEquals(sword.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Sword sword = new Sword(3, 3);
		CoOrd co_ord = new CoOrd(2,3); 
		sword.setCoordinates(2, 3);
		assertEquals(sword.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Sword sword = new Sword(3, 3);
		assertEquals(sword.getIcon(), 'T');
	}
	
	@Test
	void testisPicked_up() {
		Sword sword = new Sword(3, 3);
		assertEquals(sword.isPicked_up(), false);
	}
	
//	@Test
//	void testdestroy_arrow() {
//		sword.destroy_sword(sword);
//	}
	
	@Test
	void testweapon_action() {
		Sword sword = new Sword(3, 3);
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord);
		assertEquals(sword.weapon_action(e), Weapon.action.DESTROY);
	}
	
	@Test
	void testgetDurability() {
		Sword sword = new Sword(3, 3);
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord);
		assertEquals(sword.weapon_action(e), Weapon.action.DESTROY);
		assertEquals(sword.getDurability(), 4);
	}
	
	@Test
	void testgetType() {
		Sword sword = new Sword(3, 3);
		assertEquals(sword.getType(), "Sword");
	}
}
