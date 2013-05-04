package dk.itu.KF13.TheSim.Game.Model;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Model.Minigames.BlackJack;
import dk.itu.KF13.TheSim.Game.Model.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Model.World.LocBrewery;
import dk.itu.KF13.TheSim.Game.Model.World.Location;
import dk.itu.KF13.TheSim.Game.Model.World.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.Model.World.Location.Direction;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 * GameRunner is responsible for running the game.
 * It creates the world and keeps track of winning and losing conditions 
 * @author Simon
 */
public class GameRunner {
	private GameController controller;
	public static WorldCopenhagen copenhagen;	
	private Location[][] worldLocations;
	private HumanPlayer player;
	
	private static boolean stopGame = false;
	
	/**
	 * setter for the private attribute controller
	 * @param gameController
	 */
	public void setGameController(GameController gameController){
		controller = gameController;
	}
	
	/**
	 * Setter for the private attribute view.
	 * @param gameView
	 */
	public void setGameView(GameView gameView){
	}
	
	/**
	 * createGame creates the world and the player
	 */
	public void createGame(){
		createWorld();
		createPlayerCharacter();
	}
	
	/**
	 * createWorld creates the game world and reads the locations in the world
	 */
	private void createWorld(){
		//Creates the world
		copenhagen = new WorldCopenhagen();
		//Populates the world with locations
		worldLocations = copenhagen.getLocations();		
	}
	
	/**
	 * createPlayerCharacter creates the player character and 
	 * sets the location of the player
	 */
	private void createPlayerCharacter(){
		//Creates player
		player = new HumanPlayer();
		//Places player in the world
		player.setLocation(worldLocations[1][0]); //The coordinates for ITU
	}
	
	/**
	 * runGame is the main game runner. 
	 * It starts a new turn as long as the alcohol level is acceptable.
	 * If the player dies or chooses to exit the game during a turn, 
	 * the game will also stop.
	 */
	public void runGame(){
		boolean boContinue = true;
		do {
			if(checkAlcoholLevel()){
				boContinue = controller.getCommand();		
			} else{
				boContinue = false;
			}
		}while(boContinue && !stopGame);
	}
	
	/**
	 * checkAlcoholLevel checks the alcohol level of the player to see if
	 * the player can continue
	 * @return true if the alcohol level is between 0 (exclusive) and 20 (inclusive)
	 * @return false if the level is zero or less or more than twenty
	 */
	private boolean checkAlcoholLevel(){
		int alcoholLevel = player.getAlcoholLevel();
		if(alcoholLevel <= 0){
			GameView.print("You're too sober and fall asleep - GAME OVER");
			return false;
		} else if(alcoholLevel > 20){
			GameView.print("You're too drunk and fall asleep - GAME OVER");
			return false;
		} else{
			GameView.print("Alcohollevel: "+alcoholLevel+" / 20");
			return true;
		}
	}
	
	/**
	 * stopGame is called from other methods if the game has to be stopped 
	 * if the player dies.
	 */
	static public void stopGame(){
		stopGame=true;
	}	
	
	/**
	 * blackjackConditionsCheck checks if the player is in the right location and
	 * has enough beers to play the game. 
	 * Starts the game if both is true.
	 */
	public void blackjackConditionsCheck(){
		BlackJack blackJack = new BlackJack();
		if(player.getLocation() instanceof LocBrewery){
			if(player.lookForSpecificItem("a masterbrew") >= 2){
				GameView.print("You will now play blackjack");
				blackJack.playBlackJack(player);
				blackJack = null;
			} else{
				GameView.print("You need two beers to play");
			}
		}
	}
	/**
	 * getPlayer returns the player object stored in the attribute player 
	 * @return a pointer to the created human player object
	 */
	public HumanPlayer getPlayer(){
		return player;
	}
	
	/**
	 * getPlayer returns the world object stored in the attribute copenhagen 
	 * @return a pointer to the created WorldCopenhagen object
	 */
	public static WorldCopenhagen getWorld(){
		return copenhagen;
	}
	
	/**
	 * movePlayer moves the player character.
	 * @param direction - the direction in which the player wants to move
	 */
	public void movePlayer(Direction direction){
		boolean hasMoved = player.move(direction);
		if (!hasMoved){
			GameView.print("You can't move this way");
		}
	}
}
