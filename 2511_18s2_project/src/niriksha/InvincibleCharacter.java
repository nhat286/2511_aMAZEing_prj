package niriksha;

import java.io.Serializable;

import jae.Enemy;

public class InvincibleCharacter implements STATE, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6968055375371459964L;
	private Character current;

	public InvincibleCharacter(Character c) {
		this.current = c;
	}

	@Override
	public ACTION move(char direction, char type, Object object, int border) {
		// if the character can move
		if (direction == this.current.getIcon()) {
			
			switch(type) {
				
				// hunter, strategist, hound, coward
				case 'H':
				case 'S':
				case 'D':
				case 'C':
					((Enemy) object).enemyDies();
					return ACTION.DESTROY;
					
					
				// pit
				case 'B':
					this.current.destroyCharacter(this.current);
					return ACTION.DIE;
					
				// wall
				case '#':
					return ACTION.NOTHING;
					
				// boulder
				case 'O':
					return ACTION.PUSH_BOULDER;
				
				// door
				case 'E':
					if (((Door) object).isDoorOpen()) {
						this.current.moveCoOrd(direction, border);
						return ACTION.MOVE;
					}
					else {
						if (this.current.hasKey() && this.current.getHoldingKey().checkDoor((Door) object)) {
							return ACTION.MOVE;
						}
						else {
							return ACTION.NOTHING;
						}
					}
					
				// F is exit
				case 'F':
					this.current.moveCoOrd(direction, border);
					return ACTION.GAME_COMPLETE;
					
				// $ is treasure
				case '$':
					((Treasure) object).pickUp();
					this.current.moveCoOrd(direction, border);
					return ACTION.MOVE;
					
				default:
					this.current.moveCoOrd(direction, border);
					return ACTION.MOVE;
			}
		} 
		
		// only changes direction
		else {
			this.current.moveCoOrd(direction, border);
			return ACTION.NOTHING;
		}
	}

}
