package dk.itu.KF13.TheSim.Game;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Controller.IGameController;
import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * The main class of the program. It is from here the game is started.
 * @author Simon
 *
 */
public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		new Main().start();		
	}
	
	/**
	 * start creates the instances of the model, view and controller; 
	 * creates the game and starts the game
	 */
	private void start() {
		//Instances of model, view and control are instantiated
		IGameController controller = new GameController();
		GameRunner runner = new GameRunner();
		GameView view = new GameView();
		
		//Set attributes of runner
		runner.setGameController(controller);
		runner.setGameView(view);
		
		//Creates the game
		runner.createGame();
		
		//Set attributes of controller
		controller.setGameRunner(runner);
		controller.setPlayer(runner.getPlayer());
		controller.setGameView(view);
		
		//Game is run
		runner.runGame();
		
	}
	
	
		
}
