package kyle_maze;
import java.util.HashMap;

import prj_2511.Enemy;
import prj_2511.Maze;

public class EnemyStat implements Menu{
	
	private String[] EnemyDescript;
	private HashMap<Enemy, Integer> hashMap;
	
	EnemyStat(Maze maze){
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
		
	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Enemy, Integer> getEnemyHash(Maze maze) {
		return maze.enemyStat();
	}
	

}
