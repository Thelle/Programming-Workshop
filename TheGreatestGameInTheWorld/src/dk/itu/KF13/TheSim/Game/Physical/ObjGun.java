package dk.itu.KF13.TheSim.Game.Physical;

import dk.itu.KF13.TheSim.Game.Controller.GameController;

public class ObjGun extends MasterGameObject {

	public ObjGun(boolean canBeTaken) {
		super(canBeTaken);
	}

	@Override
	public String getDescription() {
		return "This is a gun. Don't touch it";
	}
	
	/**
	 * use is not used with the gun. 
	 * The player will die before he can use it.
	 * @return 
	 */
	@Override
	public int use() {
		return 0;

	}
	
	public boolean putInBackpack(Backpack backpack){
		System.out.println("You are dead");
		GameController.stopGame();
		return false;
	}	

}
