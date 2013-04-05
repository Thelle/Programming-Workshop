package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;
import java.util.List;

public class Main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorldCopenhagen copenhagen = new WorldCopenhagen();
		
		Location[][] worldLocations = copenhagen.getLocations();
		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				System.out.print("x: "+x+" y: "+y+" ");
				System.out.println(worldLocations[x][y]);
			}
		}
     }
		
        //        BlackJack blackJack = new BlackJack();
      //          blackJack.playBlackJack();


}
