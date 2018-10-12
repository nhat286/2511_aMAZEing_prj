
package gameBackend;

import application.Sprite;
import javafx.scene.image.Image;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
		this.sprite = new Sprite(new Image("brick_brown_0.png"),co_ord);
	}
	
	
	/**
	 * Creates a copy of the weapon
	 * 
	 * @return copy of the weapon
	 */
	@Override
	public Obstacle copy() {
		return new Wall(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Wall";
	}

}