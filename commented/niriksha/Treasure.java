package niriksha;

import eric.CoOrd;

public class Treasure {
	
	private static int points;
	private CoOrd co_ord;
	private char icon;
	private boolean picked_up;
	
	public Treasure(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
		this.picked_up = false;
	}
	
	/*
	 * Pick up treasure 
	 * 
	 * @post: treasure is picked up and prompts increase in points
	 */
	public void pickUp() {
		this.picked_up = true;
		incrementPoints();
	}
	
	/*
	 * Increase points 
	 */
	private static void incrementPoints() {
		points++;
	}
	
	/*
	 * Treasure is removed once picked up
	 * 
	 * @post: the coordinates of the treasure is set as negatives
	 */
	public void removeTreasure() {
		this.co_ord.setXY(-1, -1);
	}
	
	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public boolean isPickedUp() {
		return picked_up;
	}
	
	public int getPoints() {
		return points;
	}
	
	/*
	 * Returns the type of object 
	 * 
	 * @post type of object
	 */
	public String getType() {
		return "Treasure";
	}
}