package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.ACTION;
import niriksha.Boulder;
import niriksha.FloorSwitch;
import niriksha.Pit;

class BoulderFunctionality {
	
	static Boulder boulder = new Boulder(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		boulder.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(boulder.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		boulder.setCoordinates(1, 3);
		assertEquals(boulder.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(boulder.getIcon(),'D');
	}
	
	@Test
	void testmoveCoOrd() {
		CoOrd co_ord1 = new CoOrd(0,3); 
		boulder.moveCoOrd('<', 10);
		assertEquals(boulder.getCoordinates(), co_ord1);
		
		CoOrd co_ord2 = new CoOrd(1,3); 
		boulder.moveCoOrd('>', 10);
		assertEquals(boulder.getCoordinates(), co_ord2);
	}
	
	@Test
	void testpush_boulder() {
		// floor switch
		FloorSwitch f_s = new FloorSwitch(2, 3);
		assertEquals(boulder.push_boulder('>', 'I', f_s, 10), ACTION.MOVE);
		
		// pit
		Pit p = new Pit(3, 3);
		assertEquals(boulder.push_boulder('>', 'B', p, 10), ACTION.DESTROYED);
	}
	
	@Test
	void testgetType() {
		assertEquals(boulder.getType(), "Boulder");
	}
}