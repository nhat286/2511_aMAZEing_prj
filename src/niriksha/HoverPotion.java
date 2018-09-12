package niriksha;

import eric.Enemy;
import eric.Obstacle;

public class HoverPotion extends SpecialItems {
	
	private int action;
	// 0 == don't die
	// 1 == die
	// 2 == nothing happens

	public HoverPotion(int x, int y) {
		super(x, y, '~');
	}
	
	/*
	 * @pre: assuming the coordinates of obstacle is given
	 * @see SpecialItems#special_effect()
	 */
	@Override
	public int special_effect(Obstacle o) {
		
		if ((o.getType()).equals("Pit")) {
			action = 1;
		}
		else if (o.getType().equals("Wall")) {
			action = 2;
		}
		else if (o.getType().equals("Boulder")) {
			action = 2;
		}
		return action;
	}

	@Override
	public int special_effect(Weapon w) {
		// do nothing
		return 0;
	}

	@Override
	public int special_effect(Enemy e) {
		// do nothing
		return 0;
	}

}