package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Character;

class Movement {
	
	static Character user = new Character(0, 0);
	static CoOrd testChar;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		user.setCoordinates(0, 0);
		testChar = user.getCoordinates();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCoOrd() {
		fail("Not yet implemented");
	}

	@Test
	void testMoveLeft() {
		fail("Not yet implemented");
	}

	@Test
	void testMoveRight() {
		fail("Not yet implemented");
	}

	@Test
	void testMoveUp() {
		fail("Not yet implemented");
	}

	@Test
	void testMoveDown() {
		fail("Not yet implemented");
	}

	@Test
	void testGetX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetY() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCoOrd() {
		fail("Not yet implemented");
	}

	@Test
	void testSetXY() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCoOrd() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

}
