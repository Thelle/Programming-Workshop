package dk.itu.KF13.TheSim.Game;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class Main {
	/**
	 * @param args
	 */
	
	public GameController game;
	
	public static void main(String[] args) {
		new Main().start();		
	}
	
	/**
	 * start creates the instances of the model, view and controller; 
	 * creates the game and starts the game
	 */
	private void start() {
		//Instances of model, view and control are instantiated
		GameController controller = new GameController();
		GameRunner runner = new GameRunner();
		
		//Set attributes if runner
		runner.setGameController(controller);
		
		//Creates the game
		runner.createGame();
		
		//Set attributes of controller
		controller.setGameRunner(runner);
		controller.setPlayer(runner.getPlayer());
		
		//Game is run
		runner.runGame();
		
	}
	
	
		
}
