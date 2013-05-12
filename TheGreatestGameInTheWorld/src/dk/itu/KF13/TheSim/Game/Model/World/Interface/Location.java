package dk.itu.KF13.TheSim.Game.Model.World.Interface;

import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;


public interface Location {
	
	public enum Direction  {EAST, NORTH, WEST, SOUTH};
	
	/**
	 * Runs specific actions tied to the location.
	 * Not all location have location specific actions
	 */
	public abstract void locationSpecificAction();
	
	/**
	 * placeObject adds the given object to the list of objects at the location
	 * @param object an object extending the interface GameObject
	 */
	public boolean placeObject(GameObject object);
	
	/**
	 * Pints out the names of the locations at the exits of the current location
	 */
	public abstract void printExits();
	
	/**
	 * removeObject removes the first occurrence of the given object from list of objects at the location
	 * @param object an object extending the interface GameObject
	 */
	public void removeObject(GameObject object);
	
	/**
	 * Returns the description of the location
	 */
	String getDescription();
	
	/**
	 * getExits returns the location in the direction specified relative to
	 * the location from where the method is called
	 * @param direction - the direction of the location to be returned.
	 * @return null if there is no location in the specified direction.
	 * returns the location otherwise.
	 */
	Location getExits(Direction direction);
	
	/**
	 * @return returns the name of the location as given when the location
	 * was instantiated
	 */
	String getName();
	
	/**
	 * getObjectDescriptions loops through all the objects at the location and 
	 * returns a string with the description of all the objects
	 * @return a string with the description of all objects at the location
	 */
	String getObjectDescriptions();

	/**
	 * @return Returns a list with all the objects at the location
	 */
	List<GameObject> getObjects();

	/**
	 * playerHasArrived prints the description of the current location,
	 * runs the specific action for the location and prints out the exits from the location
	 */
	void playerHasArrived();

}
