package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Enemy;
import jae.Hunter;
import niriksha.ACTION;
import niriksha.Arrow;
import niriksha.Character;

class ArrowFunctionality {
	
	static Character user = new Character(1, 1);
	static Arrow arrow = new Arrow(1, 1, user);
	static Arrow arrow2 = new Arrow(3, 5, user);
	static Arrow arrow3 = new Arrow(0, 0, user);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arrow.setCoordinates(1, 1);
		arrow2.setCoordinates(3, 5);
		arrow3.setCoordinates(0, 0);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(3,5);
		assertEquals(arrow2.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		CoOrd co_ord2 = new CoOrd(3,5);
		arrow2.setCoordinates(1,3);
		assertEquals(arrow2.getCoordinates(), co_ord);
		arrow2.setCoordinates(3,5);
		assertEquals(arrow2.getCoordinates(), co_ord2);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(arrow2.getIcon(), '%');
	}
	
	@Test
	void testisPicked_up() {
		assertEquals(arrow2.isPicked_up(), false);
	}
	
	@Test
	void testdestroy_arrow() {
		arrow.destroyWeapon();
	}
	
	@Test
	void testweapon_action() {
		CoOrd co_ord = new CoOrd(3,6);
		Enemy e = new Hunter(co_ord);
		assertEquals(arrow2.weapon_action(e), ACTION.NOTHING);
		arrow2.setCoordinates(3, 5);
	}
	
	@Test
	void testmoving() {
		CoOrd co_ord = new CoOrd(3,4);
		Enemy e = new Hunter(co_ord);
		arrow2.moving(e, 10);
		CoOrd co_ord2 = new CoOrd(-1,-1);
		assertEquals(arrow2.getCoordinates(), co_ord2);
		arrow2.setCoordinates(3, 5);
	}
	
	@Test
	void testgetInfront() {
		CoOrd co_ord = new CoOrd(4,5);
		assertEquals(arrow2.getInfront(), co_ord);
		arrow2.setCoordinates(3, 5);
	}
	
	@Test
	void testgetType() {
		assertEquals(arrow3.getType(), "Arrow");
	}
}
