package niriksha;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;

public class Treasure {
	
	//private static int points = 0;
	private CoOrd co_ord;
	private char icon;
	private boolean picked_up;
	private Sprite sprite;
	
	public Treasure(int x, int y) {
		//this.co_ord = new CoOrd(x, y);
		this.co_ord = new CoOrd(x, y, 0);
		this.icon = '$';
		this.picked_up = false;
		this.sprite = new Sprite(new Image("gold_pile.png"), this.getCoordinates());
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	/**
	 * Pick up treasure and remove its appearance on the map 
	 * 
	 */
	public void pickUp() {
		this.picked_up = true;
		this.removeTreasure();
		//incrementPoints();
	}
	
	/*private static void incrementPoints() {
		points++;
	}*/
	
	/**
	 * Treasure is removed once picked up
	 * 
	 * @return sets the coordinates outside of the maze
	 */
	public void removeTreasure() {
		this.setCoordinates(-1, -1);
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public boolean isPickedUp() {
		return picked_up;
	}
	
	/*public static int getPoints() {
		return points;
	}*/
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Treasure";
	}
	
	/**
	 * Creates a copy of this treasure
	 * 
	 * @return copy of this treasure
	 */
	public Treasure copy() {
		return new Treasure(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}