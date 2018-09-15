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

class SwordFunctionality {
	
	static Sword sword = new Sword(3, 3);
	static Sword sword2 = new Sword(3, 5);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sword.setCoordinates(3, 3);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(3, 3);
		assertEquals(sword.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(2,3); 
		sword.setCoordinates(2, 3);
		assertEquals(sword.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(sword.getIcon(), 'T');
	}
	
	@Test
	void testisPicked_up() {
		assertEquals(sword.isPicked_up(), false);
	}
	
	@Test
	void testdestroy_arrow() {
		CoOrd co_ord = new CoOrd(-1, -1);
		sword.destroyWeapon();
		assertEquals(sword.getCoordinates(), co_ord);
	}
	
	@Test
	void testweapon_action() {
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord);
		assertEquals(sword2.weapon_action(e), ACTION.DESTROY);
	}
	
	@Test
	void testgetDurability() {
		assertEquals(sword2.getDurability(), 4);
	}
	
	@Test
	void testgetType() {
		assertEquals(sword2.getType(), "Sword");
	}
}
