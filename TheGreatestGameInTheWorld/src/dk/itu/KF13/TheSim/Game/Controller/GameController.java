package dk.itu.KF13.TheSim.Game.Controller;

import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Model.World.Location.Direction;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 * GameController parses user input and calls the corresponding methods from the model
 * @author Simon & Thelle
 *
 */
public class GameController {
	
	private GameRunner runner;
	private HumanPlayer player;
	private GameView view;
	
	public void setGameRunner(GameRunner gameRunner){
		this.runner = gameRunner;
		
	}
	
	public void setPlayer (HumanPlayer player){
		this.player = player;
	}
	
	public void setGameView (GameView gameView){
		view = gameView;
	}
	/**
	 * Takes written user input and runs the method according to command.
	 * @return true if the player doesn't choose to stop the game
	 * @return false if the player chooses to stop the game
	 */
	public boolean getCommand(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		view.print("What do you want to do?");
		String input = sc.nextLine();
		input = input.toLowerCase();
		
		if (input.matches("go [a-z]*")){
			testMovement(input);
			return true;
		}
		else if (input.matches("take .*")){
			player.takeItem(input);
			return true;
		} 
		else if (input.matches("use .*")){
			player.useItem(input);
			return true;
		} 
		else if (input.matches("exit game")){
			return false;
		} 
		else if(input.matches("look in backpack")){
			player.lookInBackpack();
			return true;
		}
		else if(input.matches("play blackjack")){
			runner.blackjackConditionsCheck();
			return true;
		}
		else {
			view.print("I did not understand the input.");
			return true;
		}
	}	
	
	/**
	 * testMovement parse the user input and calls the movePlayer method 
	 * to move the player in the correct direction
	 * @param input - the written user input
	 */
	private void testMovement(String input){
		switch (input){
		case "go north": runner.movePlayer(Direction.NORTH);break;
		case "go south": runner.movePlayer(Direction.SOUTH);break;
		case "go west": runner.movePlayer(Direction.WEST);break;
		case "go east": runner.movePlayer(Direction.EAST);break;
		default:view.print("I did not understand the direction.");break;
		}
	}
	
}
