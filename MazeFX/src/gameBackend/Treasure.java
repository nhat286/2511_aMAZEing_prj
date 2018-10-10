package gameBackend;

public class Treasure {
	
	private CoOrd co_ord;
	private char icon;
	private boolean picked_up;
	
	public Treasure(int x, int y) {
		this.co_ord = new CoOrd(x, y,0);
		this.icon = '$';
		this.picked_up = false;
	}
	
	/**
	 * Pick up treasure 
	 * 
	 * @return treasure is picked up and prompts increase in points
	 */
	public void pickUp() {
		this.picked_up = true;
		this.removeTreasure();
	}
	
	/**
	 * Treasure is removed once picked up
	 * 
	 * @return sets the coordinates outside of the maze
	 */
	public void removeTreasure() {
		this.setCoordinates(-1, -1);
	}
	
	/**
	 * Creates a copy of this treasure
	 * 
	 * @return copy of this treasure
	 */
	public Treasure copy() {
		return new Treasure(this.getCoord().getX(), this.getCoord().getY());
	}
	
	public CoOrd getCoord() {
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
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Treasure";
	}
}