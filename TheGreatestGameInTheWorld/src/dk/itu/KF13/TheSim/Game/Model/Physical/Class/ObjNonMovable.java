package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass.MasterGameObject;

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
	 * @return 
	 */
	@Override
	public int use() {
		getView().print("You can't use me!");
		return 2;
	}

}
