package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable.ObjectType;
import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;

public class LocCityHall extends MasterLocation {

	public LocCityHall(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
		objectsAtLocation.add(new ObjBottle(true, BottleType.WATER));
		objectsAtLocation.add(new ObjNonMovable(false, ObjectType.STATUE));
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
