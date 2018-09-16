package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eric.CoOrd;
import jae.Enemy;
import jae.Hunter;
import niriksha.ACTION;
import niriksha.Bomb;
import niriksha.Weapon;

class BombFunctionality {
	
	@Test
	void testgetCoordinates() {
		Bomb bomb = new Bomb(3, 5,null);
		assertEquals(bomb.getCoordinates(), new CoOrd(3,5));
	}
	
	@Test
	void testsetCoordinates() {
		Bomb bomb = new Bomb(3, 5,null);
		CoOrd co_ord = new CoOrd(5,3); 
		bomb.setCoordinates(5, 3);
		assertEquals(bomb.getCoordinates(), co_ord);
	}
	
	@Test
	void testgetIcon() {
		Bomb bomb = new Bomb(3, 5,null);
		assertEquals(bomb.getIcon(), 'Q');
	}
	
	@Test
	void testisPicked_up() {
		Bomb bomb = new Bomb(3, 5,null);
		assertEquals(bomb.isPicked_up(), false);
	}
	
	@Test
	void testisLit() {
		Bomb bomb = new Bomb(3, 5,null);
		assertEquals(bomb.isLit(), false);
	}
	
	@Test
	void testweapon_action() {
		Bomb bomb = new Bomb(3, 5,null);
		CoOrd co_ord = new CoOrd(6,3);
		Enemy e = new Hunter(co_ord);
		assertEquals(bomb.weapon_action(e), Weapon.action.DESTROY);
	}
	
	@Test
	void testgetType() {
		Bomb bomb = new Bomb(3, 5,null);
		assertEquals(bomb.getType(), "Bomb");
	}
}
