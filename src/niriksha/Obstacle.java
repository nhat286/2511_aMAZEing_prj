package niriksha;

import application.Sprite;
import eric.CoOrd;

public abstract class Obstacle {
	
	private CoOrd co_ord;
	private char icon;
	private Sprite sprite;
	
	public Obstacle(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}
	
	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	/**
	 * Destroys obstacles by setting their coordinates outside of the maze
	 */
	public void destroyObstacle() {
		this.setCoordinates(-1, -1);
	}
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Obstacle";
	}
	
	/**
	 * Creates a copy of the obstacle
	 * 
	 * @return copy of the obstacle
	 */
	public abstract Obstacle copy();
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
}
