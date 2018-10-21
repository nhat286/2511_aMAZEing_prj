package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class Wall extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2623939476923194533L;

	public Wall(int x, int y) {
		super(x, y, '#');
		this.setSprite(new Sprite(new Image("brick_brown_0.png"), this.getCoordinates()));
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Wall(int x, int y, int i) {
		super(x, y, '#');
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
	
	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("brick_brown_0.png"));
	}

}
