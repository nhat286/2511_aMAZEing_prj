package prj_2511;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		Character user = new Character(1, 1);
		Maze curr = new Maze(40);
		assert (curr != null);
		curr.addCharacter(user);
		curr.printMap();
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
