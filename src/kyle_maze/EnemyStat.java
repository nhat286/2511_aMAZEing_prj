package kyle_maze;
import java.util.HashMap;
//import java.util.Map;
import java.util.Map.Entry;

import eric.Maze;
//import jae.Enemy;

public class EnemyStat implements Menu{
	
	private String[] EnemyDescript;
	private HashMap<String, Integer> hashMap;
	
	public EnemyStat(Maze maze){
		EnemyDescript = new String [4];
		EnemyDescript[0] = "Hunter: ...";
		EnemyDescript[1] = "Hound: ...";
		EnemyDescript[2] = "Coward: ...";
		EnemyDescript[3] = "???: ...";
		hashMap = getEnemyHash(maze);
	}
	
	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		for (Entry<String, Integer> e : hashMap.entrySet()) {
			System.out.println(e.getKey().toString()+": "+e.getValue().toString());
		}
	}

	
	public HashMap<String, Integer> getEnemyHash(Maze maze) {
		return maze.enemyStat();
	}
	

}
