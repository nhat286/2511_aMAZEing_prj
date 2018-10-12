package gameBackend;

import application.Sprite;
import javafx.scene.image.Image;

public class Strategist extends Enemy implements Distance {
	
	public Strategist(CoOrd currPos) {
		super(currPos, 'S');
		this.sprite = new Sprite(new Image("strategist.png"),currPos);
	}
	
	/*
	 * Prompts movement of the Strategist
	 * 
	 * @param the character's information and the border of the maze
	 * @post the strategist moves based on its strategy
	 */
	@Override
	public void enemyMovement(Character target, int border) {
		
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		
		CoOrd ch = new CoOrd(target.getCoordinates().getX(), target.getCoordinates().getY(),50);
		
		if (target.getIcon() == '<') {
			ch.moveLeft(sprite);
		} else if (target.getIcon() == '>') {
			ch.moveRight(border,sprite);
		} else if (target.getIcon() == '^') {
			ch.moveUp(sprite);
		} else if (target.getIcon() == 'v') {
			ch.moveDown(border,sprite);
		}
		
		moveCloser(this.getCurrPos(), ch, border);
	}
	
	/*
	 * Strategist moves closer based on the predicted movement of the character
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the strategist moves based on its strategy
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
		return new Strategist(this.getCurrPos());
	}
	
	/*
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Strategist";
	}

}
