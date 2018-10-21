package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Coward;
import niriksha.Character;
import niriksha.InvincibilityPotion;

public class CowardFunctionality {
	
	static CoOrd currPos = new CoOrd(3,5);
	static Coward c = new Coward(currPos, 0);
	static Character user = new Character(3, 9, 0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		c.setCurrPos(currPos);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testenemyMovement() {
		
		// calls move closer
		c.enemyMovement(user, 10);
		assertEquals(c.getDirection(), '>');
		
		// tests moving away
		CoOrd c1 = new CoOrd(4,3);
		CoOrd c2 = new CoOrd(4,2);
		c.setDirection('^');
		c.setCurrPos(c1);
		user.setCoordinates(3, 3);
		InvincibilityPotion i = new InvincibilityPotion(0,0);
		user.pickUpPotion(i);
		user.equipPotion(i);
		c.enemyMovement(user,10);
		c.enemyMovement(user,10);
		assertEquals(c.getCurrPos(), c2);
		
	}
	
	@Test
	void testgetEnemyType() {
		assertEquals(c.getEnemyType(), "Coward");
	}
}
