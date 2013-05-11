package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocITU extends MasterLocation {

	public LocITU(int xInput, int yInput, String name, GameView gameView, WorldCopenhagen copenhagen) {
		super(xInput, yInput, name, gameView, copenhagen);
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
