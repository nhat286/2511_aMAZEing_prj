package niriksha;

import application.Sprite;
import javafx.scene.image.Image;

public class Pit extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1602300405835683465L;

	public Pit(int x, int y) {
		super(x, y, 'B');
		this.setSprite(new Sprite(new Image("shaft.png"), this.getCoordinates()));
	}
	
	/**
	 * for testing purposes only
	 * @param x
	 * @param y
	 * @param i
	 */
	public Pit(int x, int y, int i) {
		super(x, y, 'B');
	}
	
	/**
	 * Returns the type of obstacle 
	 * 
	 * @return type of obstacle
	 */
	@Override
	public String getType() {
		return "Pit";
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
	
	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("shaft.png"));
	}
}
