package dk.itu.KF13.TheSim.Game.Controller;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.View.GameView;

public interface IGameController {

	public abstract String getStringInput();

	public abstract boolean getCommand();

	public abstract void setPlayer(HumanPlayer player);

	public abstract void setGameRunner(GameRunner gameRunner);
	
	public abstract void setGameView(GameView gameView);
	
	public abstract void printHelpText();

}
