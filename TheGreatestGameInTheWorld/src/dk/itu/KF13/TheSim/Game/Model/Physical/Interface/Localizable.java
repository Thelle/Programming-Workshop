package dk.itu.KF13.TheSim.Game.Model.Physical.Interface;

import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;

public interface Localizable {
	
	/**
	 * @return the current location
	 */
	Location getLocation();
	
	/**
	 * Sets the location
	 * @param location - the location where the object is to be placed
	 * @return true if it was possible to set the location
	 * @return false otherwise
	 */
	boolean setLocation(Location location);
}
