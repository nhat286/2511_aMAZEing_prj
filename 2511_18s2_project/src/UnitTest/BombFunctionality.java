package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Enemy;
import jae.Hunter;
import niriksha.ACTION;
import eric.Bomb;

class BombFunctionality {
	
	static Bomb bomb = new Bomb(3, 5, null, 0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bomb.setCoordinates(3, 5);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(3,5);
		assertEquals(bomb.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(5,3); 
		bomb.setCoordinates(5, 3);
		assertEquals(bomb.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(bomb.getIcon(), 'Q');
	}
	
	@Test
	void testisPicked_up() {
		assertEquals(bomb.isPicked_up(), false);
	}
	
	@Test
	void testisLit() {
		bomb = new Bomb(4, 5, new CoOrd(5,5), 0);
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord, 0);
		assertEquals(bomb.isLit(), false);
		bomb.weapon_action(e, 0);
		assertEquals(bomb.isLit(), true);
	}
	
	@Test
	void testweapon_action() {
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord, 0);
		bomb = new Bomb(4, 5, new CoOrd(5,5), 0);
		assertEquals(bomb.weapon_action(e, 0), ACTION.NOTHING);
		assertEquals(bomb.weapon_action(e, 0), ACTION.DESTROY);
	}
	
	@Test
	void testgetType() {
		assertEquals(bomb.getType(), "Bomb");
	}
}
