package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Controller.GameController;

public abstract class MasterLocation implements Location {
	
	int xPos, yPos;
	private Location[][] tempMap;
	
	public MasterLocation (int xInput, int yInput){
		xPos = xInput;
		yPos = yInput;
	}
	
	@Override
	public Location getExits(Direction direction) {
		int testXPos, testYPos;
		getMap();
		switch(direction){
		case NORTH: testXPos = xPos; testYPos = yPos+1; break;
		case EAST: testXPos = xPos+1; testYPos = yPos; break;
		case SOUTH: testXPos = xPos; testYPos = yPos-1; break;
		case WEST: testXPos = xPos-1; testYPos = yPos; break;
		default: testXPos = -1; testYPos = -1;
		}
		return newLocation(testXPos, testYPos);				
	}
	
	private Location newLocation(int testXPos, int testYPos){
		if (outOfBounds(testXPos, testYPos)){
			return null;
		}
		else{
			return tempMap[testXPos][testYPos];
		}
	}	
	
	private void getMap(){
		WorldCopenhagen copenhagen = GameController.returnWorld();
		tempMap = copenhagen.getLocations();
	}
	
	/**
	 * outOfBounds tests if the tested position is within the bounds of the map (array) of WorldCopenhagen.
	 * @param testXPos
	 * @param testYPos
	 * @return true if out of bounds.
	 */
	private boolean outOfBounds (int testXPos, int testYPos){
		int xUpperBound, yUpperBound;
		xUpperBound = tempMap.length;
		yUpperBound = tempMap[1].length;
		
		if (testXPos >=0 && testXPos < xUpperBound && testYPos >=0 && testYPos < yUpperBound){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void playerHasArrived(){
		String description = this.getDescription();
		System.out.println(description);
		this.locationSpecificAction();
	}
	
	public abstract void locationSpecificAction();
	
	@Override
	public abstract String getDescription();
}
