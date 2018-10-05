package niriksha_refactored;

import eric.CoOrd;
import jae.Enemy;
import niriksha.Obstacle;
import niriksha.Pit;
import niriksha.Weapon.action;

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
	public ACTION weapon_action(Object object) {
		this.direction = this.user.getIcon();
		this.setCoordinates(this.user.getCoordinates().getX(), this.user.getCoordinates().getY());
		used = true;

		return ACTION.NOTHING;
	}

	public int moving(Object object, int border) {

		if (object != null) {
			if (object instanceof Enemy) {
				((Enemy) object).enemyDies();
				this.setCoordinates(-1, -1);
				return 1;
			}
			else if (object instanceof Obstacle && !(object instanceof Pit)) {
				this.setCoordinates(-1, -1);
				return 0;
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

		return 0;
	}
	
	public CoOrd getInfront() {

		CoOrd co = new CoOrd(this.getCoordinates().getX(), this.getCoordinates().getY());

		if (this.direction == '^') co.setXY(co.getX() - 1, co.getY());
		else if (this.direction == 'v') co.setXY(co.getX() + 1, co.getY());
		else if (this.direction == '<') co.setXY(co.getX(), co.getY() - 1);
		else if (this.direction == '>') co.setXY(co.getX(), co.getY() + 1);

		return co;
	}
	
	// getter and setter methods
	
	public boolean isUsed() {
		return this.used;
	}

}
