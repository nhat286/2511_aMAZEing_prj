package niriksha;

import java.util.ArrayList;

public interface STATES {
	public ACTION move(char direction, char type, Object object, int border);
	public void moveCoOrd(char movement, int border);
	public ArrayList<Potions> getActivePotion();
	
}
