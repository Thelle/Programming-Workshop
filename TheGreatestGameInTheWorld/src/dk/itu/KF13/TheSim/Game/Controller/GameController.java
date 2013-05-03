package dk.itu.KF13.TheSim.Game.Controller;

import java.util.List;
import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;
import dk.itu.KF13.TheSim.Game.Physical.GameObject;
import dk.itu.KF13.TheSim.Game.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.World.LocBrewery;
import dk.itu.KF13.TheSim.Game.World.Location;
import dk.itu.KF13.TheSim.Game.World.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

/**
 * GameController creates the game and stores the game world 
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
	 * createWorld creates and populates the game world
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
		
	public static WorldCopenhagen returnWorld(){
		return copenhagen;
	}
	
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
	
	static public void stopGame(){
		stopGame=true;
	}
	
	private boolean getCommand(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What do you want to do?");
		String input = sc.nextLine();
		input = input.toLowerCase();
		if (input.matches("go [a-z]*")){
			return testMovement(input);
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
			return true;
		}
		else {
			System.out.println("I did not understand the input.");
			return true;
		}
	}
	
	private void blackjackConditionsCheck(){
		BlackJack blackJack = new BlackJack();
		if(player.getLocation() instanceof LocBrewery){
			if(numberOfBeersInBackpack() >= 2){
				boolean hasWon = blackJack.playBlackJack();
			} else{
				System.out.println("You need 2 beers to play");
			}
		}
	}
	
	private void removeBeersFromBackpack(int numberOfBeers){
		List<GameObject> objectsInBackpack = player.returnContentOfBackpack();
		int beersRemoved = 0;
		for(int i = 0; i < objectsInBackpack.size() || beersRemoved < numberOfBeers;i++){
			String description = objectsInBackpack.get(i).getDescription();
			if(description.equalsIgnoreCase("a masterbrew")){
				player.removeFromBackPack(objectsInBackpack.get(i));
				beersRemoved++;
			}
		}
	}
	
	private void addBeersToBackpack(int numberOfBeers){
		//TO DO - add number of bbbbbbeeeeeeerrrrrrsssss lolz - me need lunch & sleep!!
	}
	
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
	
	private void lookInBackpack(){
		List<GameObject> objectsInBackpack = player.returnContentOfBackpack();
		System.out.println("Objects in backpack:");
		for(int i = 0; i < objectsInBackpack.size();i++){
			System.out.println(objectsInBackpack.get(i).getDescription());
		}
	}
	
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
	
	private boolean testMovement(String input){
		switch (input){
		case "go north": movePlayer(Direction.NORTH);return true;
		case "go south": movePlayer(Direction.SOUTH);return true;
		case "go west": movePlayer(Direction.WEST);return true;
		case "go east": movePlayer(Direction.EAST);return true;
		default:System.out.println("I did not understand the direction.");return true;
		}
	}
	/**
	 * movePlayer moves the player character.
	 * If move is successful, the player pays in alcohol. If not, nothing happens
	 * @param direction
	 */
	private void movePlayer(Direction direction){
		boolean hasMoved = player.move(direction);
		if (hasMoved){
			//Player move successful. Pay alcohol;
		} else {
			//Player move unsuccessful.
		}
	}
}
