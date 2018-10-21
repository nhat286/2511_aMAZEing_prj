package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Hound;
import jae.Hunter;
import niriksha.Character;

public class HoundFunctionality {
	
	static CoOrd houndPos = new CoOrd(0,0);
	static CoOrd hunterPos = new CoOrd(7,5);
	static Hound h = new Hound(houndPos);
	static Hunter hunter = new Hunter(hunterPos);
	static Character user = new Character(5,5);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		h.setCurrPos(houndPos);
		hunter.setDirection('^');
		h.linkHunter(hunterPos);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testlinkHunter() {
		h.linkHunter(hunterPos);
		assertEquals(h.getHunterCoOrd(), hunterPos);
	}
	
	@Test
	void getHunterCoOrd() {
		CoOrd c = new CoOrd(7,7);
		h.linkHunter(c);
		assertEquals(h.getHunterCoOrd(), c);
		h.linkHunter(hunterPos);
	}
	
	@Test
	void testcalculateTargetLocation() {
		CoOrd c = new CoOrd(3,5);
		assertEquals(h.calculateTargetLocation(user.getCoordinates()), c);
	}
	
	@Test
	void testenemyMovement() {
		CoOrd c = new CoOrd(3,5);
		CoOrd c2 = new CoOrd(4,5);
		h.setCurrPos(c);
		h.enemyMovement(user, 10);
		assertEquals(h.getCurrPos(), c2);
		
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
		assertEquals(h.getEnemyType(), "Hound");
	}

}
