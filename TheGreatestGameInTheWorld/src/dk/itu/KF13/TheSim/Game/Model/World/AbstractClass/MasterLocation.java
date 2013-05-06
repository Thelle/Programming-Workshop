package dk.itu.KF13.TheSim.Game.Model.World.AbstractClass;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.World.Class.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.View.GameView;

public abstract class MasterLocation implements Location {
	
	private int xPos, yPos;
	private String name;
	private Location[][] tempMap;
	protected List<GameObject> objectsAtLocation = new ArrayList<GameObject>();
	
	/**
	 * Constructor of the abstract class MasterLocation.
	 * Places the location on the game map and gives it a name
	 * @param xInput - the x-coordinate of the location
	 * @param yInput - the y-coordinate of the location
	 * @param locationName - the name of the location
	 */
	public MasterLocation (int xInput, int yInput, String locationName){
		xPos = xInput;
		yPos = yInput;
		name = locationName;
	}
	
	public Location getExits(Direction direction) {
		int newXPos, newYPos;
		getMap();
		switch(direction){
		case NORTH: newXPos = xPos; newYPos = yPos+1; break;
		case EAST: newXPos = xPos+1; newYPos = yPos; break;
		case SOUTH: newXPos = xPos; newYPos = yPos-1; break;
		case WEST: newXPos = xPos-1; newYPos = yPos; break;
		default: newXPos = -1; newYPos = -1;
		}
		return newLocation(newXPos, newYPos);				
	}
	
	/**
	 * newLocation returns a pointer to location on the map specified
	 * by the given coordinates
	 * @param xPos - the x-coordinate
	 * @param yPos - the y-coordinate
	 * @return null if the coordinates are out of bounds on the map. 
	 * Returns the location otherwise
	 */
	private Location newLocation(int xPos, int yPos){
		if (outOfBounds(xPos, yPos)){
			return null;
		}
		else{
			return tempMap[xPos][yPos];
		}
	}	
	
	/**
	 * getMap creates a pointer to the world map and saves it in the attribute tempMap
	 */
	private void getMap(){
		WorldCopenhagen copenhagen = GameRunner.getWorld();
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
		GameView.print(description);
		this.locationSpecificAction();
		printExits();
		
	}
	
	
	public void printExits(){
		for (Direction dir : Direction.values()){
			if (getExits(dir) == null){
				GameView.print(dir + ": nothing");
			} else {
				GameView.print(dir + ": " +  getExits(dir).getName());
			}
		}
	}
	
	public boolean placeObject(GameObject object){
		objectsAtLocation.add(object);
		return true;
	}
	
	public void removeObject(GameObject object){
		objectsAtLocation.remove(object);
	}
	
	public String getObjectDescriptions(){
		String returnString = "";
		if (objectsAtLocation.isEmpty()){
			return "No objects to be found at this location";
		}else {
			for (int i = 0; i < objectsAtLocation.size(); i++){
				returnString += objectsAtLocation.get(i).getDescription() + "\n";
			}
		}
		return returnString;
	}
	
	public List<GameObject> getObjects(){
		return objectsAtLocation;
	}
	
	public String getName(){
		return name;
	}	
	
	public abstract void locationSpecificAction();
	
	public abstract String getDescription();
}
