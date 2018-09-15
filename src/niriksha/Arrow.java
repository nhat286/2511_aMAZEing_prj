
package niriksha;

import jae.*;

public class Arrow extends Weapon {
	
	public Arrow(int x, int y) {
		super(x, y, '%');
	}

	@Override
	public ACTION weapon_action(Object object) {
		
		if (object instanceof Enemy) {
			//destroy_arrow(this);
			return ACTION.DESTROY;
		}
		
		return ACTION.NOTHING;
	}
	
	// arrow has to be destroyed every time its fired
	public void destroy_arrow(Weapon w) {
		//Seems this won't work
		w = null;
	}
	
	@Override
	public String getType() {
		return "Arrow";
	}
	
}
