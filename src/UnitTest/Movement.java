package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Character;

class Movement {

	//static CoOrd testChar;
	
	
	@Test
	void testCoOrd() {
		Character user = new Character(0, 0);
		CoOrd co_ord = new CoOrd(0,0);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	//The default icon is "v", so all the movement test should consider that.
	@Test
	void testMoveRight() {
		Character user = new Character(2, 2);
		CoOrd co_ord = new CoOrd(2,3);
		user.moveCoOrd('>', 10);
		user.moveCoOrd('>', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}

	@Test
	void testMoveLeft() {
		Character user = new Character(2, 2);
		CoOrd co_ord = new CoOrd(2,1);
		user.moveCoOrd('<', 10);
		user.moveCoOrd('<', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	@Test
	void testMoveDown() {
		Character user = new Character(2, 2);
		CoOrd co_ord = new CoOrd(3,2);
		user.moveCoOrd('v', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}
	
	@Test
	void testMoveUp() {
		Character user = new Character(2, 2);
		CoOrd co_ord = new CoOrd(1,2);
		user.moveCoOrd('^', 10);
		user.moveCoOrd('^', 10);
		assertEquals(user.getCoordinates(), co_ord);
	}

	@Test
	void testGetX() {
		Character user = new Character(0, 0);
		assertEquals(user.getCoordinates().getX(), 0);
	}

	@Test
	void testGetY() {
		Character user = new Character(0, 0);
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
