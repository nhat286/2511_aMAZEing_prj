package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.ACTION;
import niriksha.InvincibilityPotion;

class InvincibilityPotionFunctionality {
	
	static InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		
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
		assertEquals(potion.potion_effect(), ACTION.INVINCIBLE);
	}
	
	void testisUsed() {
		assertEquals(potion.isUsed(), true);
	}
	
	@Test
	void testgetType() {
		assertEquals(potion.getType(), "InvincibilityPotion");
	}

}
