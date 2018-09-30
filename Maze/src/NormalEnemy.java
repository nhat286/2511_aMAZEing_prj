package niriksha;

import java.util.ArrayList;

public class NormalEnemy implements STATES {
	
	// this shouldn't be implemented by enemy 
	@Override
	public ACTION move(char direction, char type, Object object, int border) {
		return null;
	}

	@Override
	public void moveCoOrd(char movement, int border) {
	}

	@Override
	public ArrayList<Potions> getActivePotion() {
		return null;
	}

}

