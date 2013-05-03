package dk.itu.KF13.TheSim.Game.Physical;

import java.util.List;

import dk.itu.KF13.TheSim.Game.World.Location;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

public class HumanPlayer implements Player {

	Location myLocation;
	Backpack myBackpack;
	int alcoholLevel;
	
	public HumanPlayer(){
		myBackpack = new Backpack();
		alcoholLevel = 4;
	}
	
	public Location getLocation() {
		return myLocation;
	}

	public boolean setLocation(Location location) {
		myLocation = location;
		return true;
	}

	public boolean move(Direction direction) {
		Location requestedLocation = myLocation.getExits(direction);
		if (requestedLocation == null){
			return false;
		}
		else{
			myLocation = requestedLocation;
			this.changeAlcoholLevel(-1);
			myLocation.playerHasArrived();
			return true;
		}
	}

	public boolean take(GameObject object) {
		boolean status;
		status = object.putInBackpack(myBackpack);
		if (status){
			myLocation.removeObject(object);
			return true;
		}else{
			return false;
		}		
	}

	public boolean drop(GameObject object) {
		myBackpack.removeFromBackpack(object);
		return true;
	}

	public int getAlcoholLevel() {
		return alcoholLevel;
	}

	public void changeAlcoholLevel(int diff) {
		alcoholLevel = alcoholLevel + diff;
	}
	
	/**
	 * use calls the use method of the object, and changes the
	 * players alcohol level with the amount returned from the GameObject.
	 * @param object
	 */
	public void use(GameObject object){
		//The returned value is subtracted from the alcohol level because
		//the use method returns the price of the action
		int diff = -object.use();
		this.changeAlcoholLevel(diff);
		myBackpack.removeFromBackpack(object);
	}

	public List<GameObject> returnContentOfBackpack() {
		return myBackpack.getContent();
	}

	public void removeFromBackPack(GameObject object) {
		myBackpack.removeFromBackpack(object);
	}
}
