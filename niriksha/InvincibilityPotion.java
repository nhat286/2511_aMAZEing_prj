package niriksha;

import java.util.Timer;

import prj_2511.Enemy;
import prj_2511.Obstacle;

public class InvincibilityPotion extends SpecialItems {
	
	private int action;
	// 0 == don't die
	// 1 == enemy dies
	// 2 == nothing happens
	
	Timer time_limit; // change to time type?

	public InvincibilityPotion(int x, int y) {
		super(x, y, '!');
		this.time_limit = new Timer();
	}

	public Timer getTime_limit() {
		return this.time_limit;
	}
	
	/*
	 * 
	 * @see SpecialItems#special_effect(Obstacle)
	 */
	@Override
	public int special_effect(Obstacle o) {
		action = 2;
		return action;
	}
	
	public int special_effect(Enemy e) {
		e.destroyEnemy();
		action = 1;
		return action;
	}
	
	public int special_effect(Weapon w) {
		if (((w.getClass()).toString()).equals("Bomb")) {
			action = 0;
			return action;
		}
		action = 2;
		return action;
	}

}