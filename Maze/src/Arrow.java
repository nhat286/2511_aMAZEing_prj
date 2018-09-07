
public class Arrow extends Weapon {
	
	public Arrow(int x, int y) {
		super(x, y);
	}
	
	@Override
	public int action() {
		
		// if (character facing right) {
			coordinates.moveRight();
		//}
			
		// else if (character facing left) {
			coordinates.moveLeft();
		//}
			
		// else if (character facing up) {
			coordinates.moveUp();
		//}
			
		// else if (character facing down) {
			coordinates.moveDown();
		//}
		
		// else {
			// do nothing
		//}
			
		return 0;
			
	}
	
}
