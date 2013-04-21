package dk.itu.KF13.TheSim.Game.Physical;
/**
 * ObjBottle is the class of the bottles in the world.
 * Two bottletypes are possible: Water and Masterbrew
 * @author Simon
 *
 */
public class ObjBottle extends MasterGameObject {
	public enum BottleType {MASTERBREW, WATER}; 
	private BottleType typeOfBottle;
	
	public ObjBottle(boolean canBeTaken, BottleType bottleType) {
		super(canBeTaken);
		typeOfBottle = bottleType;
	}

	/**
	 * getDescription returns the description of the bottle.
	 * The description is dependent on the type of bottle
	 * @return returns the description of the bottle
	 */
	public String getDescription() {
		switch(typeOfBottle){
		case MASTERBREW: return "This is a masterbrew";
		case WATER: return "This is water";	
		default:return null;
		}
	}

	/**
	 * use changes the alcohol level of the player according to the type of bottle
	 * type masterbrew: alcohol is increased
	 * type water: alcohol is decreased
	 */
	public int use() {
		switch(typeOfBottle){
		case MASTERBREW: return -5;
		case WATER: return 5;
		default: return 0;
		}
	}

}
