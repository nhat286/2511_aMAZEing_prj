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
	//static 
	
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
		sword.setCoordinates(3, 3);
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
	void testweapon_action() {
		CoOrd co_ord = new CoOrd(3,4);
		Enemy e = new Hunter(co_ord);
		assertEquals(sword.weapon_action(e), ACTION.DESTROY);
	}
	
	@Test
	void testgetDurability() {
		Sword sword2 = new Sword(3, 5);
		assertEquals(sword2.getDurability(), 5);
	}
	
	@Test
	void testgetType() {
		assertEquals(sword.getType(), "Sword");
	}
}
