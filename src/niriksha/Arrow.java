package niriksha;

import eric.Enemy;

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
