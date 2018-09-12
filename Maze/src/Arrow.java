
package niriksha;

public class Arrow extends Weapon {
	
	public Arrow(int x, int y, char c) {
		super(x, y, '^');
	}

	@Override
	public action weapon_action(char object) {
		
		if (object == 'A') {
			destroy_arrow(this);
			return action.DESTROY;
		}
		
		return action.NOTHING;
	}
	
	// arrow has to be destroyed every time its fired
	public void destroy_arrow(Weapon w) {
		w = null;
	}
	
	
}
