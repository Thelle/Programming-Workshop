package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public abstract class MasterLocation implements Location {
	int xPos, yPos;
	
	@Override
	public List<Location> getExits(Direction direction) {
		int testXPos, testYPos;
		
		switch(direction){
		case NORTH: testXPos = xPos; testYPos = yPos+1;
		case EAST: testXPos = xPos+1; testYPos = yPos;
		case SOUTH: testXPos = xPos; testYPos = yPos-1;
		case WEST: testXPos = xPos-1; testYPos = yPos;
		}
		
		
		
		return null;
	}
	
	public MasterLocation (int xInput, int yInput){
		xPos = xInput;
		yPos = yInput;
	}

	@Override
	public abstract String getDescription();
}
