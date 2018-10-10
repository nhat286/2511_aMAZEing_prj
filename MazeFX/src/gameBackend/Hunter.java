package gameBackend;

public class Hunter extends Enemy implements Distance {
	
	public Hunter(CoOrd currPos) {
		super(currPos, 'H');
	}
	
	/*
	 * Prompts the movement of the Hunter towards the character
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hunter moves closer to character
	 */
	@Override
	public void enemyMovement(Character target, int border) {
		
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		
		moveCloser(this.getCurrPos(), target.getCoordinates(), border);
	}
	
	/*
	 * Hunter moves closer based on the movement of the character
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
				this.getCurrPos().moveDown(border);
			return;
		} 
		else if (x_difference > 0) {
			if (this.getDirection() != '^')
				this.setDirection('^');
			else
				this.getCurrPos().moveUp();
			return;
		}
		
		int y_difference = self.getY() - target.getY();
		
		if (y_difference < 0) {
			if (this.getDirection() != '>')
				this.setDirection('>');
			else
				this.getCurrPos().moveRight(border);
			return;
		} 
		else if (y_difference > 0) {
			if (this.getDirection() != '<')
				this.setDirection('<');
			else
				this.getCurrPos().moveLeft();
			return;
		}
	}
	
	@Override
	public Enemy copy() {
		return new Hunter(this.getCurrPos());
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
	
}