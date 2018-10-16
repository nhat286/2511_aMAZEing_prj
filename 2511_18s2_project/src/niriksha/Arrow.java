
package niriksha;

import application.Sprite;
import eric.CoOrd;
import jae.Enemy;
import javafx.scene.image.Image;

public class Arrow extends Weapon {
	
	private Character user;
	private char direction;
	private boolean used;
	
	public Arrow(int x, int y, Character user) {
		super(x, y, '%');
		this.user = user;
		this.used = false;
		this.setSprite(new Sprite(new Image("arrow.png"), this.getCoordinates()));
	}
	
	/**
	 * Sets coordinates of the arrow in the direction of the object   
	 * 
	 * @param object in front of character
	 * @return destroy enemy or do nothing
	 */
	@Override
	public ACTION weapon_action(Object object) {
		this.direction = this.user.getIcon();
		this.setCoordinates(this.user.getCoordinates().getX(), this.user.getCoordinates().getY());
		this.getCoordinates().setVelocity(50);
		switch (this.direction) {
		case '>':
			this.setSprite(new Sprite(new Image("arrow_flying_1e.png"), this.getCoordinates()));
			break;
		case '<':
			this.setSprite(new Sprite(new Image("arrow_flying_1w.png"), this.getCoordinates()));
			break;
		case '^':
			this.setSprite(new Sprite(new Image("arrow_flying_1n.png"), this.getCoordinates()));
			break;
		case 'v':
			this.setSprite(new Sprite(new Image("arrow_flying_1s.png"), this.getCoordinates()));
			break;
		default:
			this.setSprite(new Sprite(new Image("arrow.png"), this.getCoordinates()));
			break;
		}
		this.used = true;

		return ACTION.NOTHING;
	}
	
	public boolean isUsed() {
		return this.used;
	}
	
	/**
	 * Destroys the enemy if there is one and destroys itself
	 * 		else destroys itself if there is obstacle
	 * 		else keeps moving
	 * 
	 * @param object in front and border of the maze
	 * @return whether arrows will move or not
	 */
	public int moving(Object object, int border) {
		if (object != null) {
			if (object instanceof Enemy) {
				((Enemy) object).enemyDies();
				this.destroyWeapon();
				return 1;
			} else if (object instanceof Obstacle && !(object instanceof Pit)) {
				this.destroyWeapon();
				return 0;
			}
		}
		switch (direction) {
		case '>':
			if (this.getCoordinates().getY() == border - 1)
				this.destroyWeapon();
			else {//this.getCoordinates().moveRight(border);
				this.getCoordinates().moveRight(border, this.getSprite());
			}
			break;
		case '<':
			if (this.getCoordinates().getY() == 1)
				this.destroyWeapon();
			else {//this.getCoordinates().moveLeft();
				this.getCoordinates().moveLeft(this.getSprite());
			}
			break;
		case '^':
			if (this.getCoordinates().getX() == 1)
				this.destroyWeapon();
			else {//this.getCoordinates().moveUp();
				this.getCoordinates().moveUp(this.getSprite());
			}
			break;
		case 'v':
			if (this.getCoordinates().getX() == border - 1)
				this.destroyWeapon();
			else {//this.getCoordinates().moveDown(border);
				this.getCoordinates().moveDown(border, this.getSprite());
			}
			break;
		}
		return 0;
	}
	
	/**
	 * Determines coordinates of the object in front
	 * 
	 * @return coordinates of the object
	 */
	public CoOrd getInfront() {
		CoOrd co = new CoOrd(this.getCoordinates().getX(), this.getCoordinates().getY());
		if (this.direction == '^') co.setXY(co.getX() - 1, co.getY());
		else if (this.direction == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (this.direction == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (this.direction == '>') co.setXY(co.getX(), co.getY() + 1);
		return co;
	}
	
	/**
	 * Returns the type of weapon 
	 * 
	 * @return type of weapon
	 */
	@Override
	public String getType() {
		return "Arrow";
	}
	
	/**
	 * Creates a copy of this arrow
	 * 
	 * @return copy of this arrow
	 */
	@Override
	public Weapon copy() {
		return new Arrow(this.getCoordinates().getX(), this.getCoordinates().getY(), this.user);
	}
}
