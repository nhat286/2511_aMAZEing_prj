package kyle_maze;

public class Rules implements Menu{
	
	private String[] rules;
	
	Rules(){
		rules = new String [4];
		rules[0] = "To proceed to next level push the boulder to ...";
		rules[1] = "To proceed to next level kill all enemies ...";
		rules[2] = "To proceed to next level find all treasures  ...";
		rules[3] = "To proceed to next level open all doors ...";
	}
	
	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		
	}

}
