
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
	
	/*
	 *  Boulder is pushed by the character
	 *  
	 *  @param: direction of movement, the object in front of boulder, 
	 *  		the icon of the object & the border of the maze
	 *  @post: the floorswitch trigger is deactivated if boulder moved off it, activated if 
	 *  	   boulder moves on top of the floorswitch and boulder destroyed if it falls in the pit 
	 */
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
	
	/*
	 * Prompts movement of the boulder 
	 * 
	 * @param: direction of movement & the maze border
	 * @post: boulder is moved
	 */
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
	
	/*
	 * Destroy this instance of class after being used
	 * 
	 * @post: boulder is set to null
	 */
	public void destroyBoulder(Boulder b) {
		b = null;
	}
	
	/*
	 * Returns the type of obstacle 
	 * 
	 * @post type of obstacle
	 */
	public String getType() {
		return "Boulder";
	}

}