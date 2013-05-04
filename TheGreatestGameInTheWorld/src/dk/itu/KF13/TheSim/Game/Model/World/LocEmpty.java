package dk.itu.KF13.TheSim.Game.Model.World;

import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle.BottleType;

public class LocEmpty extends MasterLocation {

	public LocEmpty(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
	}

	@Override
	public String getDescription() {
		String returnString =  "Here is empty.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	@Override
	public void locationSpecificAction() {
		ObjBottle beer1 = new ObjBottle(true, BottleType.MASTERBREW);
		ObjBottle beer2 = new ObjBottle(true, BottleType.MASTERBREW);
		this.placeObject(beer1);
		this.placeObject(beer2);
		
	}

}
