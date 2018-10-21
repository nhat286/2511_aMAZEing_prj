package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Pit;
import niriksha.Wall;

class PitFunctionality {
static Pit pit = new Pit(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pit.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(pit.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		pit.setCoordinates(1, 3);
		assertEquals(pit.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(pit.getIcon(), 'B');
	}
	
	@Test
	void testgetType() {
		assertEquals(pit.getType(), "Pit");
	}
}
