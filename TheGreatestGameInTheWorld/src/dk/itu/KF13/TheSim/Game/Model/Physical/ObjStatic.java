package dk.itu.KF13.TheSim.Game.Model.Physical;

import dk.itu.KF13.TheSim.Game.View.GameView;

public class ObjStatic extends MasterGameObject {
	
	public enum ObjectType {STATUE, BARREL}; 
	private ObjectType typeOfObject;
	
	public ObjStatic(boolean canBeTaken, ObjectType objectType) {
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
	 * An object of type ObjStatic can't be used right now
	 * @return 
	 */
	@Override
	public int use() {
		view.print("You can't use me!");
		return 2;
	}

}
