
package niriksha;

import jae.Enemy;

public class Arrow extends Weapon {
	
	public Arrow(int x, int y) {
		super(x, y, '%');
	}

	@Override
	public action weapon_action(Object object) {
		
		if (object instanceof Enemy) {
			//destroy_arrow(this);
			((Enemy) object).enemyDies();
			this.setCoordinates(-1, -1);
			return action.DESTROY;
		}
		
		return action.NOTHING;
	}
	
	// arrow has to be destroyed every time its fired
	public void destroy_arrow(Weapon w) {
		w = null;
	}
	
	@Override
	public String getType() {
		return "Arrow";
	}
	
}
