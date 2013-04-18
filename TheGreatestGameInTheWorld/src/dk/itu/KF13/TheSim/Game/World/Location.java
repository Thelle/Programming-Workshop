package dk.itu.KF13.TheSim.Game.World;


public interface Location {
	
	public enum Direction  {EAST, NORTH, WEST, SOUTH};
	
	Location getExits(Direction direction);
	
	String getDescription();
	

}
