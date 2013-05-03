package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

import dk.itu.KF13.TheSim.Game.Physical.GameObject;


public interface Location {
	
	public enum Direction  {EAST, NORTH, WEST, SOUTH};
	
	Location getExits(Direction direction);
	
	String getDescription();
	String getName();
	String getObjectDescriptions();
	List<GameObject> getObjects();
	
	void playerHasArrived();
	
	public void removeObject(GameObject object);
	public boolean placeObject(GameObject object);

}
