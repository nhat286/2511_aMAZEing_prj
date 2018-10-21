package jae;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;
import niriksha.Character;

public class Hunter extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7934917464551359312L;

	public Hunter(CoOrd currPos) {
		super(currPos, 'H');
		this.setSprite(new Sprite(new Image("hunter.png"), currPos));
	}
	
	/**
	 * for testing purposes only
	 * @param currPo
	 * @param i
	 */
	public Hunter(CoOrd currPo, int i) {
		super(currPo, 'H');
	}
	
	/*
	 * Hunter moves closer based on the movement of the character
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the hunter moves closer to character
	 */
	/**
	 * Prompts the movement of the Hunter towards the character
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hunter moves closer to character
	 */
	@Override
	public boolean specialMovement(Character target, int border) {
		return true;
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Hunter";
	}
	
	/**
	 * Make a new copy of the Hunter
	 * @return a new enemy object that has the same information
	 */
	@Override
	public Enemy copy() {
		return new Hunter(this.getCurrPos());
	}
	
	@Override
	public void updateImage() {
		this.getSprite().setImage(new Image("hunter.png"));
	}
	
	@Override
	public void setCurrPos(CoOrd currPos) {
		super.setCurrPos(currPos);
		this.setSprite(new Sprite(new Image("hunter.png"), currPos));
	}
}

