package niriksha_refactored;

public class HoverPotion extends Potion {
	
	public HoverPotion(int x, int y) {
		super(x, y, '~');
	}

	@Override
	public void potion_effect(STATE current_state) {
		current_state = HoverCharacter.GetInstance();
	}

}
