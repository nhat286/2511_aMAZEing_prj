package niriksha;

public class Boulder extends Obstacle {
	
	enum action {
		MOVE, DESTROYED, NOTHING;
	}
	
	public Boulder(int x, int y) {
		super(x, y, 'D');
	}
	
	public action push_boulder(char direction, char type, Object object, int border) {
		switch (type) {
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
				return action.NOTHING;
			
			// floor switch
			case 'I':
				moveCoOrd(direction, border);
				((FloorSwitch) object).activateTrigger();
				return action.MOVE;
			
			// pit
			case 'B':
				moveCoOrd(direction, border);
				destroyBoulder(this);
				return action.DESTROYED;
				
		}
		return null;
	}
	
	public void moveCoOrd(char movement, int border) {
		if (movement == '<') {
			this.co_ord.moveLeft();
		} 
		else if (movement == '>') {
			this.co_ord.moveRight(border);
		} 
		else if (movement == '^') {
			this.co_ord.moveUp();
		} 
		else if (movement == 'v') {
			this.co_ord.moveDown(border);
		}
	}
	
	public void destroyBoulder(Boulder b) {
		b = null;
	}
	

}
