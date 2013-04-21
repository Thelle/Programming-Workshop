package dk.itu.KF13.TheSim.Game.World;

import java.util.Scanner;

import dk.itu.KF13.TheSim.Game.Minigames.BlackJack;

public class LocBrewery extends MasterLocation {

	public LocBrewery(int xInput, int yInput) {
		super(xInput, yInput);
		
	}

	@Override
	public String getDescription() {
		return "You are standing at the Carlsberg Brewery. People are working hard.";
	}

	@Override
	public void locationSpecificAction() {
		System.out.println("Playing Black Jack is not implemented yet");
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to play Black Jack?");
		boolean doLoop = true;
		do{
			String input = sc.nextLine();
			input = input.toLowerCase();
			switch(input){
			case "yes": playBlackJack();doLoop = false;break;
			case "no": doLoop = false; break;
			default: 
				System.out.println("I don't understand. Input 'yes' or 'no'.");
				doLoop= true;
			}
		} while(doLoop);
		*/
	}
	private void playBlackJack(){
		BlackJack blackJack = new BlackJack();
	    blackJack.playBlackJack();
	}

}
