package niriksha;

import java.util.Timer;
import java.util.TimerTask;

import eric.CoOrd;
import jae.Enemy;

public class Bomb extends Weapon {
	
	private boolean lit;
	private int turn_left;
	private CoOrd user;
	
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.turn_left = 3;
		this.user = user;
	}
	
	/**
	 * Activate a bomb and destroy any objects in surrounding area
	 * 
	 * @param Object in surrounding area
	 * @return if bomb hasn't been used, destroy surroundings, otherwise do nothing
	 */
	@Override
	public action weapon_action(Object object) {
		
		if (this.lit == false) {
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
		
		return action.NOTHING;
	}
	
	public boolean isLit() {
		return this.lit;
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