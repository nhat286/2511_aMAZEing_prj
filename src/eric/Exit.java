package eric;

public class Exit {
	private CoOrd co_ord;
	private char icon;
	
	public Exit(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = 'F';
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Exit";
	}
	
	/**
	 * Creates a copy of the exit
	 * 
	 * @return copy of the exit
	 */
	public Exit copy() {
		return new Exit(this.co_ord.getX(), this.co_ord.getY());
	}
}
