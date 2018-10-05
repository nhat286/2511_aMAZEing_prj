package niriksha_refactored;

import java.util.Timer;

import eric.CoOrd;

public class Bomb extends Weapon {

	private boolean lit;
	private CoOrd user;
	private Timer burn_timer;
	private boolean explode;
	
	
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.user = user;
		this.explode = false;
		this.burn_timer = new Timer();
	}

	@Override
	public ACTION weapon_action(Object object) {
		// implement timer
		
		return null;
	}
	
	// getter and setter methods
	
	public boolean isLit() {
		return this.lit;
	}
	
	public boolean isExploded() {
		return this.explode;
	}

}
