package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public class LocCityHall implements Location {
	int xPos, yPos;
	
	public LocCityHall (int xInput, int yInput){
		xPos = xInput;
		yPos = yInput;
	}
	
	@Override
	public List<Location> getExits(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		return "This is the City hall. Everybody is looking very important.";
	}

}
