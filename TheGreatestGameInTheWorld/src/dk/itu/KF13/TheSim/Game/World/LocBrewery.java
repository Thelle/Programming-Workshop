package dk.itu.KF13.TheSim.Game.World;

import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;
import dk.itu.KF13.TheSim.Game.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Physical.ObjStatic;
import dk.itu.KF13.TheSim.Game.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Physical.ObjStatic.ObjectType;

public class LocBrewery extends MasterLocation {

	public LocBrewery(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
		objectsAtLocation.add(new ObjBottle(true, BottleType.MASTERBREW));
		objectsAtLocation.add(new ObjStatic(false, ObjectType.BARREL));
		
	}

	@Override
	public String getDescription() {
		String returnString =  "You are standing at the Carlsberg Brewery. People are working hard.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	@Override
	public void locationSpecificAction() {
		System.out.println("Playing Black Jack is not implemented yet");
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to play Black Jack?");
		boolean doLoop = true;
		do{
			String input = sc.nextLine();
			input = input.toLowerCase();
			switch(input){
			case "yes": playBlackJack();doLoop = false;break;
			case "no": doLoop = false; break;
			default: 
				System.out.println("I don't understand. Input 'yes' or 'no'.");
				doLoop= true;
			}
		} while(doLoop);
		*/
	}
	private void playBlackJack(){
		BlackJack blackJack = new BlackJack();
	    blackJack.playBlackJack();
	}

}
