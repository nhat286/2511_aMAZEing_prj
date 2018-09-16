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
	static Key key = new Key(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		key.setCoordinates(4, 4);
		key.linkDoor(door1);
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
		key.setCoordinates(4, 4);
	}
	
	@Test
	void testpickUp() {
		CoOrd co_ord = new CoOrd(-2,-2); 
		key.pickUp();
		assertEquals(key.getCoordinates(), co_ord);
		key.setCoordinates(4, 4);
		
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
	void testgetDoorLinked() {
		assertEquals(key.getDoorLinked(), door1);
	}
	
	@Test
	void testgetType() {
		assertEquals(key.getType(), "Key");
	}

}
