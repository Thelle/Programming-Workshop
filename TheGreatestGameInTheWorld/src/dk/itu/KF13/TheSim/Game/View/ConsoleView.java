package dk.itu.KF13.TheSim.Game.View;
/**
 * ConsoleView is ersponsoble for printing text to the console
 * @author Simon
 *
 */
public class ConsoleView {
	/**
	 * Prints a single string to the console using system.out.println().
	 * @param outputString - the string to be printed
	 */
	public static void print (String outputString){
		System.out.println(outputString);
	}
	
	/**
	 * Prints the input string to the console and adds an empty line underneath
	 * @param outputString - the string to be printed
	 */
	public static void printnl (String outputString){
		System.out.println(outputString);
		System.out.println("");
	}

}
