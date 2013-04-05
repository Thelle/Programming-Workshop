package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public interface Location {
	
	public enum Direction  {EAST, NORTH, WEST, SOUTH, UP, DOWN };
	
	List<Location> getExits(Direction direction);
	
	String getDescription();
	

}
