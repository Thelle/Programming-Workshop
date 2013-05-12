package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjGun;
import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;
import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * LocPoliceStation is only used as the place where the gun is.
 * Nothing else happens here.
 * @author Simon
 *
 */
public class LocPoliceStation extends MasterLocation {

	public LocPoliceStation(int xInput, int yInput, String name, GameView gameView, WorldCopenhagen copenhagen) {
		super(xInput, yInput, name, gameView, copenhagen);
		getObjectsAtLocation().add(new ObjGun(true, gameView));
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
