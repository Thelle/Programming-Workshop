package dk.itu.KF13.TheSim.Game.Controller;

import dk.itu.KF13.TheSim.Game.Model.GameRunner;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;

public interface IGameController {

	public abstract String getStringInput();

	public abstract boolean getCommand();

	public abstract void setPlayer(HumanPlayer player);

	public abstract void setGameRunner(GameRunner gameRunner);

}
