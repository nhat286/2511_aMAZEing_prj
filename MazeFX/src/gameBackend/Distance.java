package gameBackend;

public interface Distance {
	public void moveCloser(CoOrd self, CoOrd target, int border);
	
	public default void goLeft(Enemy self) {
		if (self.getDirection() != '<')
			self.setDirection('<');
		else
			self.getCurrPos().moveLeft();
	}
	
	public default void goUp(Enemy self) {
		if (self.getDirection() != '^')
			self.setDirection('^');
		else
			self.getCurrPos().moveUp();
	}
	
	public default void goRight(Enemy self, int border) {
		if (self.getDirection() != '>')
			self.setDirection('>');
		else
			self.getCurrPos().moveRight(border);
	}
	
	public default void goDown(Enemy self, int border) {
		if (self.getDirection() != 'v')
			self.setDirection('v');
		else
			self.getCurrPos().moveDown(border);
	}
	
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
				if (me.getY() < border - 1) {
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
				} else if (me.getX() < border - 1) {
					goDown(self, border);
				} else {
					goRight(self, border);
				}
			} else {
				if (me.getY() < border - 1) {
					goRight(self, border);
				} else if (me.getX() < border - 1) {
					goDown(self, border);
				} else {
					goLeft(self);
				}
			}
		}
	}
}