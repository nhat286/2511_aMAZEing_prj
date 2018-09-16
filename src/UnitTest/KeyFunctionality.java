package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Door;
import niriksha.Key;

class KeyFunctionality {
	
	
	@Test
	void testgetCoordinates() {
		Key key = new Key(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(key.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Key key = new Key(4, 4);
		CoOrd co_ord = new CoOrd(5,5); 
		key.setCoordinates(5, 5);
		assertEquals(key.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Key key = new Key(4, 4);
		assertEquals(key.getIcon(), '&');
	}
	
	@Test
	void testcheckDoor() {
		Door door1 = new Door(6,5);
		Door door2 = new Door(4,5);
		Key key = new Key(4, 4);
		key.linkDoor(door1);
		assertEquals(key.checkDoor(door2), false);
		assertEquals(key.checkDoor(door1), true);
	}
	
	@Test
	void testgetType() {
		Door door1 = new Door(6,5);
		Door door2 = new Door(4,5);
		Key key = new Key(4, 4);
		assertEquals(key.getType(), "Key");
	}

}
