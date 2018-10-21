package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.ACTION;
import niriksha.Character;
import niriksha.HoverPotion;
import niriksha.Potion;

class HoverPotionFunctionality {
	
	static HoverPotion potion = new HoverPotion(1, 1, 0);
	static Character user = new Character(1, 1, 0);
	static Character user2 = new Character(2, 1, 0);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		potion.setCoordinates(1, 1);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		HoverPotion potion = new HoverPotion(1, 1, 0);
		CoOrd co_ord = new CoOrd(1,1);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		CoOrd co_ord = new CoOrd(2,3); 
		potion.setCoordinates(2, 3);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		assertEquals(potion.getIcon(), '~');
	}
	
	@Test
	void testpotion_effect() {
		HoverPotion potion = new HoverPotion(1, 1, 0);
		assertEquals(potion.potionEffect(user), "HoverCharacter");
	}
	
	@Test
	void testisUsed() {
		HoverPotion potion = new HoverPotion(2, 2, 0);
		assertEquals(potion.potionEffect(user2), "HoverCharacter");
		assertEquals(potion.isUsed(), true);
	}
	
	@Test
	void testgetType() {
		assertEquals(potion.getType(), "HoverPotion");
	}

}
