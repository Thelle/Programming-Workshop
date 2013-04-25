package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Physical.ObjStatic;
import dk.itu.KF13.TheSim.Game.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Physical.ObjStatic.ObjectType;

public class LocCityHall extends MasterLocation {

	public LocCityHall(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
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
		// TODO Auto-generated method stub
		
	}

}
