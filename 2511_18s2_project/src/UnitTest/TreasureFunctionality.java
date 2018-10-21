package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Treasure;

class TreasureFunctionality {
	
	static Treasure t;// = new Treasure(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//t.setCoordinates(4, 4);
		t = new Treasure(4, 4, 0);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(t.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(4,5); 
		t.setCoordinates(4, 5);
		assertEquals(t.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(t.getIcon(), '$');
	}
	
	@Test
	void testisPicked_up() throws Exception {
		setUpBeforeClass();
		assertEquals(t.isPickedUp(), false);
	}
	
	@Test
	void testpickUp() {
		t.pickUp();
		assertEquals(t.isPickedUp(), true);
	}
	
//	@Test
//	void testgetPoints() {
//		assertEquals(t.getPoints(), 1);
//	}
	
	@Test
	void testgetType() {
		assertEquals(t.getType(), "Treasure");
	}
	
	@Test
	void testremoveTreasure() {
		CoOrd co_ord = new CoOrd(-1,-1);
		t.removeTreasure();
		assertEquals(t.getCoordinates(), co_ord);
	}
	
	

}
