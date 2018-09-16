package niriksha;

import eric.CoOrd;

public abstract class Obstacle {
	
	protected CoOrd co_ord;
	private char icon;
	
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
	
	/**
	 * Destroys obstacles by setting their coordinates outside of the maze
	 */
	public void destroyObstacle() {
		this.setCoordinates(-1, -1);
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	/**
	 * Creates a copy of the obstacle
	 * 
	 * @return copy of the obstacle
	 */
	public abstract Obstacle copy();
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Obstacle";
	}
}