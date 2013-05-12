package dk.itu.KF13.TheSim.Game.Model.Physical.Interface;

import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location.Direction;

public interface Player extends Localizable {
	/**
	 * Changes the energy level of the player as specified by the diff
	 * @param diff - change in energy level
	 */
	void changeAlcoholLevel(int diff);
	
	/**
	 * @return Returns the energy level of the player
	 */
	int getAlcoholLevel();
	
	/**
	 * Looks in the players backpack for items with the specified description by
	 * calling the method numberOfSpecificItemsInBackpack from the player's backpack
	 * @param descriptionOfItem - the description of the item as given when the item is created
	 * @return then number of items with the specified description
	 */
	int lookForSpecificItem(String descriptionOfItem);
	
	/**
	 * lookInBackpack prints out a list of all the items in the player's backpack
	 */
	void lookInBackpack();
	
	/** 
	 * Moves the player to a new location
	 * @param direction - direction to be moved too
	 * @return true if new location can be set as requested, false
	otherwise
	 */
	boolean move(Direction direction);
	
	/**
	 * Removes the given object from the player's backpack by calling the method
	 * removeFromBackpack from the class {@link Backpack}.
	 * The item is deleted from the game when this method is used
	 * @param object - the object to be removed
	 */
	void removeFromBackPack(GameObject object);
	
	/**
	 * @return Returns a list with all the objects in the players backpack
	 */
	List<GameObject> returnContentOfBackpack();
	
	/** 
	 * The take method places the given object in the player's backpack
	 * @param object - the object to take
	 * @return True if object has been successfully placed in the backpack.
	 * False otherwise
	 */
	boolean takeObject(GameObject object);
	
	/**
	 * Loops through the items at the player location and compare the player's
	 * input with the descriptions of the items at the location.
	 * @param input - the written user input
	 */
	boolean takeObject(String input);
	
	/**
	 * useObject calls the use method of the given object, and changes the
	 * players alcohol level with the amount returned from the GameObject.
	 * The object is removed from the player's backpack after it has been used.
	 * @param object - the object the player wants to use
	 */
	void useObject(GameObject object);

	/**
	 * useObject loops through the objects in the player's backpack and
	 * compares the description of the item with the object that
	 * the user has written.
	 * The comparison is done by removing "a" from the description of the 
	 * item and then compare with the input by adding "use" before the description.
	 * If the item is not in the backpack, the item is not used and the user is warned
	 * that the item is not in the backpack
	 * @param input - the string input that the user has written. Starts with "use".
	 */
	void useObject(String input);
}
