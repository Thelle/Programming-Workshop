package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public class Main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorldCopenhagen copenhagen = new WorldCopenhagen();
		
		List<Location> worldLocations = copenhagen.getLocations();
		
		for (Location locationToBePrinted : worldLocations) {
			System.out.println(locationToBePrinted.getDescription());
			
		}
	}

}
