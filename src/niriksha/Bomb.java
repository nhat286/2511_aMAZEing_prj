package niriksha;

import java.util.Timer;
import java.util.TimerTask;

import eric.CoOrd;

public class Bomb extends Weapon {
	
	private boolean lit;
	private boolean explode;
	private Timer burn_timer;
	private CoOrd user;
	
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.explode = false;
		this.burn_timer = new Timer();
		this.user = user;
	}
	
	
	
	// only destroy if bomb hasn't been used before
	@Override
	public action weapon_action(Object object) {
		if (this.lit == false) {
			this.lit = true;
			this.setCoordinates(this.user.getX(), this.user.getY());
			this.burn_timer.schedule(new destroy_surroundings(), 1000*5);
			//this.setCoordinates(-1, -1);
			this.explode = true;
			return action.BOMB_DESTROY;
		}
		return action.NOTHING;
	}
	
	// used to run and then stop the timer
	class destroy_surroundings extends TimerTask {
		public void run() {
            burn_timer.cancel();
        }
	}
	
	public boolean isLit() {
		return this.lit;
	}
	
	public boolean isExploded() {
		return this.explode;
	}

	public Timer getBurn_time() {
		return this.burn_timer;
	}

	
	
	
	
	
	/*@Override
	public action special_effect() {
		if (this.lit == false && (((o.getClass()).toString()).equals("Boulder"))) {
			this.lit = true;
			this.burn_timer.schedule(new destroy_surroundings(), 1000*10);;
			return action.DESTROY;
		}
		
		return action.N_DESTROY;
		
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
	}*/
	
	@Override
	public String getType() {
		return "Bomb";
	}
	
}

