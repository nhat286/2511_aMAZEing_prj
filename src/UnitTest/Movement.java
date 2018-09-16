package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Character;

class Movement {
	
	static Character user = new Character(0, 0);
	//static CoOrd testChar;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		user.setCoordinates(0, 0);
		//testChar = user.getCoordinates();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCoOrd() {
		CoOrd co_ord = new CoOrd(0,0);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	@Test
	void testMoveRight() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(0,1);
		CoOrd change_direction = new CoOrd(0, 0);
		user.moveCoOrd('>', 10);
		assertEquals(user.getCoordinates(), change_direction);
		user.moveCoOrd('>', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}

	@Test
	void testMoveLeft() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(0,0);
		user.moveCoOrd('<', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	@Test
	void testMoveDown() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(1,0);
		user.moveCoOrd('v', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	@Test
	void testMoveUp() throws Exception {
		setUpBeforeClass();
		CoOrd co_ord = new CoOrd(0,0);
		user.moveCoOrd('^', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}

	@Test
	void testGetX() {
		assertEquals(user.getCoordinates().getX(), 0);
	}

	@Test
	void testGetY() {
		assertEquals(user.getCoordinates().getY(), 0);
	}

	@Test
	void testGetCoOrd() {
		CoOrd co_ord = new CoOrd(0,0);
		assertEquals(co_ord.getCoOrd(), co_ord);
	}

	@Test
	void testSetXY() {
		CoOrd co_ord = new CoOrd(0,0);
		CoOrd co_ord2 = new CoOrd(2,2);
		co_ord.setXY(2, 2);
		assertEquals(co_ord, co_ord2);
	}

	@Test
	void testSetCoOrd() {
		CoOrd co_ord = new CoOrd(0,0);
		CoOrd co_ord2 = new CoOrd(2,2); 
		co_ord.setCoOrd(co_ord2);
		assertEquals(co_ord, co_ord2);
	}

	@Test
	void testEqualsObject() {
		// i am not sure what's happening here
	}

}
