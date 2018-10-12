package gameBackend;

import java.lang.Math;

import application.Sprite;
import javafx.scene.image.Image;

public class Coward extends Enemy implements Distance {
	
	public Coward(CoOrd currPos) {
		super(currPos, 'C');
		this.sprite = new Sprite(new Image("coward.png"),currPos);
	}
	
	/*
	 * Prompts the movement of the Coward 
	 * 
	 * @param the character's information and the border of the maze
	 * @post the coward moves near the character
	 */
	public void enemyMovement(Character target, int border) {
		
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		
		CoOrd me = this.getCurrPos();
		CoOrd ch = target.getCoordinates();
		
		if (me.getX() == ch.getX() && Math.abs(me.getY() - ch.getY()) == 1) {
			if (this.getDirection() != '^')
				this.setDirection('^');
			else
				this.getCurrPos().moveUp(sprite);
		} 
		
		else if (me.getY() == ch.getY() && Math.abs(me.getX() - ch.getX()) == 1) {
			if (this.getDirection() != '<')
				this.setDirection('<');
			else
				this.getCurrPos().moveLeft(sprite);
		} 
		
		else {
			moveCloser(me, ch, border);
		}
	}
	
	/*
	 * Coward acts similar to a Hunter but moves away from the character when it's close
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the hunter moves closer to character
	 */
	@Override
	public void moveCloser(CoOrd self, CoOrd target, int border) {
		int x_difference = self.getX() - target.getX();
		
		if (x_difference < 0) {
			if (this.getDirection() != 'v')
				this.setDirection('v');
			else
				this.getCurrPos().moveDown(border,sprite);
			return;
		} 
		else if (x_difference > 0) {
			if (this.getDirection() != '^')
				this.setDirection('^');
			else
				this.getCurrPos().moveUp(sprite);
			return;
		}
		
		int y_difference = self.getY() - target.getY();
		
		if (y_difference < 0) {
			if (this.getDirection() != '>')
				this.setDirection('>');
			else
				this.getCurrPos().moveRight(border,sprite);
			return;
		} 
		
		else if (y_difference > 0) {
			if (this.getDirection() != '<')
				this.setDirection('<');
			else
				this.getCurrPos().moveLeft(sprite);
			return;
		}
	}
	
	@Override
	public Enemy copy() {
		return new Coward(this.getCurrPos());
	}
	
	/*
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Coward";
	}
	
}