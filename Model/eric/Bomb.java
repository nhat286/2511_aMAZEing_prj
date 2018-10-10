package eric;

import jae.Enemy;
import niriksha.Boulder;
import niriksha.Weapon;
import niriksha.ACTION;

public class Bomb extends Weapon {
	
	private boolean lit;
	private int turn_left;
	private CoOrd user;
	
	/**
	 * Constructor to instantiate a bomb object
	 * @param x valid x co-ordinate of the bomb on the map to be picked up
	 * @param y valid y co-ordinate of the bomb on the map to be picked up
	 * @param user the player, once picked up, set bomb co-odrinates to be same of user's
	 * 				to display where the bomb would explode 
	 */
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.turn_left = 3;
		this.user = user;
	}
	
	/**
	 * Lit of the bomb when called, and keep counting down until it explodes
	 * 		which destroys objects near it (passed in by Maze)
	 * @param object the object next to bomb to be destroyed
	 * @return action either destroy the object or do nothing
	 */
	@Override
	public ACTION weapon_action(Object object) {
		if (this.lit == false) {
			this.lit = true;
			this.setCoordinates(this.user.getX(), this.user.getY());
			return ACTION.NOTHING;
		} else if (this.turn_left > 0) {
			this.countDown();
			return ACTION.NOTHING;
		} else if (this.turn_left == 0) {
			if (object != null) {
				if (object instanceof Boulder) {
					((Boulder) object).destroyObstacle();
					return ACTION.DESTROY;
				} else if (object instanceof Enemy) {
					((Enemy) object).enemyDies();
					return ACTION.DESTROY;
				}
			}
		}
		return ACTION.NOTHING;
	}
	
	/**
	 * Check if the bomb is currently lit/used
	 * @return true if bomb has been lit
	 */
	public boolean isLit() {
		return this.lit;
	}
	
	/**
	 * Decrement the turn left of bomb
	 */
	public void countDown() {
		this.turn_left--;
	}
	
	/**
	 * Getter method to check turns remaining until bomb explodes
	 * @return turns left until bomb explodes
	 */
	public int turnsRemaining() {
		return this.turn_left;
	}
	
	/**
	 * Get the type of bomb object
	 */
	@Override
	public String getType() {
		return "Bomb";
	}
	
	/**
	 * Make a new copy of itself
	 */
	@Override
	public Weapon copy() {
		return new Bomb(this.getCoordinates().getX(), this.getCoordinates().getY(), this.user);
	}
}