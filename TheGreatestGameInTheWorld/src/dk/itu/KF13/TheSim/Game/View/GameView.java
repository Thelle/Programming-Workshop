package dk.itu.KF13.TheSim.Game.View;
/**
 * GameView is responsible for sending the output to the correct output class
 * chosen set in the ouputDevice constant
 * @author Simon
 *
 */
public class GameView {
	private final ConsoleView printDevice;
	
	public GameView(){
		printDevice = new ConsoleView();
	}
	/**
	 * print takes the input from the model and sends it to the correct output device
	 * based on the value of the constant ouputDevice
	 * @param outputString - one single string that has to be printed
	 */
	public void print (String outputString){
		printDevice.print(outputString); // send output to console as text		
	}
	
	/**
	 * print takes the input from the model and sends it to the correct output device
	 * based on the value of the constant ouputDevice.
	 * An empty line is added underneath the written line
	 * @param outputString - one single string that has to be printed
	 */
	public void printnl (String outputString){
		printDevice.printnl(outputString); // send output to console as text
	}
	
	
}
