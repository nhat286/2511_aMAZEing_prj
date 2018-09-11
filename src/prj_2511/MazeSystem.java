package prj_2511;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import niriksha.Character;
import kyle_maze.*;

public class MazeSystem implements KeyListener, ActionListener {

	/**
	 * Run in terminal
	 * Go to directory bin
	 */
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze curr = new Maze(40);
		assert (curr != null);
		
		Character user = new Character(1, 1);
		curr.addCharacter(user);
		curr.printMap();
		
		Enemy e1 = new Enemy(3, 4, 'A',"Hunter");
		curr.addEnemy(e1);
		Enemy e2 = new Enemy(7, 21, 'A',"Hunter");
		curr.addEnemy(e2);
		Enemy e3 = new Enemy(11, 17, 'B',"Hound");
		curr.addEnemy(e3);
		curr.printMap();
		Menu eMenu = new EnemyStat(curr);
		eMenu.display();
		
		//clearScreen();
		//curr.printMap();
		/*
		 * Logic for system would be:
		 * while game not end {
		 * 		printMap();
		 * 		get info and coords from user
		 * 		get/update behaviours and stats
		 * 		updateMap();
		 * }
		 */
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Pressed: " + arg0.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Released: " + arg0.getKeyChar());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
