package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Wall;

class WallFunctionality {
	

	@Test
	void testgetCoordinates() {
		Wall wall = new Wall(4, 4);
		CoOrd co_ord = new CoOrd(4,4);
		assertEquals(wall.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		Wall wall = new Wall(4, 4);
		CoOrd co_ord = new CoOrd(1,3); 
		wall.setCoordinates(1, 3);
		assertEquals(wall.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Wall wall = new Wall(4, 4);
		assertEquals(wall.getIcon(), '#');
	}
	
	@Test
	void testgetType() {
		Wall wall = new Wall(4, 4);
		assertEquals(wall.getType(), "Wall");
	}

}
