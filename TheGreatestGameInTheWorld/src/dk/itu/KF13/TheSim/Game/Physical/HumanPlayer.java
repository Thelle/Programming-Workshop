package dk.itu.KF13.TheSim.Game.Physical;

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
			myLocation.playerHasArrived();
			return true;
		}
	}

	@Override
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

	@Override
	public boolean drop(GameObject object) {
		myBackpack.removeFromBackpack(object);
		return true;
	}

	@Override
	public int getAlcoholLevel() {
		return alcoholLevel;
	}

	@Override
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
	}

}
