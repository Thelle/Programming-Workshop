package dk.itu.KF13.TheSim.Game.Model.World;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjStatic;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjStatic.ObjectType;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocBrewery extends MasterLocation {

	public LocBrewery(int xInput, int yInput, String name, 
			GameController controller, GameView view) {
		super(xInput, yInput, name, controller, view);
		objectsAtLocation.add(new ObjBottle(true, BottleType.MASTERBREW));
		objectsAtLocation.add(new ObjStatic(false, ObjectType.BARREL));
		
	}

	public String getDescription() {
		String returnString =  "You are standing at the Carlsberg Brewery. " +
				"People are working hard.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	public void locationSpecificAction() {
		view.print("You can play blackjack here");
	}
}
