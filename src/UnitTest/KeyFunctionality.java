package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Door;
import niriksha.Key;

class KeyFunctionality {
	
	static Door door1 = new Door(6,5);
	static Door door2 = new Door(4,5);
	static Key key = new Key(4, 4, door1);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		key.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(key.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(5,5); 
		key.setCoordinates(5, 5);
		assertEquals(key.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(key.getIcon(), '&');
	}
	
	@Test
	void testcheckDoor() {
		assertEquals(key.checkDoor(door2), false);
		assertEquals(key.checkDoor(door1), true);
	}
	
	@Test
	void testgetType() {
		assertEquals(key.getType(), "Key");
	}

}
