package dk.itu.KF13.TheSim.Game.Model.World;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.GameObject;
import dk.itu.KF13.TheSim.Game.View.GameView;

public abstract class MasterLocation implements Location {
	
	private int xPos, yPos;
	private String name;
	private Location[][] tempMap;
	protected List<GameObject> objectsAtLocation = new ArrayList<GameObject>();
	protected GameController controller;
	protected GameView view;
	
	/**
	 * Constructor of the abstract class MasterLocation.
	 * Places the location on the game map and gives it a name
	 * @param xInput - the x-coordinate of the location
	 * @param yInput - the y-coordinate of the location
	 * @param locationName - the name of the location
	 */
	public MasterLocation (int xInput, int yInput, String locationName, 
			GameController gameController, GameView gameView){
		xPos = xInput;
		yPos = yInput;
		name = locationName;
	}
	
	/**
	 * getExits returns the location in the direction specified relative to
	 * the location from where the method is called
	 * @param direction - the direction of the location to be returned.
	 * @return null if there is no location in the specified direction.
	 * returns the location otherwise.
	 */
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
	
	/**
	 * playerHasArrived prints the description of the current location,
	 * runs the specific action for the location and prints out the exits from the location
	 */
	public void playerHasArrived(){
		String description = this.getDescription();
		view.print(description);
		this.locationSpecificAction();
		printExits();
		
	}
	
	/**
	 * Pints out the names of the locations at the exits of the current location
	 */
	public void printExits(){
		for (Direction dir : Direction.values()){
			if (getExits(dir) == null){
				view.print(dir + ": nothing");
			} else {
				view.print(dir + ": " +  getExits(dir).getName());
			}
		}
	}
	
	/**
	 * placeObject adds the given object to the list of objects at the location
	 * @param object an object extending the interface GameObject
	 */
	public boolean placeObject(GameObject object){
		objectsAtLocation.add(object);
		return true;
	}
	
	/**
	 * removeObject removes the first occurrence of the given object from list of objects at the location
	 * @param object an object extending the interface GameObject
	 */
	public void removeObject(GameObject object){
		objectsAtLocation.remove(object);
	}
	
	/**
	 * getObjectDescriptions loops through all the objects at the location and 
	 * returns a string with the description of all the objects
	 * @return a string with the description of all objects at the location
	 */
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
	
	/**
	 * @return a list with all the objects at the location
	 */
	public List<GameObject> getObjects(){
		return objectsAtLocation;
	}
	
	/**
	 * @return returns the name of the location as given when the location
	 * was instantiated
	 */
	public String getName(){
		return name;
	}	
	
	/**
	 * Runs specific actions tied to the location.
	 * Not all location have location specific actions
	 */
	public abstract void locationSpecificAction();
	
	/**
	 * returns the description of the location
	 */
	public abstract String getDescription();
}
