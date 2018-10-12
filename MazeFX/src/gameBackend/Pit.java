package gameBackend;

import application.Sprite;
import javafx.scene.image.Image;

public class Pit extends Obstacle {
	
	public Pit(int x, int y) {
		super(x, y, 'B');
		this.sprite = new Sprite(new Image("shaft.png"),co_ord);
	}
	
	/**
	 * Creates a copy of this pit
	 * 
	 * @return copy of this pit
	 */
	@Override
	public Obstacle copy() {
		return new Pit(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	public String getType() {
		return "Pit";
	}

}
