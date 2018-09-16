package kyle_maze;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import eric.Maze;

public class EnemyStat implements Menu{
	
	private String[] EnemyDescript;
	private HashMap<String, Integer> hashMap;
	
	public EnemyStat(Maze maze){
		EnemyDescript = new String [4];
		EnemyDescript[0] = "Hunter: will try to follow your step";
		EnemyDescript[1] = "Hound: will move so it can position you between it and the Hunter";
		EnemyDescript[2] = "Coward: will chase you down, but stay away if it gets too close to you";
		EnemyDescript[3] = "Strategist: will try to predict your next move and go there";
		hashMap = getEnemyHash(maze);
	}
	
	/**
	 * Prints the Enemy statistics
	 */
	@Override
	public void displayMenu() {
		for (Entry<String, Integer> e : hashMap.entrySet()) {
			System.out.println(e.getKey().toString()+": "+e.getValue().toString());
		}
		try {
			pause1Sec();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	
	public HashMap<String, Integer> getEnemyHash(Maze maze) {
		return maze.enemyStat();
	}
	

}
