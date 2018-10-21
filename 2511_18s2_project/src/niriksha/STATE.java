package niriksha;

public interface STATE {
	
	public ACTION move(char direction, char type, Object object, int border);
	
}