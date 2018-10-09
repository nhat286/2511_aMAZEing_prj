package kyle_maze;

import java.io.IOException;

import gameBackend.Maze;

public class Rules implements Menu{
	
	private String[] rules;
	private int goal;
	
	/**
	 * initiate the with all possible rules storage and get a integer goal for later use
	 * 
	 * @param maze
	 */
	public Rules(Maze maze){
		rules = new String [4];
		rules[0] = "Find the exit door.";
		rules[1] = "Collect all treasures.";
		rules[2] = "Kill all enemies.";
		rules[3] = "Find keys to open locked doors.";
		rules[4] = "Solve the puzzles.";
		goal = maze.getWinCond();
	}
	
	/* (non-Javadoc)
	 * Overiding the default method
	 * Take the bit and with winning goal mask to display current goal in the maze
	 * 
	 * @see kyle_maze.Menu#displayMenu()
	 */
	@Override
	public void display() {
		System.out.println("To win this game, you need to:");
		if ((goal & 0b00001) !=0)
			System.out.println(rules[0]);
		if ((goal & 0b00010) !=0)
			System.out.println(rules[1]);
		if ((goal & 0b00100) !=0)
			System.out.println(rules[2]);
		if ((goal & 0b01000) !=0)
			System.out.println(rules[3]);
		if ((goal & 0b10000) !=0)
			System.out.println(rules[4]);
		
	}

}
