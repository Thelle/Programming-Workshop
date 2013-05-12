package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass.MasterGameObject;
/**
 * ObjNonMovable is the class for all objects in the game which are just used as decorations.
 * @author Simon
 *
 */
public class ObjNonMovable extends MasterGameObject {
	
	public enum ObjectType {STATUE, BARREL}; 
	private ObjectType typeOfObject;
	
	public ObjNonMovable(boolean canBeTaken, ObjectType objectType) {
		super(canBeTaken);
		typeOfObject = objectType;
	}

	@Override
	public String getDescription() {
		switch(typeOfObject){
		case STATUE: return "a statue";
		case BARREL: return "a barrel";
		default: return null;
		}
	}
	
	/**
	 * An object of type ObjStatic can't be used 
	 * @return The cost of trying to use the object. 
	 */
	@Override
	public int use() {
		getView().print("You can't use me!");
		return 2;
	}

}
