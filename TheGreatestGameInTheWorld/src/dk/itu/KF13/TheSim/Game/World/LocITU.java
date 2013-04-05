package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public class LocITU implements Location {
	int xPos, yPos;
	
	public LocITU (int xInput, int yInput){
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
		// TODO Auto-generated method stub
		return "You are at ITU!";
	}

}
