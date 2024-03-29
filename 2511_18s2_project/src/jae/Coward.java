package jae;

import eric.CoOrd;
import javafx.scene.image.Image;
import niriksha.Character;

import java.lang.Math;

import application.Sprite;

public class Coward extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4573904191602388796L;

	public Coward(CoOrd currPos) {
		super(currPos, 'C');
		this.setSprite(new Sprite(new Image("coward.png"), currPos));
	}
	
	/**
	 * for testing purposes only
	 * @param currPos
	 * @param i
	 */
	public Coward(CoOrd currPos, int i) {
		super(currPos, 'C');
	}
	
	/*
	 * Coward acts similar to a Hunter but moves away from the character when it's close
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the hunter moves closer to character
	 */
	/**
	 * Prompts the movement of the Coward 
	 * 
	 * @param the character's information and the border of the maze
	 * @post the coward moves near the character
	 */
	public boolean specialMovement(Character target, int border) {
		CoOrd me = this.getCurrPos();
		CoOrd ch = target.getCoordinates();
		if ((me.getX() == ch.getX() && Math.abs(me.getY() - ch.getY()) == 1) ||
				(me.getY() == ch.getY() && Math.abs(me.getX() - ch.getX()) == 1)) {
			moveAway(this, ch, border);
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Coward";
	}
	
	/**
	 * Make a copy of the Coward
	 * @return new Coward object with same information
	 */
	@Override
	public Enemy copy() {
		return new Coward(this.getCurrPos());
	}

	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("coward.png"));
	}
}

