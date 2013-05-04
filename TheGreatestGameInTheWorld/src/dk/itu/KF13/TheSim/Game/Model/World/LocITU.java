package dk.itu.KF13.TheSim.Game.Model.World;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocITU extends MasterLocation {

	public LocITU(int xInput, int yInput, String name, 
			GameController controller, GameView view) {
		super(xInput, yInput, name, controller, view);
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
