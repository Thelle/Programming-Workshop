package dk.itu.KF13.TheSim.Game.Model.Physical.AbstractClass;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.World.Interface.Location;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 *  MasterGameObject is the abstract class for all game objects except the Backpack.
 *  @author Simon & Thelle
 *
 */
public abstract class MasterGameObject implements GameObject {
	private Location myLocation;
	private boolean takeable;
	private GameView view;
	
	public MasterGameObject(boolean canBeTaken, GameView gameView){
		takeable = canBeTaken;
		setView(gameView);
	}
	
	public MasterGameObject(boolean canBeTaken){
		takeable = canBeTaken;
	}
	
	public boolean setLocation(Location location) {
		boolean status;
		status = location.placeObject(this);
		if (status){
			this.myLocation = location;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean putInBackpack(Backpack backpack){
		if (takeable){
			return backpack.putInBackpack(this);
		}else{
			return false;
		}
	}	

	public boolean canBeTaken() {
		return takeable;
	}

	public Location getLocation() {
		return myLocation;
	}

	public abstract String getDescription();
	
	public abstract int use();

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

}
