package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Treasure;

class TreasureFunctionality {
	
	@Test
	void testgetCoordinates() {
		Treasure t = new Treasure(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(t.getCoord(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Treasure t = new Treasure(4, 4);
		CoOrd co_ord = new CoOrd(4,5); 
		t.setCoordinates(4, 5);
		assertEquals(t.getCoord(), co_ord);
	}
	
//	@Test
//	void testgetIcon() {
//		Treasure t = new Treasure(4, 4);
//		assertEquals(t.getIcon(), '*');
//	}
	
	@Test
	void testisPicked_up() {
		Treasure t = new Treasure(4, 4);
		assertEquals(t.isPickedUp(), false);
	}
	
	@Test
	void testpickUp() {
		Treasure t = new Treasure(4, 4);
		t.pickUp();
		assertEquals(t.isPickedUp(), true);
	}
	
//	@Test
//	void testgetPoints() {
//		Treasure t = new Treasure(4, 4);
//		assertEquals(t.getPoints(), 1);
//	}
	
	@Test
	void testgetType() {
		Treasure t = new Treasure(4, 4);
		assertEquals(t.getType(), "Treasure");
	}
	
	@Test
	void testremoveTreasure() {
		Treasure t = new Treasure(4, 4);
		CoOrd co_ord = new CoOrd(-1,-1);
		t.removeTreasure();
		assertEquals(t.getCoord(), co_ord);
	}
	
	

}
