package niriksha;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Weapon {
	
	private boolean lit;
	private Timer burn_timer;
	
	public Bomb(int x, int y) {
		super(x, y, 'Q');
		this.lit = false;
		this.burn_timer = new Timer();
	}
	
	/*
	 * Activate a bomb and destroy any objects in surrounding area
	 * 
	 * @param: Object in surrounding area
	 * @post: if bomb hasn't been used, destroy surroundings, otherwise do nothing
	 */
	@Override
	public action weapon_action(Object object) {
		if (this.lit == false) {
			this.lit = true;
			this.burn_timer.schedule(new destroy_surroundings(), 1000*5);
			this.setCoordinates(-1, -1);
			return action.DESTROY;
		}
		return action.NOTHING;
	}
	
	/*
	 * Runs the timer
	 */
	class destroy_surroundings extends TimerTask {
		public void run() {
            burn_timer.cancel();
        }
	}
	
	public boolean isLit() {
		return this.lit;
	}
	
	/*
	 * Returns the type of weapon 
	 * 
	 * @post type of weapon
	 */
	@Override
	public String getType() {
		return "Bomb";
	}
	
}
