package dk.itu.KF13.TheSim.Game.Model.Physical;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class ObjGun extends MasterGameObject {

	public ObjGun(boolean canBeTaken) {
		super(canBeTaken);
	}

	@Override
	public String getDescription() {
		return "a gun";
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
		GameView.print("You are dead");
		GameRunner.stopGame();
		return false;
	}	

}
