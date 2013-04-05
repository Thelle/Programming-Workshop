package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public class LocEmpty implements Location {
	int xPos, yPos;
	
	public LocEmpty (int xInput, int yInput){
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
		return "Here is empty.";
	}

}
