package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;

public class LocITU extends MasterLocation {

	public LocITU(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
	}

	@Override
	public String getDescription() {
		String returnString =  "You are at ITU!";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	@Override
	public void locationSpecificAction() {
		// No location specific actions at ITU
		
	}

}
