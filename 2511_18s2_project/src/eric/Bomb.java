package eric;

import java.util.Timer;
import java.util.TimerTask;

import application.Sprite;
import jae.Enemy;
import javafx.scene.image.Image;
import niriksha.Boulder;
import niriksha.Weapon;
import niriksha.ACTION;

public class Bomb extends Weapon {
	
	private boolean lit;
	private boolean explode;
	private CoOrd user;
	private Timer invincibility_timer;
	
	/**
	 * Constructor to instantiate a bomb object
	 * @param x valid x co-ordinate of the bomb on the map to be picked up
	 * @param y valid y co-ordinate of the bomb on the map to be picked up
	 * @param user the player, once picked up, set bomb co-odrinates to be same of user's
	 * 				to display where the bomb would explode 
	 */
	public Bomb(int x, int y, CoOrd user) {
		super(x, y, 'Q');
		this.lit = false;
		this.explode = false;
		this.user = user;
		this.setSprite(new Sprite(new Image("bomb_unlit.png"), this.getCoordinates()));
	}
	
	/**
	 * Lit of the bomb when called, and keep counting down until it explodes
	 * 		which destroys objects near it (passed in by Maze)
	 * @param object the object next to bomb to be destroyed
	 * @return action either destroy the object or do nothing
	 */
	@Override
	public ACTION weapon_action(Object object) {
		if (this.lit == false) {
			this.lit = true;
			this.setCoordinates(this.user.getX(), this.user.getY());
			this.invincibility_timer = new Timer();
			this.setSprite(new Sprite(new Image("bomb_lit_1.png"), this.getCoordinates()));
			invincibility_timer.schedule(new tick2(), 1000);
		}
		if (this.explode) {
			this.invincibility_timer.purge();
			if (object != null) {
			if (object instanceof Boulder) {
				((Boulder) object).destroyObstacle();
				return ACTION.DESTROY;
			} else if (object instanceof Enemy) {
				((Enemy) object).enemyDies();
				return ACTION.DESTROY;
			}
		}
		}
		return ACTION.NOTHING;
	}
	
	class explode extends TimerTask {
		public void run() {
			explode = true;
			invincibility_timer.cancel();
        }
	}
	
	class tick2 extends TimerTask {
		public void run() {
			setSprite(new Sprite(new Image("bomb_lit_2.png"), getCoordinates()));
			invincibility_timer.schedule(new tick3(), 1000);
		}
	}
	
	class tick3 extends TimerTask {
		public void run() {
			setSprite(new Sprite(new Image("bomb_lit_3.png"), getCoordinates()));
			invincibility_timer.schedule(new tick4(), 1000);
		}
	}
	
	class tick4 extends TimerTask {
		public void run() {
			setSprite(new Sprite(new Image("bomb_lit_4.png"), getCoordinates()));
			invincibility_timer.schedule(new explode(), 100);
		}
	}
	
	/**
	 * Check if the bomb is currently lit/used
	 * @return true if bomb has been lit
	 */
	public boolean isLit() {
		return this.lit;
	}
	
	public boolean isExploded() {
		return this.explode;
	}
	
	/**
	 * Get the type of bomb object
	 */
	@Override
	public String getType() {
		return "Bomb";
	}
	
	/**
	 * Make a new copy of itself
	 */
	@Override
	public Weapon copy() {
		return new Bomb(this.getCoordinates().getX(), this.getCoordinates().getY(), this.user);
	}
}