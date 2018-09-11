package niriksha;

import prj_2511.Enemy;

public class Arrow extends Weapon {
	
	public Arrow(int x, int y, char c) {
		super(x, y, '^');
	}
	
	@Override
	public int action(Enemy e) {
		e.destroyEnemy();	
		return 0;
			
	}
	
}
