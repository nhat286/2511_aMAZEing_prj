package jae;

import eric.CoOrd;

public interface Distance {
	public default void moveCloser(Enemy self, CoOrd target, int border) {
		CoOrd me = self.getCurrPos();
		int x_difference = me.getX() - target.getX();
		if (x_difference < 0) {
			goDown(self, border);
			return;
		} else if (x_difference > 0) {
			goUp(self);
			return;
		}
		
		int y_difference = me.getY() - target.getY();
		if (y_difference < 0) {
			goRight(self, border);
			return;
		} else if (y_difference > 0) {
			goLeft(self);
			return;
		}
	}
	
	/**
	 * Basic movement of enemies (similar to character)
	 * Changes direction first and then moves left
	 * @param self the enemy size
	 */
	public default void goLeft(Enemy self) {
		if (self.getDirection() != '<')
			self.setDirection('<');
		else
			self.getCurrPos().moveLeft();
	}
	
	/**
	 * Basic movement of enemies (similar to character)
	 * Changes direction first and then moves up
	 * @param self the enemy size
	 */
	public default void goUp(Enemy self) {
		if (self.getDirection() != '^')
			self.setDirection('^');
		else
			self.getCurrPos().moveUp();
	}
	
	/**
	 * Basic movement of enemies (similar to character)
	 * Changes direction first and then moves right
	 * @param self the enemy object
	 * @param border the map size
	 */
	public default void goRight(Enemy self, int border) {
		if (self.getDirection() != '>')
			self.setDirection('>');
		else
			self.getCurrPos().moveRight(border);
	}
	
	/**
	 * Basic movement of enemies (similar to character)
	 * Changes direction first and then moves down
	 * @param self the enemy object
	 * @param border the map size
	 */
	public default void goDown(Enemy self, int border) {
		if (self.getDirection() != 'v')
			self.setDirection('v');
		else
			self.getCurrPos().moveDown(border);
	}
	
	/**
	 * Determines to move away from character, is called when character uses InvincibilityPotion
	 * @param self the enemy object which moves away from character
	 * @param target the player
	 * @param border the map size
	 */
	public default void moveAway(Enemy self, CoOrd target, int border) {
		CoOrd me = self.getCurrPos();
		if (me.getX() < target.getX()) {
			if (me.getY() < target.getY()) {
				if (me.getY() > 1) {
					goLeft(self);
				} else if (me.getX() > 1) {
					goUp(self);
				} else {
					goRight(self, border);
				}
			} else {
				if (me.getY() < border - 2) {
					goRight(self, border);
				} else if (me.getX() > 1) {
					goUp(self);
				} else {
					goLeft(self);
				}
			}
		} else {
			if (me.getY() < target.getY()) {
				if (me.getY() > 1) {
					goLeft(self);
				} else if (me.getX() < border - 2) {
					goDown(self, border);
				} else {
					goRight(self, border);
				}
			} else {
				if (me.getY() < border - 1) {
					goRight(self, border);
				} else if (me.getX() < border - 2) {
					goDown(self, border);
				} else {
					goLeft(self);
				}
			}
		}
	}
}
