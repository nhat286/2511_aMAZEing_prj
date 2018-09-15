package niriksha;

public class Boulder extends Obstacle {
	
	boolean on_switch;
	FloorSwitch f_s;
	
	enum action {
		MOVE, DESTROYED, NOTHING;
	}
	
	public Boulder(int x, int y) {
		super(x, y, 'O');
		this.on_switch = false;
	}
	
	public action push_boulder(char direction, char type, Object object, int border) {
		switch (type) {
			case '#':
			case 'O':
			case 'E':
			case 'F':
			case 'G':
				if (this.on_switch == true) {
					this.f_s.deactivateTrigger();
					this.f_s = null;
					this.on_switch = false;
				}
				return action.NOTHING;
			
			// floor switch
			case 'I':
				moveCoOrd(direction, border);
				this.f_s = ((FloorSwitch) object);
				((FloorSwitch) object).activateTrigger();
				this.on_switch = true;
				return action.MOVE;
			
			// pit
			case 'B':
				if (this.on_switch == true) {
					this.f_s.deactivateTrigger();
					this.f_s = null;
					this.on_switch = false;
				}
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
