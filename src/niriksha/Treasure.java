package niriksha;

import eric.CoOrd;

public class Treasure {
	
	//private static int points = 0;
	private CoOrd co_ord;
	private char icon;
	private boolean picked_up;
	
	public Treasure(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '$';
		this.picked_up = false;
	}
	
	public void pickUp() {
		this.picked_up = true;
		this.removeTreasure();
		//incrementPoints();
	}
	
	/*private static void incrementPoints() {
		points++;
	}*/
	
	public void removeTreasure() {//Treasure t) {
		//t = null;
		this.setCoordinates(-1, -1);
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
	
	/*public static int getPoints() {
		return points;
	}*/
	
	public String getType() {
		return "Treasure";
	}
	
	public Treasure copy() {
		return new Treasure(this.getCoord().getX(), this.getCoord().getY());
	}
}