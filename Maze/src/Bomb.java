package niriksha;

import java.util.Timer;
import java.util.TimerTask;

import eric.CoOrd;
import jae.Enemy;

public class Bomb extends Weapon {
	
	private boolean lit;
	private int turn_left;
	private CoOrd user;
	private Timer burn_timer;
	private boolean explode;
	
	
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.turn_left = 3;
		this.user = user;
		this.explode = false;
		this.burn_timer = new Timer();
	}
	
	/**
	 * Activate a bomb and destroy any objects in surrounding area
	 * 
	 * @param Object in surrounding area
	 * @return if bomb hasn't been used, destroy surroundings, otherwise do nothing
	 */
	@Override
	public action weapon_action(Object object) {
		
		/*if (this.lit == false) {
			this.lit = true;
			this.setCoordinates(this.user.getX(), this.user.getY());
			return action.NOTHING;
		} 
		
		else if (this.turn_left > 0) {
			this.countDown();
			return action.NOTHING;
		} 
		
		else if (this.turn_left == 0) {
			if (object != null) {
				if (object instanceof Boulder) {
					((Boulder) object).destroyObstacle();
					return action.DESTROY;
				} 
				else if (object instanceof Enemy) {
					((Enemy) object).enemyDies();
					return action.DESTROY;
				}
			}
		}
		
		return action.NOTHING;*/
		if (this.lit == false) {
			this.lit = true;
			this.setCoordinates(this.user.getX(), this.user.getY());
			this.burn_timer.schedule(new burnout(), 1000*5);
			/*synchronized(object) {
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
			}*/
			//this.explode = true;
			burn_timer.cancel();
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
//		        synchronized(Character.equip_weapon) 
//		            { 
//		        		Character.equip_weapon.notify(); 
//		            }
		    }     
		}
	
	public boolean isLit() {
		return this.lit;
	}
	
	public boolean isExploded() {
		return this.explode;
	}
	
	/**
	 * Reduces the number of turns left by 1
	 */
	public void countDown() {
		this.turn_left--;
	}

	public int turnsRemaining() {
		return this.turn_left;
	}
	
	/**
	 * Creates a copy of this bomb
	 * 
	 * @return copy of this bomb
	 */
	@Override
	public Weapon copy() {
		return new Bomb(this.getCoordinates().getX(), this.getCoordinates().getY(), this.user);
	}
	
	/**
	 * Returns the type of weapon 
	 * 
	 * @return type of weapon
	 */
	@Override
	public String getType() {
		return "Bomb";
	}
	
}
