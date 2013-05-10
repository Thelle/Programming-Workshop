package dk.itu.KF13.TheSim.Game.Model;

import dk.itu.KF13.TheSim.Game.Controller.IGameController;
import dk.itu.KF13.TheSim.Game.Model.Minigames.BlackJack.BlackJack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Model.World.Class.LocBrewery;
import dk.itu.KF13.TheSim.Game.Model.World.Class.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location.Direction;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 * GameRunner is responsible for running the game.
 * It creates the world and keeps track of winning and losing conditions 
 * @author Simon
 */
public class GameRunner {
	private IGameController controller;
	private GameView view;
	public static WorldCopenhagen copenhagen;	
	private Location[][] worldLocations;
	private HumanPlayer player;
	
	private static boolean stopGame = false;
	
	/**
	 * setter for the private attribute controller
	 * @param gameController
	 */
	public void setGameController(IGameController gameController){
		controller = gameController;
	}
	
	public void setGameView(GameView gameView){
		view = gameView;
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
		copenhagen = new WorldCopenhagen(view);
		//Populates the world with locations
		worldLocations = copenhagen.getLocations();		
	}
	
	/**
	 * createPlayerCharacter creates the player character and 
	 * sets the location of the player
	 */
	private void createPlayerCharacter(){
		//Creates player
		player = new HumanPlayer(view);
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
		
		if (doesPlayerWantToStart()){
			controller.printHelpText();
			player.announceArrival();
			boolean boContinue = true;
			do {
				if(checkAlcoholLevel() && !playerHasTenBeers()){
					boContinue = controller.getCommand();
					view.print("");
				} else{
					boContinue = false;
				}
			}while(boContinue && !stopGame);
		
			if (playerHasTenBeers()){
				view.print("Congratulations. You now have ten beers, and are ready to party. YOU WON!");
			}
		} else {
			view.print("Your loss..");
		}
	}
	
	private boolean doesPlayerWantToStart(){
		printStartText();
		boolean doLoop = true;
		boolean status = false;
		
		do {
			String input = controller.getStringInput();
			switch (input.toLowerCase()){
			case "yes": 
				status = true; 
				doLoop = false; 
				view.printnl("Great! Before we start you should know the basic commands of the game:");
				break;
			case "no": 
				status = !doesPlayerReallyWantToStop();
				doLoop = false;
				break;				
			default: view.print("I did not understand that. Write yes or no"); doLoop = true;
			}
		} while(doLoop);
		return status;		
	}
	
	private boolean doesPlayerReallyWantToStop(){
		int numberOfNo = 1;
		boolean doLoop = true;
		boolean annoyPlayer = true;
		do {
			if (numberOfNo <= 10){
				view.print("Are you " + addReally(numberOfNo) + "sure");
				numberOfNo++;
				String input = controller.getStringInput();
				switch (input.toLowerCase()){
				case "yes":
					annoyPlayer = true; doLoop = true; break;
				case "no":
					annoyPlayer = false; doLoop = false; break;					
				}				
			}else{
				doLoop = false;
			}
		} while (doLoop);
		
		return annoyPlayer;
	}
	
	private String addReally(int numberOfReally){
		String output = "";
		for (int i = 0; i < numberOfReally; i++){
			output = output + "really ";
		}
		return output;
	}
	private void printStartText(){
		view.printnl("Studying is hard. Especially at the ITU. You have to get up and be sober every day of the week. No time for fun.\n" +
				"Except from friday that is. Friday is the best day of the week. At Fridays you can start the day with a film, and end it " +
					"with beer. \nLots of nice cold beer. \n"+
					"But not today. Today is a disatrous day. You just got a text from your favourite bartender from ScrollBar: \n'OMG!! No "+
					"Beeerzz left. U hv 2 drink breezers 2night. LOL!!'. \nOk maybe not you favourite bartender, but at least you got a warning "+
					"about what a disatrous evening this could have been. Now you just have to go out and get ten Masterbrews for tonight. As "+
					"everyone knows, ten is the perfect number of beers for a quiet night.\n"+
					"So, are you up for saving your evening?\n" +
					"Answer 'yes' or 'no'");
	}
	
	/**
	 * checkAlcoholLevel checks the alcohol level of the player to see if
	 * the player can continue
	 * @return true if the alcohol level is between 0 (exclusive) and 10 (inclusive)
	 * @return false if the level is zero or less or more than twenty
	 */
	private boolean checkAlcoholLevel(){
		int alcoholLevel = player.getAlcoholLevel();
		if(alcoholLevel <= 0){
			view.printnl("You're too sober and fall asleep - GAME OVER");
			return false;
		} else if(alcoholLevel > 10){
			view.printnl("You're too drunk and fall asleep - GAME OVER");
			return false;
		} else{
			view.printnl("Alcohol level: "+alcoholLevel+" / 10");
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
		BlackJack blackJack = new BlackJack(controller, view);
		if(player.getLocation() instanceof LocBrewery){
			if(player.lookForSpecificItem("a masterbrew") >= 2){
				blackJack.playBlackJack(player);
				blackJack = null;
			} else{
				view.printnl("You need two beers to play");
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
			view.printnl("You can't move this way");
		}
	}
	/**
	 * Check if the player has ten or more beers
	 * @return true if player has ten or more beers
	 */
	private boolean playerHasTenBeers(){
		return player.lookForSpecificItem("a masterbrew") >= 10;
	}
}
