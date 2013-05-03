package dk.itu.KF13.TheSim.Game.Physical;

import java.util.List;

import dk.itu.KF13.TheSim.Game.World.Location.Direction;

public interface Player extends Localizable {
	/** 
	 * Moves the player to a new location
	 * @param direction direction to be moved too
	 * @return true if new location can be set as requested, false
	otherwise
	 */
	boolean move(Direction direction);
	
	/** Player can take an object
	 * 
	 * @param object - the object to take
	 * @return true if object has been successfully taken, false
	otherwise
	 */
	boolean take(GameObject object);
	boolean drop(GameObject object);
	List<GameObject> returnContentOfBackpack();
	
	/**
	 * Returns the energy level of the player
	 * @return
	 */
	int getAlcoholLevel();
	
	/**
	 * Changes the energy level of the player as specified by the diff
	 * @param diff change in energy level
	 */
	void changeAlcoholLevel(int diff);
	
	void removeFromBackPack(GameObject object);
}
