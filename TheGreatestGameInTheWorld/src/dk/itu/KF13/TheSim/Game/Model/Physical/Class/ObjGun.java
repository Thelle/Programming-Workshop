package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass.MasterGameObject;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class ObjGun extends MasterGameObject {

	public ObjGun(boolean canBeTaken) {
		super(canBeTaken);
	}

	public String getDescription() {
		return "a gun";
	}
	
	/**
	 * The player can't use the gun.
	 * He will be dead before being able to use it.
	 */
	
	public int use() {
		return 0;

	}
	/**
	 * When the user tries to take the gun, he will die and the game will end
	 */
	@Override
	public boolean putInBackpack(Backpack backpack){
		GameView.print("You are dead");
		GameRunner.stopGame();
		return false;
	}	

}
