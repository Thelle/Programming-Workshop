package dk.itu.KF13.TheSim.Game.Controller;

import java.util.List;
import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;
import dk.itu.KF13.TheSim.Game.Physical.GameObject;
import dk.itu.KF13.TheSim.Game.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.World.LocBrewery;
import dk.itu.KF13.TheSim.Game.World.Location;
import dk.itu.KF13.TheSim.Game.World.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

/**
 * GameController creates and controls the game and stores the game world 
 * and the player character
 * @author Simon & Thelle
 *
 */
public class GameController {
	public static WorldCopenhagen copenhagen;	
	Location[][] worldLocations;
	public HumanPlayer player;
	private static boolean stopGame = false;
	
	/**
	 * GameController is the constructor.
	 * It calls the methods that creates the world and the player character.
	 */
	public GameController(){
		createWorld();
		createPlayerCharacter();
	}
	/**
	 * createWorld creates and populates the game world with locations
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
		player.setLocation(worldLocations[1][0]);
	}
	/**
	 * returnWorld returns the world stored in the in the GameController
	 * @return a WorldCopenhagen object
	 */
	public static WorldCopenhagen returnWorld(){
		return copenhagen;
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
			System.out.println("You're too sober and fall asleep - GAME OVER");
			return false;
		} else if(alcoholLevel > 20){
			System.out.println("You're too drunk and fall asleep - GAME OVER");
			return false;
		} else{
			System.out.println("Alcohollevel: "+alcoholLevel+" / 20");
			return true;
		}
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
				boContinue = getCommand();		
			} else{
				boContinue = false;
			}
		}while(boContinue && !stopGame);
	}
	
	/**
	 * stopGame is called from other methods if the game has to be stopped 
	 * if the player dies.
	 */
	static public void stopGame(){
		stopGame=true;
	}
	
	/**
	 * Takes written user input and runs the method according to command.
	 * @return true if the player doesn't choose to stop the game
	 * @return false if the player chooses to stop the game
	 */
	private boolean getCommand(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What do you want to do?");
		String input = sc.nextLine();
		input = input.toLowerCase();
		if (input.matches("go [a-z]*")){
			testMovement(input);
			return true;
		}
		else if (input.matches("take .*")){
			takeItem(input);
			return true;
		} 
		else if (input.matches("use .*")){
			useItem(input);
			return true;
		} 
		else if (input.matches("exit game")){
			return false;
		} 
		else if(input.matches("look in backpack")){
			lookInBackpack();
			return true;
		}
		else if(input.matches("play blackjack")){
			blackjackConditionsCheck();
			return true;
		}
		else {
			System.out.println("I did not understand the input.");
			return true;
		}
	}
	
	/*
	 * blackjackConditionsCheck checks if the player is in the right location and
	 * has enough beers to play the game. 
	 * Starts the game if both is true.
	 */
	private void blackjackConditionsCheck(){
		BlackJack blackJack = new BlackJack();
		if(player.getLocation() instanceof LocBrewery){
			if(numberOfBeersInBackpack() >= 2){
				System.out.println("You will now play blackjack");
				blackJack.playBlackJack(player);
				blackJack = null;
			} else{
				System.out.println("You need 2 beers to play");
			}
		}
	}
		
	/**
	 * numberOfBeersInBackpack loops through the backpack and returns
	 * the number of beers
	 * @return then number of beers in the backpack
	 */
	private int numberOfBeersInBackpack(){
		int numberOfBeers = 0;
		List<GameObject> objectsInBackpack = player.returnContentOfBackpack();
		for(int i = 0; i < objectsInBackpack.size();i++){
			String description = objectsInBackpack.get(i).getDescription();
			if(description.equalsIgnoreCase("a masterbrew")){
				numberOfBeers++;
			}
		}
		return numberOfBeers;
	}
	
	/**
	 * Prints out a list of all the items in the player's backpack
	 */
	private void lookInBackpack(){
		List<GameObject> objectsInBackpack = player.returnContentOfBackpack();
		System.out.println("Objects in backpack:");
		for(int i = 0; i < objectsInBackpack.size();i++){
			System.out.println(objectsInBackpack.get(i).getDescription());
		}
	}
	
	/**
	 * useItem loops through the objects in the player's backpack and
	 * compares the description of the item with the object that
	 * the user has written.
	 * The comparison is done by removing "a" from the description of the 
	 * item and then compare with the input by adding "use" before the description.
	 * If the item is not in the backpack, the item is not used and the user is warned
	 * that the item is not in the backpack
	 * @param input - the string input that the user has written. Starts with "use".
	 */
	private void useItem(String input){
		List<GameObject> objectsInBackpack = player.returnContentOfBackpack();
		for(int i = 0; i < objectsInBackpack.size();i++){
			String objectName = objectsInBackpack.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			if(input.equalsIgnoreCase("use "+objectName)){
				player.use(objectsInBackpack.get(i));
				System.out.println("You used "+objectName);
				return;
			}
		}
		System.out.println("No such item in backpack");
	}
	
	/**
	 * Loops through the items at the player location and compare the player's
	 * input with the descriptions of the items at the location.
	 * @param - the written user input
	 */
	private void takeItem(String input){
		Location playerLocation = player.getLocation();
		List<GameObject> objectsAtLocation = playerLocation.getObjects();
		for(int i = 0; i < objectsAtLocation.size();i++){
			String objectName = objectsAtLocation.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			objectName = "take "+objectName;
			if(input.equalsIgnoreCase(objectName)){
				boolean boStatus = player.take(objectsAtLocation.get(i));
				if(boStatus){
					System.out.println("You have taken the object");
				} else{
					System.out.println("There was no room in your backpack or you can't take the item");
				}
				return;
			}
		}
		System.out.println("No such item");
	}
	
	/**
	 * testMovement parse the user input and calls the movePlayer method 
	 * to move the player in the correct direction
	 * @param input - the written user input
	 */
	private void testMovement(String input){
		switch (input){
		case "go north": movePlayer(Direction.NORTH);break;
		case "go south": movePlayer(Direction.SOUTH);break;
		case "go west": movePlayer(Direction.WEST);break;
		case "go east": movePlayer(Direction.EAST);break;
		default:System.out.println("I did not understand the direction.");break;
		}
	}
	/**
	 * movePlayer moves the player character.
	 * @param direction
	 */
	private void movePlayer(Direction direction){
		boolean hasMoved = player.move(direction);
		if (!hasMoved){
			System.out.println("You can't move this way");
		}
	}
}
