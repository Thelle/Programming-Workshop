package dk.itu.KF13.TheSim.Game.Model.World;

import dk.itu.KF13.TheSim.Game.Model.Physical.ObjGun;

public class LocPoliceStation extends MasterLocation {

	public LocPoliceStation(int xInput, int yInput, String name) {
		super(xInput, yInput, name);
		objectsAtLocation.add(new ObjGun(true));
	}

	@Override
	public String getDescription() {
		String returnString;
		returnString =  "This is the police station. You see a criminal being beaten by five big police officers.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	@Override
	public void locationSpecificAction() {
		// No location specific actions at the police station
		
	}

}
