package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable.ObjectType;
import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocBrewery extends MasterLocation {

	public LocBrewery(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
		objectsAtLocation.add(new ObjBottle(true, BottleType.MASTERBREW));
		objectsAtLocation.add(new ObjNonMovable(false, ObjectType.BARREL));
		
	}

	public String getDescription() {
		String returnString =  "You are standing at the Carlsberg Brewery. " +
				"People are working hard.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	public void locationSpecificAction() {
			GameView.print("You can play blackjack here");
	}
}
