package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Door;
import niriksha.Key;

class KeyFunctionality {
	
	static Door door1 = new Door(6,5,0);
	static Door door2 = new Door(4,5,0);
	static Key key = new Key(4, 4,0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		key.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
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
		key.linkDoor(door1);
		assertEquals(key.checkDoor(door2,0), false);
		assertEquals(key.checkDoor(door1,0), true);
		assertEquals(key.getCoordinates(), new CoOrd(-1,-1));
	}
	
	@Test
	void testopenDoor() {
		key = new Key(4, 4, 0);
		key.linkDoor(door1);
		assertEquals(key.checkDoor(door1,0), true);
		assertEquals(door1.isDoorOpen(),true);		
	}
	
	@Test
	void testgetType() {
		assertEquals(key.getType(), "Key");
	}

}
