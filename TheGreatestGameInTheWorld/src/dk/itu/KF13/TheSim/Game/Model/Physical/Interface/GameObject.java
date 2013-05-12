package dk.itu.KF13.TheSim.Game.Model.Physical.Interface;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;

public interface GameObject extends Localizable {
	
	/**
	 * Reads the items attribute to see if the object can be taken by the human player
	 * @return true if the object can be taken. False otherwise.
	 */
	boolean canBeTaken();
	
	/**
	 * @return a description of the object
	 */
	String getDescription();
	
	Location getLocation();
	
	/**
	 * putInBackpack places an object in a backpack if there is room and the object can be taken
	 * @param backpack the backpack where the item is placed
	 * @return true if the object is placed in the backpack
	 * @return false if the object is not placed in the backpack
	 */
	boolean putInBackpack(Backpack backpack);
	
	boolean setLocation(Location location);
	
	/**
	 * Executes the items specific use method. 
	 * Not all items have a use method
	 * @return returns the alcohol cost of using the item
	 */
	int use();
	
}
