package ass2package;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class EnemyFunctionality {
	CoOrd testCoOrd = new CoOrd(1, 1);
	Enemy testEnemy = new Enemy(6, 6, testCoOrd, "Hunter");
	
	@BeforeAll
	void setUpBeforeClass() throws Exception{
		testEnemy.setCurrPos(new CoOrd(1, 1));
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(1,1);
		assertEquals(testEnemy.getCurrPos(), co_ord)
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(2,5); 
		testEnemy.setCurrPos(new CoOrd(2, 5));
		assertEquals(testEnemy.getCurrPos(), co_ord);
	}
	
	@Test
	void testmoveCoOrd() {
		CoOrd co_ord1 = new CoOrd(5,3); 
		testEnemy.setSpeedX(5);
		assertEquals(testEnemy.getCurrPos(), co_ord1);
		
		CoOrd co_ord2 = new CoOrd(1,8); 
		testEnemy.setSpeedY(7);
		assertEquals(testEnemy.getCurrPos(), co_ord2);
	}
	
	
	@Test
	void testgetType() {
		assertEquals(testEnemy.getType(), "Hunter");
	}
}

