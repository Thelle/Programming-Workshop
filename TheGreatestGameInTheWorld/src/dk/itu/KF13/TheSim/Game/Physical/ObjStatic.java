package dk.itu.KF13.TheSim.Game.Physical;

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
		case STATUE: return "This is a statue";
		case BARREL: return "This is a barrel";
		default: return null;
		}
	}
	
	/**
	 * An object of type ObjStatic can't be used right now
	 * @return 
	 */
	@Override
	public int use() {
		System.out.println("You can't use me!");
		return 2;
	}

}
