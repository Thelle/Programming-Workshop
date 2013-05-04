package dk.itu.KF13.TheSim.Game.Model.World;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Controller.GameController;
import dk.itu.KF13.TheSim.Game.View.GameView;
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
	public WorldCopenhagen(GameController controller, GameView view) {
		// The population of the locationMap is implemented with 2D arrays for a better overview of the world.
		Location[][] tempMap = 	{{new LocPoliceStation(0,0, "Police station", controller, view), new LocEmpty(0,1,"Amager", controller, view), new LocEmpty(0,2,"Nørrebro", controller, view)},
								{new LocITU(1,0,"ITU", controller, view), new LocCityHall(1,1,"City hall", controller, view), new LocEmpty(1,2,"Vesterbro", controller, view)},
								{new LocEmpty(2,0,"Østerbro", controller, view), new LocBrewery(2,1,"Carlsberg brewery", controller, view), new LocEmpty(2,2,"Sydhavnen", controller, view)}};
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