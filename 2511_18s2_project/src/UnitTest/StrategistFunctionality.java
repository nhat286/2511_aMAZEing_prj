package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Strategist;
import niriksha.Character;

public class StrategistFunctionality {
	
	static CoOrd currPos = new CoOrd(3,5);
	static Strategist s = new Strategist(currPos);
	static Character user = new Character(5,5);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s.setCurrPos(currPos);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testenemyMovement() {
		CoOrd c = new CoOrd(4,5);
		s.enemyMovement(user, 10);
		assertEquals(s.getCurrPos(), c);
	}
	
	@Test
	void testmoveCloser() {
		CoOrd c = new CoOrd(3,5);
		CoOrd c2 = new CoOrd(4,5);
		s.setCurrPos(c);
		s.setDirection('v');
		s.moveCloser(s.getCurrPos(), user.getCoordinates(), 10);
		assertEquals(s.getCurrPos(), c2);
	}
	
	@Test
	void testgetEnemyType() {
		assertEquals(s.getEnemyType(), "Strategist");
	}
	
}

