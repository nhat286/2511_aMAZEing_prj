package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.*;

class BoulderFunctionality {
	
	@Test
	void testgetCoordinates() {
		Boulder boulder = new Boulder(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(boulder.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Boulder boulder = new Boulder(4, 4);
		CoOrd co_ord = new CoOrd(1,3); 
		boulder.setCoordinates(1, 3);
		assertEquals(boulder.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Boulder boulder = new Boulder(4, 4);
		assertEquals(boulder.getIcon(),'O');
	}
	
	@Test
	void testmoveCoOrd() {
		Boulder boulder = new Boulder(4, 4);
		CoOrd co_ord1 = new CoOrd(4,4); 
		boulder.moveCoOrd('>', 5);
		assertEquals(boulder.getCoordinates(), co_ord1);
		
		co_ord1 = new CoOrd(3,4); 
		boulder.moveCoOrd('^', 5);
		assertEquals(boulder.getCoordinates(), co_ord1);
		
		co_ord1 = new CoOrd(3,3); 
		boulder.moveCoOrd('<', 5);
		assertEquals(boulder.getCoordinates(), co_ord1);
		
		co_ord1 = new CoOrd(4,3); 
		boulder.moveCoOrd('v', 5);
		assertEquals(boulder.getCoordinates(), co_ord1);
	}
	
	@Test
	void testpush_boulder() {
		Boulder boulder = new Boulder(4, 4);
		// floor switch
		FloorSwitch f_s = new FloorSwitch(2, 3);
		assertEquals(boulder.push_boulder('>', 'I', f_s, 10), Boulder.action.MOVE);
		
		// pit
		Pit p = new Pit(3, 3);
		assertEquals(boulder.push_boulder('>', 'B', p, 10), Boulder.action.DESTROYED);
	}
	
	@Test
	void testgetType() {
		Boulder boulder = new Boulder(4, 4);
		assertEquals(boulder.getType(), "Boulder");
	}
}