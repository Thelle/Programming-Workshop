package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Physical.GameObject;


public interface Location {
	
	public enum Direction  {EAST, NORTH, WEST, SOUTH};
	
	Location getExits(Direction direction);
	
	String getDescription();
	
	void playerHasArrived();
	
	public void removeObject(GameObject object);
	public boolean placeObject(GameObject object);
}
