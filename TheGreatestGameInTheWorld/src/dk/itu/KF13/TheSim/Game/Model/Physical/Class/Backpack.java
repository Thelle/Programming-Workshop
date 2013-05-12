package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import java.util.ArrayList;
import java.util.List;

import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;

/**
 * The Backpack is a container for the game objects that the player has picked up in the game.
 * @author Simon & Thelle
 *
 */
public class Backpack {
	private List<GameObject> content = new ArrayList<GameObject>();
	
	/** 
	 * @return Returns a list of the objects currently in the backpack
	 */
	public List<GameObject> getContent(){
		return content;
	}
	
	/**
	 * numberOfBeersInBackpack loops through the backpack and returns
	 * the number of beers
	 * @return then number of beers in the backpack
	 */
	public int numberOfSpecificItemsInBackpack(String descriptionOfItem){
		int numberOfItems = 0;
		for(int i = 0; i < content.size();i++){
			String description = content.get(i).getDescription();
			if(description.equalsIgnoreCase(descriptionOfItem)){
				numberOfItems++;
			}
		}
		return numberOfItems;
	}
	
	/**
	 * putInBackpack places an object in the backpack
	 * This method does not test if the object can be taken.
	 * @param object - an object of type GameObject
	 * @return True if it was possible to place the object in the backpack.
	 * False if it wasn't possible to place the object in the backpack
	 */
	public boolean putInBackpack(GameObject object){
		if (roomForMore()){
			content.add(object);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * removeFromBackpack removes the given object from the backpack by using 
	 * the ArrayList.remove() method from the class ArrayList.
	 * @param object - the object to be removed
	 */
	public void removeFromBackpack(GameObject object){
		content.remove(object);
	}
	
	/**
	 * roomForMore tests if there is room for more objects in the backpack
	 * @return true if there is room for more objects
	 * @return false if there is no room for more objects
	 */
	public boolean roomForMore(){
		if (content.size() < 30){
			return true;
		}else{
			return false;
		}
	}
}
