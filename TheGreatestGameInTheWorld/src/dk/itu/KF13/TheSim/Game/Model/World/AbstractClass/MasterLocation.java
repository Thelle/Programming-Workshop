package dk.itu.KF13.TheSim.Game.Model.World.AbstractClass;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.World.Class.WorldCopenhagen;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.View.GameView;

public abstract class MasterLocation implements Location {
	
	private int xPos, yPos;
	private String name;
	private Location[][] tempMap;
	private GameView view;
	private List<GameObject> objectsAtLocation = new ArrayList<GameObject>();
	private WorldCopenhagen copenhagen;
	
	/**
	 * Constructor of the abstract class MasterLocation.
	 * Places the location on the game map and gives it a name
	 * @param xInput - the x-coordinate of the location
	 * @param yInput - the y-coordinate of the location
	 * @param locationName - the name of the location
	 */
	public MasterLocation (int xInput, int yInput, String locationName, 
			GameView gameView, WorldCopenhagen world){
		xPos = xInput;
		yPos = yInput;
		name = locationName;
		setView(gameView);
		setCopenhagen(world);
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
		getView().print(description);
		this.locationSpecificAction();
		printExits();
		
	}
	
	
	public void printExits(){
		for (Direction dir : Direction.values()){
			if (getExits(dir) == null){
				getView().print(dir + ": nothing");
			} else {
				getView().print(dir + ": " +  getExits(dir).getName());
			}
		}
	}
	
	public boolean placeObject(GameObject object){
		getObjectsAtLocation().add(object);
		return true;
	}
	
	public void removeObject(GameObject object){
		getObjectsAtLocation().remove(object);
	}
	
	public String getObjectDescriptions(){
		String returnString = "";
		if (getObjectsAtLocation().isEmpty()){
			return "No objects to be found at this location";
		}else {
			returnString = "Objects at location: \n";
			for (int i = 0; i < getObjectsAtLocation().size(); i++){
				returnString += getObjectsAtLocation().get(i).getDescription() + "\n";
			}
		}
		return returnString;
	}
	
	public List<GameObject> getObjects(){
		return getObjectsAtLocation();
	}
	
	public String getName(){
		return name;
	}	
	
	public abstract void locationSpecificAction();
	
	public abstract String getDescription();

	public List<GameObject> getObjectsAtLocation() {
		return objectsAtLocation;
	}

	public void setObjectsAtLocation(List<GameObject> objectsAtLocation) {
		this.objectsAtLocation = objectsAtLocation;
	}

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public WorldCopenhagen getCopenhagen() {
		return copenhagen;
	}

	public void setCopenhagen(WorldCopenhagen copenhagen) {
		this.copenhagen = copenhagen;
	}
}
