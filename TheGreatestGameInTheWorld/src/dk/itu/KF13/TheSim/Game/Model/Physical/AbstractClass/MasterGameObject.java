package dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;

/**
 *  MasterGameObject is the abstract class for all gameobjects except the Backpack.
 *  @author Simon & Thelle
 *
 */
public abstract class MasterGameObject implements GameObject {
	private Location myLocation;
	private boolean takeable;
	
	public MasterGameObject(boolean canBeTaken){
		takeable = canBeTaken;
	}
	
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
	
	public boolean putInBackpack(Backpack backpack){
		if (takeable){
			return backpack.putInBackpack(this);
		}else{
			return false;
		}
	}	

	public boolean canBeTaken() {
		return takeable;
	}

	public Location getLocation() {
		return myLocation;
	}

	public abstract String getDescription();
	
	public abstract int use();

}
