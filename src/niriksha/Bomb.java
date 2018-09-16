package niriksha;
/*
import java.util.Timer;
import java.util.TimerTask;

import eric.CoOrd;
import jae.Enemy;

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
			this.burn_timer.schedule(new burnout(), 1000*5);
			synchronized(object) {
				burn_timer.cancel();
				this.explode = true;
				if (object != null) {
					if (object instanceof Boulder) {
						((Boulder) object).destroyObstacle();
					} else if (object instanceof Enemy) {
						((Enemy) object).enemyDies();
					}
				}
				burn_timer.purge();
			}
			//this.explode = true;
			return action.BOMB_DESTROY;
		}
		return action.NOTHING;
	}
	
	// used to run and then stop the timer
	class burnout extends TimerTask 
	{
	    public void run() 
	    { 
	        System.out.println("BOOM!");
//	        synchronized(Character.equip_weapon) 
//	            { 
//	        		Character.equip_weapon.notify(); 
//	            }
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
	
	@Override
	public String getType() {
		return "Bomb";
	}
	
	@Override
	public Weapon copy() {
		return new Bomb(this.getCoordinates().getX(), this.getCoordinates().getY(), this.user);
	}
}
*/
