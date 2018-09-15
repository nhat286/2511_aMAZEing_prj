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
	
	public void pickUp() {
		this.picked_up = true;
		incrementPoints();
	}
	
	private static void incrementPoints() {
		points++;
	}
	
	public void removeTreasure() {//Treasure t) {
		//t = null;
		this.co_ord.setXY(-1, -1);
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
	
	public static int getPoints() {
		return points;
	}
	public String getType() {
		return "Treasure";
	}
}