package jae;

import application.Sprite;
import eric.CoOrd;
import javafx.scene.image.Image;
import niriksha.Character;

public class Hound extends Enemy {
	CoOrd hunterCoOrd;
	
	public Hound(CoOrd currPos) {
		super(currPos, 'D');
		this.hunterCoOrd = null;
		this.setSprite(new Sprite(new Image("hound.png"), currPos));
	}
	
	public void linkHunter(CoOrd hunterCoOrd) {
		this.hunterCoOrd = hunterCoOrd;
	}
	
	public CoOrd getHunterCoOrd() {
		return this.hunterCoOrd;
	}

	/**
	 * Moves the Hound such that the character is between it and the Hound
	 * or try to locate character's location and move towards that if no hunter present
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hound moves to the target location
	 */
	@Override
	public boolean specialMovement(Character target, int border) {
		CoOrd ch = target.getCoordinates();
		CoOrd me = this.getCurrPos();
		/*
		 * If no hunter is linked, use Hunter's logic to follow player
		 */
		if (this.hunterCoOrd == null || this.hunterCoOrd.getX() < 0) {
			return true;
		}
		/*
		 * Try to position itself so character is between it and the Hunter
		 */
		if (this.hunterCoOrd.getY() < ch.getY()) {
			if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			} else if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			}
		} else if (this.hunterCoOrd.getY() > ch.getY()) {
			if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			} else if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			}
		} else if (this.hunterCoOrd.getX() < ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		} else if (this.hunterCoOrd.getX() > ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		}
		return false;
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Hound";
	}
	
	/**
	 * Make a new copy of the Hound
	 * @return a new enenmy object which has the same information
	 */
	@Override
	public Enemy copy() {
		return new Hound(this.getCurrPos());
	}
}

