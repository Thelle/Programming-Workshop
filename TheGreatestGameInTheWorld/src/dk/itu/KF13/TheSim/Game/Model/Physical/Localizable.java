package dk.itu.KF13.TheSim.Game.Model.Physical;

import dk.itu.KF13.TheSim.Game.Model.World.Location;

public interface Localizable {
	/** Returns the current location */
	Location getLocation();
	
	/** Sets the location */
	boolean setLocation(Location location);
}
