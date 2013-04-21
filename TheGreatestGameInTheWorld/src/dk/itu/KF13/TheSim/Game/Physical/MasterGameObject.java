package dk.itu.KF13.TheSim.Game.Physical;

import dk.itu.KF13.TheSim.Game.World.Location;

public abstract class MasterGameObject implements GameObject {
	private Location myLocation;
	private boolean takeable;
	
	public MasterGameObject(boolean canBeTaken){
		takeable = canBeTaken;
	}
	
	@Override
	public boolean setLocation(Location location) {
		boolean status;
		status = location.placeObject(this);
		if (status){
			this.myLocation = location;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * putInBackpack places an object in a backpack if there is room and the object can be taken
	 * @param backpack the backpack where the item is placed
	 * @return true if the object is placed in the backpack
	 * @return false if the object is not placed in the backpack
	 */
	public boolean putInBackpack(Backpack backpack){
		if (takeable){
			return backpack.putInBackpack(this);
		}else{
			return false;
		}
	}	
	
	@Override
	public boolean canBeTaken() {
		return takeable;
	}

	@Override
	public Location getLocation() {
		return myLocation;
	}

	@Override
	public abstract String getDescription();
	
	/**
	 * 
	 * @return returns the alcohol price to use the item
	 */
	public abstract int use();

}
