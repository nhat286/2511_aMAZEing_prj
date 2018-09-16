package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Wall;

class WallFunctionality {
	
	static Wall wall = new Wall(4, 4);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		wall.setCoordinates(4, 4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(wall.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(1,3); 
		wall.setCoordinates(1, 3);
		assertEquals(wall.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(wall.getIcon(), '#');
	}
	
	@Test
	void testgetType() {
		assertEquals(wall.getType(), "Wall");
	}

}
