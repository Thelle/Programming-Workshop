package dk.itu.KF13.TheSim.Game.Model.World.Class;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjNonMovable.ObjectType;
import dk.itu.KF13.TheSim.Game.Model.World.AbstractClass.MasterLocation;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class LocBrewery extends MasterLocation {

	public LocBrewery(int xInput, int yInput, String name, GameView gameView, WorldCopenhagen copenhagen) {
		super(xInput, yInput, name, gameView, copenhagen);
		getObjectsAtLocation().add(new ObjBottle(true, BottleType.MASTERBREW));
		getObjectsAtLocation().add(new ObjNonMovable(false, ObjectType.BARREL));
		
	}

	public String getDescription() {
		String returnString =  "You are standing at the Carlsberg Brewery. " +
				"People are working hard.";
		returnString += "\n" + getObjectDescriptions();
		return returnString;
	}

	public void locationSpecificAction() {
			getView().print("As you look around the brewery, you see a big man with a big beard comming towards you. \n" 
						    + "- You look lost my young friend. What brings you to my brewery? \n" 
							+ "Before you have time to answer he begins speaking again. \n"
						    + "- Don't bother telling me. It looks like you are in need of some nice cold bottles of beer. Am I right? \n"
							+ "You smile and nod. It seems like getting beer is going to be quite easy. \n"
						    + "- I thought so much when I saw you. But you know. My beer is not free. You have to fight for it.\n" 
							+ "Oh no! Pictures of you and the big bearded man in a mud fight begins to appear. That's not a pretty sight.\n"
						    + "- Why do you look so scared? It's not like we are going to fight in a pool of mud. Eventhough that would be quite funny. \n"
							+ "  You are just going to \"fight\" me in a game of blackjack. If you want to play, just say so.\n"
						    + "(give the command 'play blackjack' to begin the game)\n");
	}
}
