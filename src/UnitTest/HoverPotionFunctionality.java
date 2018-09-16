package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import niriksha.ACTION;
import niriksha.HoverPotion;
import niriksha.Potions;

class HoverPotionFunctionality {
	
	@Test
	void testgetCoordinates() {
		HoverPotion potion = new HoverPotion(1, 1);
		CoOrd co_ord = new CoOrd(1,1);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testsetCoordinates() {
		HoverPotion potion = new HoverPotion(1, 1);
		CoOrd co_ord = new CoOrd(2,3); 
		potion.setCoordinates(2, 3);
		assertEquals(potion.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		HoverPotion potion = new HoverPotion(1, 1);
		assertEquals(potion.getIcon(), '~');
	}
	
	@Test
	void testpotion_effect() {
		HoverPotion potion = new HoverPotion(1, 1);
		assertEquals(potion.potion_effect(), Potions.action.HOVER);
	}
	
	@Test
	void testisUsed() {
		HoverPotion potion = new HoverPotion(1, 1);
		potion.potion_effect();
		assertEquals(potion.isUsed(), true);
	}
	
	@Test
	void testgetType() {
		HoverPotion potion = new HoverPotion(1, 1);
		assertEquals(potion.getType(), "HoverPotion");
	}

}
