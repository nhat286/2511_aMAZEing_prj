package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.FloorSwitch;

class FloorSwitchFunctionality {
	
	static FloorSwitch f_s = new FloorSwitch(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		f_s.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(f_s.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		f_s.setCoordinates(1, 3);
		assertEquals(f_s.getCoordinates(), co_ord);
		f_s.setCoordinates(4, 4);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(f_s.getIcon(), 'I');
	}
	
	@Test
	void testtriggered() {
		assertEquals(f_s.triggered(), false);
	}
	
	@Test
	void testactivateTrigger() {
		f_s.activateTrigger();
		assertEquals(f_s.triggered(), true);
	}
	
	@Test
	void testdeactivateTrigger() {
		f_s.deactivateTrigger();
		assertEquals(f_s.triggered(), false);
	}

	
	@Test
	void testgetType() {
		assertEquals(f_s.getType(), "FloorSwitch");
	}

}