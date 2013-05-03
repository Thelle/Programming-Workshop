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

	public String getDescription() {
		String returnString =  "You are standing at the Carlsberg Brewery. People are working hard.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	public void locationSpecificAction() {
			System.out.println("You can play blackjack here");
	}
}
