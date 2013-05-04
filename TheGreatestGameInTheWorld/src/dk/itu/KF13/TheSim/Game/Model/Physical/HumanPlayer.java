package dk.itu.KF13.TheSim.Game.Model.Physical;

import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.World.Location;
import dk.itu.KF13.TheSim.Game.Model.World.Location.Direction;
import dk.itu.KF13.TheSim.Game.View.GameView;

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
	
	/**
	 * Adds beers to the player backpack
	 * @param numberOfBeers - number of beers to be added
	 */
	public void addBeersToBackpack(int numberOfBeers){
		for ( int i = 0; i < numberOfBeers; i++){
			ObjBottle beer = new ObjBottle(true, BottleType.MASTERBREW);
			beer.putInBackpack(myBackpack);
		}
	}
	/**
	 * removeBeersFromBackpack loops through the contents of the backpack 
	 * and removes the numbers of beers specified in numberOfBeers
	 * @param numberOfBeers - the number of beers to be removed
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
	
	/**
	 * lookInBackpack prints out a list of all the items in the player's backpack
	 */
	public void lookInBackpack(){
		List<GameObject> objectsInBackpack = this.returnContentOfBackpack();
		GameView.print("Objects in backpack:");
		for(int i = 0; i < objectsInBackpack.size();i++){
			GameView.print(objectsInBackpack.get(i).getDescription());
		}
	}
	/**
	 * Looks in the players backpack for items with the specified description by
	 * calling the method numberOfSpecificItemsInBackpack from the player's backpack
	 * @param descriptionOfItem - the description of the item as given when the item is created
	 * @return then number of items with the specified description
	 */
	public int lookForSpecificItem(String descriptionOfItem){
		return myBackpack.numberOfSpecificItemsInBackpack(descriptionOfItem);
	}

	/**
	 * useItem loops through the objects in the player's backpack and
	 * compares the description of the item with the object that
	 * the user has written.
	 * The comparison is done by removing "a" from the description of the 
	 * item and then compare with the input by adding "use" before the description.
	 * If the item is not in the backpack, the item is not used and the user is warned
	 * that the item is not in the backpack
	 * @param input - the string input that the user has written. Starts with "use".
	 */
	public void useItem(String input){
		List<GameObject> objectsInBackpack = returnContentOfBackpack();
		for(int i = 0; i < objectsInBackpack.size();i++){
			String objectName = objectsInBackpack.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			if(input.equalsIgnoreCase("use "+objectName)){
				use(objectsInBackpack.get(i));
				GameView.print("You used "+objectName);
				return;
			}
		}
		GameView.print("No such item in backpack");
	}

	/**
	 * Loops through the items at the player location and compare the player's
	 * input with the descriptions of the items at the location.
	 * @param input - the written user input
	 */
	public void takeItem(String input){
		Location playerLocation = getLocation();
		List<GameObject> objectsAtLocation = playerLocation.getObjects();
		for(int i = 0; i < objectsAtLocation.size();i++){
			String objectName = objectsAtLocation.get(i).getDescription();
			objectName = objectName.replaceFirst("a ", "");
			objectName = "take "+objectName;
			if(input.equalsIgnoreCase(objectName)){
				boolean boStatus = take(objectsAtLocation.get(i));
				if(boStatus){
					GameView.print("You have taken the object");
				} else{
					GameView.print("There was no room in your backpack or you can't take the item");
				}
				return;
			}
		}
		GameView.print("No such item");
	}
}
