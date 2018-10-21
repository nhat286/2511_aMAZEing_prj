package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Hunter;
import niriksha.Character;

public class HunterFunctionality {
	
	static CoOrd currPos = new CoOrd(3,5);
	static Hunter h = new Hunter(currPos);
	static Character user = new Character(5,5);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		h.setCurrPos(currPos);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testenemyMovement() {
		CoOrd c = new CoOrd(4,5);
		h.enemyMovement(user, 10);
		assertEquals(h.getCurrPos(), c);
	}
	
	@Test
	void testmoveCloser() {
		CoOrd c = new CoOrd(3,5);
		CoOrd c2 = new CoOrd(4,5);
		h.setCurrPos(c);
		h.setDirection('v');
		h.moveCloser(h.getCurrPos(), user.getCoordinates(), 10);
		assertEquals(h.getCurrPos(), c2);
	}
	
	@Test
	void testgetEnemyType() {
		assertEquals(h.getEnemyType(), "Hunter");
	}

}
