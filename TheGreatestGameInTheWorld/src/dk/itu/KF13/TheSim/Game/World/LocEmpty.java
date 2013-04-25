package dk.itu.KF13.TheSim.Game.World;

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
		// TODO Auto-generated method stub
		
	}

}
