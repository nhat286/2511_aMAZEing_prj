
package niriksha;

import eric.CoOrd;

public class Boulder extends Obstacle {
	
	boolean on_switch;
	FloorSwitch f_s;
	
	public enum action {
		MOVE, DESTROYED, NOTHING;
	}
	
	public Boulder(int x, int y) {
		super(x, y, 'O');
		this.on_switch = false;
	}
	
	/**
	 *  Boulder is pushed by the character
	 *  
	 *  @param direction of movement, the object in front of boulder, 
	 *  	   the icon of the object & the border of the maze
	 *  @return the floorswitch trigger is deactivated if boulder moved off it, activated if 
	 *  		boulder moves on top of the floorswitch, boulder is destroyed if it falls in 
	 *  		the pit, else it moves to the next position 
	 */
	public action push_boulder(char direction, char type, Object object, int border) {
		
		switch (type) {
		
			case '#':
			case 'O':
			case 'E':
			case 'F':
				this.toggleSwitch(null);
				return action.NOTHING;
			
			// floor switch
			case 'I':
				moveCoOrd(direction, border);
				this.toggleSwitch((FloorSwitch) object);
				return action.MOVE;
			
			// pit
			case 'B':
				this.toggleSwitch(null);
				this.setCoordinates(-1, -1);
				return action.DESTROYED;
			
			default:
				this.toggleSwitch(null);
				moveCoOrd(direction, border);
				return action.MOVE;
				
		}
	}
	
	/**
	 * Activates and deactivates trigger for floorswitch 
	 * 
	 * @param floorswitch
	 */
	private void toggleSwitch(FloorSwitch fs) {
		if (this.on_switch == true) {
			this.f_s.deactivateTrigger();
			this.f_s = null;
			this.on_switch = false;
		} else  if (fs != null){
			this.f_s = fs;
			this.f_s.activateTrigger();
			this.on_switch = true;
		}
	}
	
	/**
	 * Prompts movement of the boulder 
	 * 
	 * @param direction of movement & the maze border
	 * @return boulder is moved
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
	
	/**
	 * Activates the trigger of floorswitch 
	 * 
	 * @param floorswitch
	 */
	public void setSwitch(FloorSwitch fs) {
		this.f_s = fs;
		if (fs != null)
			fs.activateTrigger();
	}
	
	public FloorSwitch getTriggeredSwitch() {
		return this.f_s;
	}
	
	/**
	 * Determines coordinates of the object in front
	 * @return coordinates of the object
	 */
	public CoOrd getInfront(char direction) {
		CoOrd co = new CoOrd(this.getCoordinates().getX(), this.getCoordinates().getY());
		if (direction == '^') co.setXY(co.getX() - 1, co.getY());
		else if (direction == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (direction == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (direction == '>') co.setXY(co.getX(), co.getY() + 1);
		return co;
	}
	
	/**
	 * Creates a copy of this boulder
	 * 
	 * @return copy of this boulder
	 */
	@Override
	public Obstacle copy() {
		return new Boulder(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	public String getType() {
		return "Boulder";
	}

}