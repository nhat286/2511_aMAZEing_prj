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

class ArrowFunctionality {
	
	static Arrow arrow = new Arrow(1, 1);
	static Arrow arrow2 = new Arrow(3, 5);
	static Arrow arrow3 = new Arrow(0, 0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arrow.setCoordinates(1, 1);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		Arrow arrow = new Arrow(1, 1);
		CoOrd co_ord = new CoOrd(1,1);
		assertEquals(arrow.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		arrow.setCoordinates(1, 3);
		assertEquals(arrow.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(arrow.getIcon(), '%');
	}
	
	@Test
	void testisPicked_up() {
		assertEquals(arrow.isPicked_up(), false);
	}
	
	@Test
	void testdestroy_arrow() {
		arrow.destroy_arrow(arrow);
		assertEquals(arrow,null);
	}
	
	@Test
	void testweapon_action() {
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord);
		assertEquals(arrow2.weapon_action(e), ACTION.DESTROY);
	}
	
	@Test
	void testgetType() {
		assertEquals(arrow3.getType(), "Arrow");
	}
}
