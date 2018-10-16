package kyle_maze;

import java.util.HashMap;
import java.util.Map.Entry;

import eric.Maze;

public class EnemyStat implements Menu{
	
	private String[] EnemyDescript;
	private HashMap<String, Integer> enemyHash;
	
	/**
	 * Receive a hashmap of enemies from maze and initiate enemy descriptions
	 * 
	 * @param maze
	 */
	public EnemyStat(Maze maze){
		EnemyDescript = new String [4];
		EnemyDescript[0] = "Hunter: "
				+ "The hunter constantly moves toward you,"
				+ " stopping if it cannot move any closer. ";
		EnemyDescript[1] = "Hound: "
				+ "The hound will assist the hunter to catch you "
				+ "by besieging you with the hunter on the opposite side of you.";
		EnemyDescript[2] = "Coward: "
				+ "The coward behaves like the hunter when far away from you,"
				+ " but runs away when you are close to it.";
		EnemyDescript[3] = "Strategist: "
				+ "The Strategist could read your mind "
				+ "and move towards where you want to go.";
		this.enemyHash = getEnemyHash(maze);
	}
	
	/* (non-Javadoc)
	 * Overided default method to print enemy statistics from hashmap
	 * 
	 * @see kyle_maze.Menu#displayMenu()
	 */
	/**
	 * Prints the Enemy statistics
	 */
	@Override
	public void display() {
		System.out.println("Any collision with enemies would cause death. There are these types of enemy in current maze: ");
		for (Entry<String, Integer> e : enemyHash.entrySet()) {
			switch(e.getKey().toString()) {
			case "Hunter":
				System.out.println("------------------------------------------------------------");
				System.out.println(EnemyDescript[0]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Hound":
				System.out.println("------------------------------------------------------------");
				System.out.println(EnemyDescript[1]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Coward":
				System.out.println("------------------------------------------------------------");
				System.out.println(EnemyDescript[2]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			case "Strategist":
				System.out.println("------------------------------------------------------------");
				System.out.println(EnemyDescript[3]);
				System.out.println("------------------------------------------------------------");
				System.out.println(e.getKey().toString()+": "+e.getValue().toString());
				break;
			default:
				break;
			}
		}
	}

	
	public HashMap<String, Integer> getEnemyHash(Maze maze) {
		return maze.enemyStat();
	}
	

}