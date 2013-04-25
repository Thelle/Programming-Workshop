package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Physical.GameObject;
import dk.itu.KF13.TheSim.Game.Physical.ObjGun;

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
		// TODO Auto-generated method stub
		
	}

}
