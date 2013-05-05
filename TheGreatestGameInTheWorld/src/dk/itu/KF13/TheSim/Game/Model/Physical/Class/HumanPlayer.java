package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.Player;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location.Direction;
import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * HumanPlayer is responsible for all actions the human player can do
 * @author Simon & Thelle
 *
 */
public class HumanPlayer implements Player {

	private Location myLocation;
	private Backpack myBackpack;
	private int alcoholLevel;
	
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

	public boolean takeObject(GameObject object) {
		boolean status;
		status = object.putInBackpack(myBackpack);
		if (status){
			myLocation.removeObject(object);
			GameView.print("You have taken the object");
			return true;
		}else{
			GameView.print("There was no room in your backpack or you can't take the item");
			return false;
		}		
	}
	
	public void takeObject(String input){
		Location playerLocation = getLocation();
		List<GameObject> objectsAtLocation = playerLocation.getObjects();
		for(int i = 0; i < objectsAtLocation.size();i++){
			String objectName = objectsAtLocation.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			objectName = "take "+ objectName;
			if(input.equalsIgnoreCase(objectName)){
				takeObject(objectsAtLocation.get(i));				
				return;
			} else {
				GameView.print("No such item at this location");
			}
		}		
	}

	public int getAlcoholLevel() {
		return alcoholLevel;
	}

	public void changeAlcoholLevel(int diff) {
		alcoholLevel = alcoholLevel + diff;
	}
	
	public void useObject(GameObject object){
		//The returned value is subtracted from the alcohol level because
		//the use method returns the price of the action
		int diff = -object.use();
		this.changeAlcoholLevel(diff);
		myBackpack.removeFromBackpack(object);
		GameView.print("You used "+ object.getDescription());
	}
	
	public void useObject(String input){
		List<GameObject> objectsInBackpack = returnContentOfBackpack();
		for(int i = 0; i < objectsInBackpack.size();i++){
			String objectName = objectsInBackpack.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			if(input.equalsIgnoreCase("use " + objectName)){
				useObject(objectsInBackpack.get(i));
				return;
			}
		}
		GameView.print("No such item in backpack");
	}

	public List<GameObject> returnContentOfBackpack() {
		return myBackpack.getContent();
	}

	public void removeFromBackPack(GameObject object) {
		myBackpack.removeFromBackpack(object);
	}
	
	/**
	 * addBeersToBackpack adds the given number of beers to the player's backpack.
	 * This method does not take the beers from the location and can be called whenever new
	 * beers should be added.
	 * @param numberOfBeers - the number of beers to be added
	 */
	public void addBeersToBackpack(int numberOfBeers){
		for ( int i = 0; i < numberOfBeers; i++){
			ObjBottle beer = new ObjBottle(true, BottleType.MASTERBREW);
			beer.putInBackpack(myBackpack);
		}
	}
	
	/**
	 * removeBeersFromBackpack removes the given number of beers from the player's backpack.
	 * @param numberOfBeers - number of beers to be removed.
	 */
	public void removeBeersFromBackpack(int numberOfBeers){
		List<GameObject> objectsInBackpack = this.returnContentOfBackpack();
		int beersRemoved = 0;
		for(int i = 0; i < objectsInBackpack.size() && beersRemoved < numberOfBeers;i++){
			String description = objectsInBackpack.get(i).getDescription();
			if(description.equalsIgnoreCase("a masterbrew")){
				removeFromBackPack(objectsInBackpack.get(i));
				beersRemoved++;
				i--; //Subtracting one from i, because the size of the list 
					//is one less after deleting an element
			}
		}
	}
	
	public void lookInBackpack(){
		List<GameObject> objectsInBackpack = this.returnContentOfBackpack();
		GameView.print("Objects in backpack:");
		for(int i = 0; i < objectsInBackpack.size();i++){
			GameView.print(objectsInBackpack.get(i).getDescription());
		}
	}
	
	public int lookForSpecificItem(String descriptionOfItem){
		return myBackpack.numberOfSpecificItemsInBackpack(descriptionOfItem);
	}	
}
