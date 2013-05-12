package dk.itu.KF13.TheSim.Game.Model.Physical.Class;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass.MasterGameObject;
import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * ObjGun is a subclass of MasterGameObject. 
 * This class has the ability to stop the game if the player chooses to pick it up.
 * @author Simon
 *
 */
public class ObjGun extends MasterGameObject {

	public ObjGun(boolean canBeTaken, GameView gameView) {
		super(canBeTaken, gameView);
	}

	public String getDescription() {
		return "a gun";
	}
	
	/**
	 * When the user tries to take the gun, s/he will die and the game will end
	 */
	@Override
	public boolean putInBackpack(Backpack backpack){
		getView().print("As you reach out for the gun, the five policemen stop beating the suspect and looks at you.\n"
				+"- Are you sure, you want to take that? The biggest policeman asks, as he draws his own gun.\n"
				+"You don't care, you are drunk, and nothing can stop you from taking the nice and shiny gun (it looks almost pink).\n"
				+"As you grab the gun, you hear five shots... GAME OVER!!");
		GameRunner.stopGame();
		return true;
	}
	/**
	 * The player can't use the gun.
	 * He will be dead before being able to use it.
	 */
	
	public int use() {
		return 0;

	}	

}
