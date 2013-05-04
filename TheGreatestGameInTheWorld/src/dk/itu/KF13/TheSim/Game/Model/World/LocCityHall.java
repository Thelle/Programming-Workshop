package dk.itu.KF13.TheSim.Game.Model.World;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjStatic;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjStatic.ObjectType;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocCityHall extends MasterLocation {

	public LocCityHall(int xInput, int yInput, String name, 
			GameController controller, GameView view) {
		super(xInput, yInput, name, controller, view);
		objectsAtLocation.add(new ObjBottle(true, BottleType.WATER));
		objectsAtLocation.add(new ObjStatic(false, ObjectType.STATUE));
	}

	@Override
	public String getDescription() {
		String returnString =  "This is the City hall. Everybody is looking very important.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	@Override
	public void locationSpecificAction() {
		// No location specific actions at the City Hall
		
	}

}
