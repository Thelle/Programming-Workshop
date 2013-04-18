package dk.itu.KF13.TheSim.Game.Physical;

import dk.itu.KF13.TheSim.Game.World.Location;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

public class HumanPlayer implements Player {

	Location myLocation;
	
	@Override
	public Location getLocation() {
		return myLocation;
	}

	@Override
	public boolean setLocation(Location location) {
		myLocation = location;
		return true;
	}

	@Override
	public boolean move(Direction direction) {
		Location requestedLocation = myLocation.getExits(direction);
		if (requestedLocation == null){
			return false;
		}
		else{
			myLocation = requestedLocation;
			return true;
			}
	}

	@Override
	public boolean take(GameObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drop(GameObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEnergyLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeEnergyLevel(int diff) {
		// TODO Auto-generated method stub

	}

}
