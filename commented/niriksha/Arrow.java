package niriksha;

import jae.Enemy;

public class Arrow extends Weapon {
	
	public Arrow(int x, int y) {
		super(x, y, '%');
	}

	/*
	 * Enemy is destroyed with the arrow if it has the same row or column as the character  
	 * 
	 * @param: object in front of character
	 * @post: destroy enemy or do nothing 
	 */
	@Override
	public action weapon_action(Object object) {
		
		if (object instanceof Enemy) {
			((Enemy) object).enemyDies();
			this.setCoordinates(-1, -1);
			return action.DESTROY;
		}
		
		return action.NOTHING;
	}
	
	/*
	 * Destroy this instance of class after being used
	 * 
	 * @post: this arrow is set to null
	 */
	public void destroy_arrow(Weapon w) {
		w = null;
	}
	
	/*
	 * Returns the type of weapon 
	 * 
	 * @post type of weapon
	 */
	@Override
	public String getType() {
		return "Arrow";
	}
	
}