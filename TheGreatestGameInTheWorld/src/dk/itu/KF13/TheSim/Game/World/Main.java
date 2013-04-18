package dk.itu.KF13.TheSim.Game.World;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;
import dk.itu.KF13.TheSim.Game.Physical.HumanPlayer;
import dk.itu.KF13.TheSim.Game.World.Location.Direction;

import java.util.List;

public class Main {

	static WorldCopenhagen copenhagen = new WorldCopenhagen();	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Location[][] worldLocations = copenhagen.getLocations();
		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				System.out.print("x: "+x+" y: "+y+" ");
				System.out.println(worldLocations[x][y]);
			}
		}
		HumanPlayer john = new HumanPlayer();
		john.setLocation(worldLocations[1][0]);
		System.out.println("location for john is:"+john.getLocation());
		
		
		boolean LOLtest;
		LOLtest = john.move(Direction.NORTH);
		LOLtest = john.move(Direction.NORTH);
		LOLtest = john.move(Direction.NORTH);
		if (LOLtest){
			System.out.println("location for john is:"+john.getLocation());
		}
		else {
			System.out.println("location FAIL!!! LOL");
		}
		
	}
	
	public static WorldCopenhagen returnWorld(){
		return copenhagen;
	}
		
        //        BlackJack blackJack = new BlackJack();
      //          blackJack.playBlackJack();


}
