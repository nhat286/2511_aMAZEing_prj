package niriksha;

import java.io.Serializable;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;

public class Key implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2972232956767401283L;
	private CoOrd co_ord;
	private char icon;
	private Door door;
	private Sprite sprite;
	
	public Key(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '&';
		this.door = null;
		this.sprite = new Sprite(new Image("Key.png"), this.getCoordinates());
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Key(int x, int y, int i) {
		this.co_ord = new CoOrd(x, y);
		this.icon = '&';
		this.door = null;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}

	/**
	 * Determines if the door is assigned to be opened by this key
	 * 
	 * @param Door
	 * @return true if door is opened and is assigned to this key, false otherwise
	 */ 
	public boolean checkDoor(Door d) {
		if (this.door != null && this.door.equals(d)) {
			d.openDoor();
			this.setCoordinates(-1, -1);
			return true;
		}
		return false;
	}
	
	/**
	 * for testing purposes only
	 * @param d
	 * @param i
	 * @return
	 */
	public boolean checkDoor(Door d, int i) {
		if (this.door != null && this.door.equals(d)) {
			d.openDoor(i);
			this.setCoordinates(-1, -1);
			return true;
		}
		return false;
	}
	
	public void linkDoor(Door d) {
		this.door = d;
	}
	
	public Door getDoorLinked() {
		return this.door;
	}
	
	public CoOrd getCoordinates() {
		return co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
		this.sprite = new Sprite(new Image("Key.png"), this.getCoordinates());
	}
	/**
	 * Pickup which remove its appearance from the map
	 */
	public void pickUp() {
		this.setCoordinates(-2, -2);
	}
	
	/**
	 * Returns the type of object 
	 * 
	 * @return type of object
	 */
	public String getType() {
		return "Key";
	}
	
	/**
	 * Creates a copy of this key
	 * 
	 * @return copy of this key
	 */
	public Key copy() {
		Key k = new Key(this.getCoordinates().getX(), this.getCoordinates().getY());
		k.linkDoor(this.getDoorLinked());
		return k;
	}
	
	public void updateImage() {
		this.getSprite().setImage(new Image("Key.png"));
	}

}
