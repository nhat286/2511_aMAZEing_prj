package jae;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;
import niriksha.Character;

public class Strategist extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 883543305792571493L;

	public Strategist(CoOrd currPos) {
		super(currPos, 'S');
		this.setSprite(new Sprite(new Image("strategist.png"), currPos));
	}
	
	/*
	 * Strategist moves closer based on the predicted movement of the character
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the strategist moves based on its strategy
	 */
	/**
	 * Prompts movement of the Strategist
	 * 
	 * @param the character's information and the border of the maze
	 * @post the strategist moves into location that character was facing (which would be likely where character moves to next)
	 */
	@Override
	public boolean specialMovement(Character target, int border) {
		CoOrd ch = new CoOrd(target.getCoordinates().getX(), target.getCoordinates().getY());
		if (target.getIcon() == '^') {
			ch.moveLeft();
		} else if (target.getIcon() == 'v') {
			ch.moveRight(border);
		} else if (target.getIcon() == '<') {
			ch.moveUp();
		} else if (target.getIcon() == '>') {
			ch.moveDown(border);
		}
		moveCloser(this, ch, border);
		return false;
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Strategist";
	}
	
	/**
	 * Make a new copy of the Strategist
	 * @return a new enemy object that has the same information
	 */
	@Override
	public Enemy copy() {
		return new Strategist(this.getCurrPos());
	}
	
	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("strategist.png"));
	}
}
