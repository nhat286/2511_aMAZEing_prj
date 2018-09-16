package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Door;
class DoorFunctionality {
	
	@Test
	void testgetCoordinates() {
		Door door = new Door(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(door.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Door door = new Door(4, 4);
		CoOrd co_ord = new CoOrd(1,3); 
		door.setCoordinates(1, 3);
		assertEquals(door.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Door door = new Door(4, 4);
		assertEquals(door.getIcon(),'E');
	}
	
	@Test
	void testisDoor_open() {
		Door door = new Door(4, 4);
		assertEquals(door.isDoor_open(), false);
	}
		
	@Test
	void testopenDoor() {
		Door door = new Door(4, 4);
		door.openDoor();
		assertEquals(door.isDoor_open(), true);
	}
	
	@Test
	void testgetType() {
		Door door = new Door(4, 4);
		assertEquals(door.getType(), "Door");
	}
	
}
