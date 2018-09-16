package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.ACTION;
import niriksha.InvincibilityPotion;
import niriksha.Potions;

class InvincibilityPotionFunctionality {
	
		
	@Test
	void testgetCoordinates() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		CoOrd co_ord = new CoOrd(1,1);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		CoOrd co_ord = new CoOrd(2,3); 
		potion.setCoordinates(2, 3);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		assertEquals(potion.getIcon(), '!');
	}
	
	@Test
	void testpotion_effect() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		assertEquals(potion.potion_effect(), Potions.action.INVINCIBLE);
	}
	
	void testisUsed() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		assertEquals(potion.isUsed(), true);
	}
	
	@Test
	void testgetType() {
		InvincibilityPotion potion = new InvincibilityPotion(1, 1);
		assertEquals(potion.getType(), "InvincibilityPotion");
	}

}
