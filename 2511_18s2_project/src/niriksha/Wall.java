package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
		this.setSprite(new Sprite(new Image("brick_brown_0.png"), this.getCoordinates()));
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
	
	/**
	 * Creates a copy of the wall
	 * 
	 * @return copy of the wall
	 */
	@Override
	public Obstacle copy() {
		return new Wall(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
