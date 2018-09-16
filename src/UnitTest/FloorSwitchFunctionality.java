package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.FloorSwitch;

class FloorSwitchFunctionality {
	
	@Test
	void testgetCoordinates() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(f_s.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		CoOrd co_ord = new CoOrd(1,3); 
		f_s.setCoordinates(1, 3);
		assertEquals(f_s.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		assertEquals(f_s.getIcon(), 'I');
	}
	
	@Test
	void testtriggered() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		assertEquals(f_s.triggered(), false);
	}
	
	@Test
	void testactivateTrigger() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		f_s.activateTrigger();
		assertEquals(f_s.triggered(), true);
	}
	
	@Test
	void testdeactivateTrigger() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		f_s.deactivateTrigger();
		assertEquals(f_s.triggered(), false);
	}

	
	@Test
	void testgetType() {
		FloorSwitch f_s = new FloorSwitch(4, 4);
		assertEquals(f_s.getType(), "FloorSwitch");
	}

}