package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

import java.util.Scanner;

public class Main {
	/**
	 * @param args
	 */
	
	public GameController game;
	
	public static void main(String[] args) {
		
		new Main().start();
		
	}
	
	private void start() {
		game = new GameController();
		//Running the game
		game.runGame();
	}
	
	
		
}
