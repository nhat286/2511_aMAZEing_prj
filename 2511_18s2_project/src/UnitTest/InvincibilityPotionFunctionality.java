package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.Character;
import niriksha.InvincibilityPotion;
import niriksha.Potion;

class InvincibilityPotionFunctionality {
	
	static InvincibilityPotion potion = new InvincibilityPotion(1, 1, 0);
	static Character user = new Character(1, 1, 0);
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		potion.setCoordinates(1, 1);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testgetCoordinates() {
		CoOrd co_ord = new CoOrd(1,1);
		potion = new InvincibilityPotion(1, 1, 0);
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
		assertEquals(potion.getIcon(), '!');
	}
	
	@Test
	void testpotion_effect() {
		assertEquals(potion.potionEffect(user), "InvincibilityCharacter");
		assertEquals(potion.potionEffect(user), "InvincibilityCharacter");
	}
	
	void testisUsed() {
		assertEquals(potion.isUsed(), true);
	}
	
	@Test
	void testgetType() {
		assertEquals(potion.getType(), "InvincibilityPotion");
	}

}
