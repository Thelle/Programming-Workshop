package dk.itu.KF13.TheSim.Game.World;

import java.util.ArrayList;
import java.util.List;
/**
 * The WorldCopenhagen class is the world the for game.
 * @author Thelle & Simon
 * @version 2013-04-05
 */
public class WorldCopenhagen implements World {
	List<Location> worldLocations = new ArrayList<Location>();
	
	Location[][] locationMap = new Location [3][3];
	
	/**
	 * WorldCopenhagen generates the map of the game world and creates the locations.
	 */
	public WorldCopenhagen() {
		// The population of the locationMap is implemented with 2D arrays for a better overview of the world.
		Location[][] tempMap = 	{{new LocPoliceStation(0,0), new LocEmpty(0,1), new LocEmpty(0,2)},
								{new LocITU(1,0), new LocCityHall(1,1), new LocEmpty(1,2)},
								{new LocEmpty(2,0), new LocBrewery("Carlsberg",2,1), new LocEmpty(2,2)}};
		locationMap = tempMap;
	}

	/**
	 *@return all locations in WorldCopenhagen.
	 */
	@Override
	public Location[][] getLocations() {
		return locationMap;
	}
}