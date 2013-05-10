package dk.itu.KF13.TheSim.Game.Model.World.Class;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.World;
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
	public WorldCopenhagen(GameView gameView) {
		// The population of the locationMap is implemented with 2D arrays for a better overview of the world.
		Location[][] tempMap = 	{{new LocPoliceStation(0,0, "Police station", gameView), new LocEmpty(0,1,"Amager", gameView), new LocEmpty(0,2,"Nørrebro", gameView)},
								{new LocITU(1,0,"ITU", gameView), new LocCityHall(1,1,"City hall", gameView), new LocEmpty(1,2,"Vesterbro", gameView)},
								{new LocEmpty(2,0,"Østerbro", gameView), new LocBrewery(2,1,"Carlsberg brewery", gameView), new LocEmpty(2,2,"Sydhavnen", gameView)}};
		locationMap = tempMap;
	}

	public Location[][] getLocations() {
		return locationMap;
	}
}