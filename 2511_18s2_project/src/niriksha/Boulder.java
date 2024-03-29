package niriksha;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;

public class Boulder extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1799498590372763810L;
	private boolean on_switch;
	private FloorSwitch f_s;
	
	public Boulder(int x, int y) {
		super(x, y, 'O');
		this.on_switch = false;
		this.setSprite(new Sprite(new Image("boulder.png"), this.getCoordinates()));
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Boulder(int x, int y, int i) {
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
	public ACTION push_boulder(char direction, char type, Object object, int border) {
		switch (type) {
			case '#':
			case 'O':
			case 'E':
			case 'F':
				this.toggleSwitch(null);
				return ACTION.NOTHING;
			
			// floor switch
			case 'I':
				moveCoOrd(direction, border);
				this.toggleSwitch((FloorSwitch) object);
				return ACTION.MOVE;
			
			// pit
			case 'B':
				this.toggleSwitch(null);
				this.destroyObstacle();
				return ACTION.DESTROYED;
			default:
				this.toggleSwitch(null);
				moveCoOrd(direction, border);
				return ACTION.MOVE;
		}
	}
	
	/**
	 * for testing purposes only
	 * @param direction
	 * @param type
	 * @param object
	 * @param border
	 * @param i
	 * @return
	 */
	public ACTION push_boulder(char direction, char type, Object object, int border, int i) {
		switch (type) {
			case '#':
			case 'O':
			case 'E':
			case 'F':
				this.toggleSwitch(null);
				return ACTION.NOTHING;
			
			// floor switch
			case 'I':
				moveCoOrd(direction, border, i);
				this.toggleSwitch((FloorSwitch) object);
				return ACTION.MOVE;
			
			// pit
			case 'B':
				this.toggleSwitch(null);
				this.destroyObstacle();
				return ACTION.DESTROYED;
			default:
				this.toggleSwitch(null);
				moveCoOrd(direction, border, i);
				return ACTION.MOVE;
		}
	}
	
	/**
	 * Check the state of boulder if it moves into a switch or out of a switch
	 * @param fs the floorswitch to be triggered if moves onto it
	 */
	private void toggleSwitch(FloorSwitch fs) {
		if (this.on_switch == true) {
			this.f_s.deactivateTrigger();
			this.f_s = null;
			this.on_switch = false;
		} else if (fs != null){
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
			this.getCoordinates().moveLeft();
		} 
		else if (movement == '>') {
			this.getCoordinates().moveRight(border);
		} 
		else if (movement == '^') {
			this.getCoordinates().moveUp();
		} 
		else if (movement == 'v') {
			this.getCoordinates().moveDown(border);
		}
		this.getSprite().setPosition(this.getCoordinates().getX() * 32, this.getCoordinates().getY() * 32);
	}
	
	public void moveCoOrd(char movement, int border, int i) {
		if (movement == '<') {
			this.getCoordinates().moveLeft();
		} 
		else if (movement == '>') {
			this.getCoordinates().moveRight(border);
		} 
		else if (movement == '^') {
			this.getCoordinates().moveUp();
		} 
		else if (movement == 'v') {
			this.getCoordinates().moveDown(border);
		}
	
	}
	
	/**
	 * Setter method in case of designing a boulder already triggers a floorswitch
	 * @param fs
	 */
	public void setSwitch(FloorSwitch fs) {
		this.f_s = fs;
		if (fs != null)
			fs.activateTrigger();
	}
	
	/**
	 * Getter method
	 * @return the floorswitch object the boulder triggers, or null if none
	 */
	public FloorSwitch getTriggeredSwitch() {
		return this.f_s;
	}
	
	/**
	 * Determines coordinates of the object in front
	 * @return coordinates of the object
	 */
	public CoOrd getInfront(char direction) {
		CoOrd co = new CoOrd(this.getCoordinates().getX(), this.getCoordinates().getY());
//		if (direction == '^') co.setXY(co.getX() - 1, co.getY());
//		else if (direction == 'v') co.setXY(co.getX() + 1, co.getY());
//		else if (direction == '<') co.setXY(co.getX(), co.getY() - 1);
//		else if (direction == '>') co.setXY(co.getX(), co.getY() + 1);
		
		if (direction == '<') co.setXY(co.getX() - 1, co.getY());
		else if (direction == '>') co.setXY(co.getX() + 1, co.getY());
		else if (direction == '^') co.setXY(co.getX(), co.getY() - 1);
		else if (direction == 'v') co.setXY(co.getX(), co.getY() + 1);
		return co;
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Boulder";
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

	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("boulder.png"));
	}
}
