package dk.itu.KF13.TheSim.Game.World;

import java.util.ArrayList;
import java.util.List;

public class WorldCopenhagen implements World {
	List<Location> worldLocations = new ArrayList<Location>();
	
	public WorldCopenhagen() {
		worldLocations.add(new LocPoliceStation());
		worldLocations.add(new LocCityHall());
		worldLocations.add(new LocBrewery("Carslberg"));
	}

	@Override
	public List<Location> getLocations() {
		return worldLocations;
	}

}
