package kyle_maze;

import java.io.IOException;

public class Rules implements Menu{
	
	private String[] rules;
	
	public Rules(){
		rules = new String [4];
		rules[0] = "To proceed to next level push the boulder to ...";
		rules[1] = "To proceed to next level kill all enemies ...";
		rules[2] = "To proceed to next level find all treasures  ...";
		rules[3] = "To proceed to next level open all doors ...";
	}
	
	@Override
	public void displayMenu() {
		try {
			pause1Sec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}