package dk.itu.KF13.TheSim.Game.Model.World.Class;

import java.util.Random;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;
import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * LocEmpty is the class used for all the empty locations in the game world.
 * When the player arrives at this location, there is a chance that either a beer or a bottle of water appears.
 * @author Simon
 *
 */
public class LocEmpty extends MasterLocation {

	public LocEmpty(int xInput, int yInput, String name, GameView gameView, WorldCopenhagen copenhagen) {
		super(xInput, yInput, name, gameView, copenhagen);
	}

	@Override
	public String getDescription() {
		String returnString =  "Here is empty.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}
	
	/** 
	 * When a player arrives there is a chance that a bottle is placed on the location.	 * 
	 */
	@Override
	public void locationSpecificAction() {
		ObjBottle obj = placeRandomBottle();
		if ( obj != null){
			this.placeObject(obj);
			getView().printnl("You suddently see " + obj.getDescription());
		}
		
	}
	
	/**
	 * createBottle creates a bottle based on the random number it is given
	 * @param randomNumber - a number between 0 and 14
	 * @return - a bottle type or null
	 */
	private ObjBottle createBottle(int randomNumber){
		ObjBottle obj;
		if (randomNumber < 5){
			obj = new ObjBottle(true, BottleType.MASTERBREW);
		}else if (randomNumber >= 5 && randomNumber < 10){
			obj = new ObjBottle(true, BottleType.WATER);
		}else{
			obj = null;
		}
		return obj;
	}
	
	/**
	 * placeRandomBottle places a bottle of water, a bottle of beer or nothing.
	 * There is 33% chance for each object type to appear.
	 * @return Returns an bottle object or null.
	 */
	private ObjBottle placeRandomBottle(){
		Random rand = new Random();
		int randomNumber = rand.nextInt(15); //returns a number between 0 and 14
		ObjBottle obj = createBottle(randomNumber);
		return obj;
	}

}
