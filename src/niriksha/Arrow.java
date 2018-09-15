
package niriksha;

import eric.CoOrd;
import jae.Enemy;

public class Arrow extends Weapon {
	
	private Character user;
	private char direction;
	private boolean used;
	
	public Arrow(int x, int y, Character user) {
		super(x, y, '%');
		this.user = user;
		this.used = false;
	}

	@Override
	public action weapon_action(Object object) {
		this.direction = this.user.getIcon();
		this.setCoordinates(this.user.getCoordinates().getX(), this.user.getCoordinates().getY());
		used = true;
//		if (object != null) {
//			if (object instanceof Enemy) {
//				//destroy_arrow(this);
//				((Enemy) object).enemyDies();
//				this.setCoordinates(-1, -1);
//				return action.DESTROY;
//			} else if (object instanceof Obstacle) {
//				this.setCoordinates(-1, -1);
//				return action.DESTROY;
//			}
//		}
		
		return action.NOTHING;
	}
	
	// arrow has to be destroyed every time its fired
	public void destroy_arrow(Weapon w) {
		w = null;
	}
	
	public boolean isUsed() {
		return this.used;
	}
	
	public int moving(Object object, int border) {
		int kill = 0;
		if (object != null) {
			if (object instanceof Enemy) {
				//destroy_arrow(this);
				((Enemy) object).enemyDies();
				kill = 1;
				this.setCoordinates(-1, -1);
				//return action.DESTROY;
			} else if (object instanceof Obstacle) {
				this.setCoordinates(-1, -1);
				//return action.DESTROY;
			}
		}
		switch (direction) {
		case '>':
			if (this.getCoordinates().getY() == border - 1)
				this.setCoordinates(-1, -1);
			else this.getCoordinates().moveRight(border);
			break;
		case '<':
			if (this.getCoordinates().getY() == 1)
				this.setCoordinates(-1, -1);
			else this.getCoordinates().moveLeft();
			break;
		case '^':
			if (this.getCoordinates().getX() == 1)
				this.setCoordinates(-1, -1);
			else this.getCoordinates().moveUp();
			break;
		case 'v':
			if (this.getCoordinates().getX() == border - 1)
				this.setCoordinates(-1, -1);
			else this.getCoordinates().moveDown(border);
			break;
		}
		return kill;
	}
	
	public CoOrd getInfront() {
		CoOrd co = new CoOrd(this.getCoordinates().getX(), this.getCoordinates().getY());
		if (this.direction == '^') co.setXY(co.getX() - 1, co.getY());
		else if (this.direction == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (this.direction == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (this.direction == '>') co.setXY(co.getX(), co.getY() + 1);
		return co;
	}
	
	@Override
	public String getType() {
		return "Arrow";
	}
	
}
