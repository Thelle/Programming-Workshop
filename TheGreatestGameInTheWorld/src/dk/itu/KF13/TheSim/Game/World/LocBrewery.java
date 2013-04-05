package dk.itu.KF13.TheSim.Game.World;

import java.util.List;

public class LocBrewery implements Location {
	String breweryName;
	int xPos, yPos;
	
	public LocBrewery (String name, int xInput, int yInput){
		breweryName = name;
		xPos = xInput;
		yPos = yInput;
	}

	
	@Override
	public List<Location> getExits(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		return "You are standing at the new " + breweryName +" Brewery. People are working hard.";
	}

}
