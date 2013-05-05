package dk.itu.KF13.TheSim.Game.Model.Minigames.BlackJack;

import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.View.GameView;
/**
 * The BlackJackController class is used to take input from the user
 * which are used during the Black Jack game.
 * @author Simon
 *
 */
public class BlackJackController {
	/**
	 * getYesNo reads the user input and makes sure it's a y or an n.
	 * If the user enters anything else, s/he is asked to redo it.
	 * @return 'y' or 'n'
	 */
	public String getYesNo(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean doLoop = true;
		String input;
		do{
			input = sc.next();
			if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n") ){
				doLoop = false;
			} else {
				GameView.printnl("I did not understand that. Write Y or N");
			}
		}while(doLoop);
		return input;
	}
	
	
	

}
