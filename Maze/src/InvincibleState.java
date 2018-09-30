package niriksha;

public class InvincibleState implements State {

	@Override
	public ACTION potion_effect() {
		return ACTION.INVINCIBLE;
	}

}