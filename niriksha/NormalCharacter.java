package niriksha_refactored;

public class NormalCharacter extends Character implements STATE, Move {
	
	private static NormalCharacter instance = new NormalCharacter(co_ord.getX(), co_ord.getY());

	public NormalCharacter(int x, int y) {
		super(x, y);
	}
	
	public static STATE GetInstance() {
		return instance;
	}

	@Override
	public ACTION move(char direction, char type, Object object, int border) {
		// if the character can move
				if (direction == this.getIcon()) {
					
					switch(type) {
						
						// hunter, strategist, hound, coward
						case 'H':
						case 'S':
						case 'D':
						case 'C':
							destroy_character(this);
							return ACTION.DIE;
							
							
						// pit
						case 'B':
							return ACTION.DIE;
							
						// wall
						case '#':
							return ACTION.NOTHING;
							
						// boulder
						case 'O':
							return ACTION.PUSH_BOULDER;
						
						// door
						case 'E':
							if (((Door) object).isDoor_open()) {
								moveCoOrd(direction, border);
								return ACTION.MOVE;
							}
							else {
								if (holding_key != null && holding_key.checkDoor((Door) object)) {
									return ACTION.MOVE;
								}
								else {
									return ACTION.NOTHING;
								}
							}
							
						// F is exit
						case 'F':
							moveCoOrd(direction, border);
							return ACTION.GAME_COMPLETE;
							
						// G is treasure
						case 'G':
							((Treasure) object).pickUp();
							moveCoOrd(direction, border);
							return ACTION.MOVE;
							
						default:
							moveCoOrd(direction, border);
							return ACTION.MOVE;
					}
				} 
				
				// only changes direction
				else {
					moveCoOrd(direction, border);
					return ACTION.NOTHING;
				}
	}

}
