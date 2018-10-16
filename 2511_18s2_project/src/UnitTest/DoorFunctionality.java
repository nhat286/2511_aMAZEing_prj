package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Door;

class DoorFunctionality {
	
	static Door door = new Door(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		door.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(door.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		door.setCoordinates(1, 3);
		assertEquals(door.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(door.getIcon(),'E');
	}
	
	@Test
	void testisDoor_open() {
		door = new Door(4, 4);
		assertEquals(door.isDoorOpen(), false);
	}
		
	@Test
	void testopenDoor() {
		door.openDoor();
		assertEquals(door.isDoorOpen(), true);
	}
	
	@Test
	void testgetType() {
		assertEquals(door.getType(), "Door");
	}
	
}
