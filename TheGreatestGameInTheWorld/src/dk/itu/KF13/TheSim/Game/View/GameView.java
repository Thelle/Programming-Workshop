package dk.itu.KF13.TheSim.Game.View;
/**
 * GameView is responsible for sending the output to the correct output class
 * chosen set in the ouputDevice constant
 * @author Simon
 *
 */
public class GameView {
	private final static int outputDevice = 1;
	
	/**
	 * print takes the input from the model and sends it to the correct output device
	 * based on the value of the constant ouputDevice
	 * @param outputString - one single string that has to be printed
	 */
	public static void print (String outputString){
		switch (outputDevice){
		case 1: ConsoleView.print(outputString); break; // send output to console as text
		case 2: break; // send output to JFrame as text. Not implemented
		}
	}
	
	
}
