package dk.itu.KF13.TheSim.Game.Model.Physical;

import java.util.ArrayList;
import java.util.List;

/**
 * The Backpack is a container for the game objects that the player has picked up in the game.
 * @author Simon
 *
 */
public class Backpack {
	private List<GameObject> content = new ArrayList<GameObject>();
	
	/**
	 * 
	 * @return array of the objects currently in the backpack
	 */
	public List<GameObject> getContent(){
		return content;
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
	
	/**
	 * putInBackpack places an object in the backpack
	 * This method does not test if the object can be taken.
	 * @param object an object of type GameObject
	 * @return true if it was possible to place the object in the backpack
	 * @return false if it wasn't possible to place the object in the backpack
	 */
	public boolean putInBackpack(GameObject object){
		if (roomForMore()){
			content.add(object);
			return true;
		}else{
			return false;
		}
	}
	
	public void removeFromBackpack(GameObject object){
		content.remove(object);
	}
	
	/**
	 * numberOfBeersInBackpack loops through the backpack and returns
	 * the number of beers
	 * @return then number of beers in the backpack
	 */
	public int numberOfSpecificItemsInBackpack(String descriptionOfItem){
		int numberOfBeers = 0;
		for(int i = 0; i < content.size();i++){
			String description = content.get(i).getDescription();
			if(description.equalsIgnoreCase(descriptionOfItem)){
				numberOfBeers++;
			}
		}
		return numberOfBeers;
	}
}
