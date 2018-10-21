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
	static Strategist s = new Strategist(currPos, 0);
	static Character user = new Character(5,5,0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s.setCurrPos(currPos);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testenemyMovement() {
		CoOrd c = new CoOrd(3,5);
		s.enemyMovement(user, 10);
		assertEquals(s.getCurrPos(), c);
	}
	
	@Test
	void testgetEnemyType() {
		assertEquals(s.getEnemyType(), "Strategist");
	}
	
}

