import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends SpecialItems {
	
	boolean lit;
	Timer burn_timer;
	
	public Bomb(int x, int y) {
		super(x, y);
		this.lit = false;
		this.burn_timer = new Timer();
	}
	
	public boolean isLit() {
		return this.lit;
	}

	public Timer getBurn_time() {
		return this.burn_timer;
	}

	public void special_effect(){
		if (lit == false) {
			this.lit = true;
			this.burn_timer.schedule(new destroy_surroundings(), 1000*10);;
		}
	}
	
	class destroy_surroundings extends TimerTask {
		public void run() {
            // destroy anything on the surrounding squares
            burn_timer.cancel();
        }
	}
}
