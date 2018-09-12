package niriksha;

import java.util.Timer;
import java.util.TimerTask;

import eric.Enemy;
import eric.Obstacle;

public class Bomb extends SpecialItems {
	
	
	private int action;
	// 0 == don't destroy
	// 1 == destroy
	
	private boolean lit;
	private Timer burn_timer;
	
	public Bomb(int x, int y) {
		super(x, y, 'Q');
		this.lit = false;
		this.burn_timer = new Timer();
	}
	
	public boolean isLit() {
		return this.lit;
	}

	public Timer getBurn_time() {
		return this.burn_timer;
	}

	@Override
	public int special_effect(Obstacle o) {
		if (lit == false && (((o.getClass()).toString()).equals("Boulder"))) {
			this.lit = true;
			this.burn_timer.schedule(new destroy_surroundings(), 1000*10);;
			action = 1;
			return action;
		}
		
		action = 0;
		return action;
	}

	@Override
	public int special_effect(Weapon w) {
		action = 0;
		return 0;
	}

	@Override
	public int special_effect(Enemy e) {
		if (lit == false) {
			this.lit = true;
			this.burn_timer.schedule(new destroy_surroundings(), 1000*10);;
		}
		
		action = 1;
		return action;
	}
	
	class destroy_surroundings extends TimerTask {
		public void run() {
            // destroy anything on the surrounding squares
            burn_timer.cancel();
        }
	}

	
}
