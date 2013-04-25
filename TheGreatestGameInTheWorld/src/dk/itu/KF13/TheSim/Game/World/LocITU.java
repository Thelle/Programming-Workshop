package dk.itu.KF13.TheSim.Game.World;

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
		// TODO Auto-generated method stub
		
	}

}
