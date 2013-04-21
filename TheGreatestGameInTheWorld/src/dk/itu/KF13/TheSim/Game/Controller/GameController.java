package dk.itu.KF13.TheSim.Game.Controller;

import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Physical.HumanPlayer;
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
	
	public void runGame(){
		boolean boContinue = true;
		do {
			boContinue = getCommand();			
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
		switch (input){
		case "go north": movePlayer(Direction.NORTH);return true;
		case "go south": movePlayer(Direction.SOUTH);return true;
		case "go west": movePlayer(Direction.WEST);return true;
		case "go east": movePlayer(Direction.EAST);return true;
		case "exit game":return false;
		default:System.out.println("I did not understand the input.");return true;
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
