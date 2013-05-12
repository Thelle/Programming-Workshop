package dk.itu.KF13.TheSim.Game.Controller;

import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * The BlackJackController class is used to take input from the user
 * which are used during the Black Jack game.
 * @author Simon
 *
 */
public class BlackJackController {
	
	private IGameController controller;
	private GameView view;
	
	public BlackJackController (IGameController gameController, GameView gameView){
		controller = gameController;
		view = gameView;
	}
	
	/**
	 * getYesNo reads the user input and makes sure it's a y or an n.
	 * If the user enters anything else, s/he is asked to redo it.
	 * @return 'y' or 'n'
	 */
	public String getYesNo(){
		boolean stopLoop = false;
		String input;
		do{
			input = getStringInput();
			if (testUserInput(input)){
				stopLoop = true;
			} else {
				view.printnl("I did not understand that. Write y or n");
			}
		}while(!stopLoop);
		return input;
	}
	
	/**
	 * getStringInput calls the method getStringInput from {@link GameController}
	 * and returns the users input.
	 * @return Returns user input.
	 */
	private String getStringInput(){
		String input = controller.getStringInput();
		return input;
	}
	/**
	 * testUserInput tests the user input to see if it is a 'y' or 'n'
	 * @param input - the input given by the user
	 * @return True if the input is 'y' or 'n'. False otherwise
	 */
	private boolean testUserInput(String input){
		if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n") ){
			return true;
		} else {
			return false;
		}
	}
	
	

}
