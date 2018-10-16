package eric;

import application.Sprite;
import javafx.scene.image.Image;

public class Exit {
	private CoOrd co_ord;
	private char icon;
	private Sprite sprite;
	
	public Exit(int x, int y) {
		//this.co_ord = new CoOrd(x, y);
		this.co_ord = new CoOrd(x, y, 0);
		this.icon = 'F';
		this.sprite = new Sprite(new Image("exit.png"), this.getCoordinates());
	}
	
	public Sprite getSprite() {
		return this.sprite;
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
