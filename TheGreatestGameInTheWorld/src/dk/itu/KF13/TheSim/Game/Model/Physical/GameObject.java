package dk.itu.KF13.TheSim.Game.Model.Physical;

import dk.itu.KF13.TheSim.Game.Model.World.Location;

public interface GameObject extends Localizable {

	boolean canBeTaken();
	
	String getDescription();
	
	boolean setLocation(Location location);
	Location getLocation();
	
	boolean putInBackpack(Backpack backpack);
	
	int use();
	
}
