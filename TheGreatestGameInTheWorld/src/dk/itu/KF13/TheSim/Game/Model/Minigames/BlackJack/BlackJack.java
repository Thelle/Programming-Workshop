package dk.itu.KF13.TheSim.Game.Model.Minigames.BlackJack;
import java.util.Random;

import dk.itu.KF13.TheSim.Game.Controller.BlackJackController;
import dk.itu.KF13.TheSim.Game.Controller.IGameController;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 * BlackJack is responsible for running the game Black jack
 * @author Simon & Thelle
 */
public class BlackJack {
    private String playedCards ="";
    private String playedCardsPlayer ="";
    private String playedCardsDealer ="";
    private int pointsPlayed;
    private int playerPoints;
    private int dealerPoints;
    private int numberOfAcesWorth11 = 0;
    
    private BlackJackController controller;
    private GameView view;
    
    public BlackJack(IGameController gameController, GameView gameView){
    	controller = new BlackJackController(gameController, gameView);
    	view = gameView;
    }
    
    /**
     * playBlackJack runs the Black Jack game.
     */
    public void playBlackJack(HumanPlayer player){
        boolean playerDidWin;
    	
        //The rules of the game are printed
        printRules();
        
    	// Human round is played
    	boolean continueGame = playPlayerRound();
    	
    	//Info about the human round is saved
    	saveInfoAboutPlayersRound();
    	
    	//Global variables are reset
    	resetGlobalVariables();
    	
    	if (continueGame){
    		//Dealer round is played
    		playRound("Dealer");
    		
    		//Info about the dealer round is saved
    		saveInfoAboutDealerRound();
    		
    		//The winner is found
    		playerDidWin = playerDidWin();
    	}else{
    		playerDidWin = false;
    	}
    	
    	//Beers are added / subtracted
    	if (playerDidWin){
    		view.printnl("You win two beers");
    		player.addBeersToBackpack(2);
    	} else {
    		view.printnl("You loose two beers");
    		player.removeBeersFromBackpack(2);
    	}        
    }
    
    /**
     * addPoints adds points according to the value of the drawn card. 
     * @param drawnCard - The card that has been drawn
     */
    private void addPoints(String drawnCard){
        String numbersOnly = drawnCard.replaceAll("[()]","");
        numbersOnly = numbersOnly.replaceFirst("[a-zA-Z]* ","");
        int cardNumber = Integer.parseInt(numbersOnly);
        int cardPoints = 0;
        if (cardNumber >1 && cardNumber <10) {
            cardPoints = cardNumber;
        }
        else if (cardNumber >9){
            cardPoints = 10;
        }
        else if (cardNumber == 1){
            cardPoints = 11;
            numberOfAcesWorth11++;
        }
        pointsPlayed = pointsPlayed + cardPoints;
    }
    
    /**
     * changeSuitNumberIntoText changes the suit number of the card 
     * into text describing the suit
     * @param suitNumber 
     * @return returns the name of the suit.
     */
    private String changeSuitNumberIntoText(int suitNumber){
    	switch (suitNumber){
    	case 1: return "Hearts";
    	case 2: return "Spades";
    	case 3: return "Diamonds";
    	case 4: return "Clubs";
    	default: return null;
    	}
    }
    
    /**
     * checkPoints checks the points that the player has, 
     * and returns a statement based on the player type.
     * Before the number of points is calculated, the values of aces are changed.
     * @param playerType - the type of player. Accepts 'Dealer' and 'Player'
     * @return Returns
     * <li> "Less than max" if the player has less than number where s/he should stop
     * <li> "More than 21" if the player has more than 21 points
     * <li> "Exactly 21" if the player has exactly 21 points
     * <li> "Between max and 21" if player has between the number where s/he should stop and 21
     */
    private String checkPoints (String playerType ){
        int maxNumber = 0;
        if (playerType.equals("Dealer")){
            maxNumber = 17; //Dealer does only continues if it has less than 17 points.
        }
        if (playerType.equals("Player")){
            maxNumber = 21; //Player may continue until s/he has more than 21 points
        }
        
        //Changes the amount of points if the player has an ace worth 11
        if (pointsPlayed > 21 && numberOfAcesWorth11 > 0){
            pointsPlayed = pointsPlayed-10;
            numberOfAcesWorth11--;
        }
        if (pointsPlayed > 21){
            return "More than 21";
        }
        else if (pointsPlayed == 21){
            return "Exactly 21";
        }
        else if (pointsPlayed < maxNumber){
            return "Less than max";
        }
        else{
            //pointsPlayed is greater than or equal to maxNumber, but less than 21
        	return "Between max and 21";
        }
    }
    
    /**
	 * doesPlayerWantToContinue asks the player if he wants to continue
	 * @param drawnCard - the card that has just been drawn.
	 * @return True if the player wants to continue. False otherwise.
	 */
	private boolean doesPlayerWantToContinue(String drawnCard) {
		 view.print("You have drawn this card: " + 
	            drawnCard + "\nYou currently have " + pointsPlayed + " points" + 
	            "\nDo you want to continue (Y/N)?");
		 String answer = controller.getYesNo();
		 switch (answer){
		 case "y": view.print(""); return true;
		 case "n": view.print(""); return false;
		 default: return false; //the method getYesNo only returns y or n
		 }
		
	}
    
    /**
     * drawCard draws a random card and returns the suit and value of that.
     * It does not check if the card has been drawn before.
     * @return Returns a string describing the drawn card.
     */
    private String drawCard(){
        Random rand = new Random();
        int cardSuitNumber = rand.nextInt(3) + 1; //Adding 1 to match values in a card deck
        int cardNumber = rand.nextInt(13) + 1;
        String suitName = changeSuitNumberIntoText(cardSuitNumber);
        return "(" + suitName + " " + cardNumber + ")";
    }
    
    /**
     * isAlreadyDrawn tests if the card has already been drawn
     * @param drawnCard
     * @return True if the card has been drawn. False otherwise.
     */
    private boolean isAlreadyDrawn (String drawnCard) {
        int subStringIndex = playedCards.lastIndexOf(drawnCard);
        if (subStringIndex == -1){ //Card is not already drawn
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * playerDidWin tests if the player won or lost
     * @return true if the player did win. False if not
     */
    private boolean playerDidWin(){
    	if(playerPoints > dealerPoints || dealerPoints > 21){
        	view.printnl("Congratulations. You won with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	return true;
    	}
        else {
        	view.printnl("Loser! You lost with with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	return false;
        }
    }
   
	/**
	 * playOneTurn draws a card for the player
	 * @return Returns the card drawn
	 */
    private String playOneTurn(){
        String drawnCard;
        do{ //A unique card is drawn
                drawnCard = drawCard();
            } while (isAlreadyDrawn(drawnCard) == true);
            playedCards = playedCards + drawnCard;
            addPoints(drawnCard);
            return drawnCard;
    }
    
	/**
     * playPlayerRound plays the human players black jack round
     * @return false if player got more than 21 points. Returns true otherwise.     * 
     */
    private boolean playPlayerRound(){
    	int result = playRound("Player");
    	
    	switch (result){
    	case 0:
    		view.printnl("You chose to hold. Let's see how the dealer plays.");
    		return true;
    	case 1:
    		view.printnl("You got more than 21 points. You lost.");
    		return false;
    	case 2:
    		view.printnl("Great! You got exactly 21 points. Let's see how the dealer plays.");
    		return true;
    	default: return true;
    	}    	
    }
    
    /**
	 * playRound until the player/dealer holds, has exactly 21 points or has more than 21 points
	 * @param playerType the type of player. Only accepts "Dealer" and "Player"
	 * @return 0 if playertype chooses to hold. 
	 * 		   1 if playertype gets more than 21 points. 
	 * 		   2 if playertype gets exactly 21 points
	 */
	
	private int playRound(String playerType){
	    boolean boContinue = true;
	    int outcomeOfTurn;
	    String drawnCard;
	    do{ //A card is drawn until the player holds, or total points played is equal to or higher than 21
	        drawnCard = playOneTurn();
	        outcomeOfTurn = turnOutcome(playerType);
	        if (outcomeOfTurn == 0 && playerType.equalsIgnoreCase("Player")){
	        	boContinue = doesPlayerWantToContinue(drawnCard);
	        }
	        else if(outcomeOfTurn == 0 && playerType.equalsIgnoreCase("Dealer")){
	        	boContinue = true;
	        }
	        else{
	        	boContinue = false;
	        }
	   } while (boContinue);
	
	    return outcomeOfTurn;
	}
    
    private void printRules(){
    	view.printnl("I will just go over the rules before we start: \n"
    				+"The goal of the game is to get as close to 21 points without getting more. \n" +
    				"At the same time you also have to have more points than the dealer unless you want to lose.\n" +
    				"Each turn you draw a card and chose to continue or hold.\n" +
    				"If you get more than 21 points you lose automatically.\n" +
    				"If you choose to hold the delaer will play.\n" +
    				"The one who gets closest to 21 points without getting more points win.\n" +
    				"The dealer wins on a tie.\n" +
    				"The card values 2 to 9 is worth their printed values. \n" +
    				"10 and all the royals are worth 10 points.\n" +
    				"The ace is a special card. It counts as 11 points as long as that doesn't give you more than 21 points.\n" +
    				"If you get more than 21 points with an ace, the value of the ace is changed to 1 point.\n" +
    				"That was all the rules. Let's begin!");
    	
    }
    
    /**
     * Resets the variables where points for each playerround are stored
     */
    private void resetGlobalVariables(){
        pointsPlayed = 0;
        numberOfAcesWorth11 = 0;
    }
    
    /**
     * saveInfoAboutDealerRound saves the info about the dealer's played round 
     * and tells the player which cards where played.
     */
    private void saveInfoAboutDealerRound() {
    	dealerPoints = pointsPlayed;
        playedCardsDealer = playedCards.replace(playedCardsPlayer,"");
        view.printnl("Dealer played: " + playedCardsDealer);
    }
    
    /**
     * saveInfoAboutPlayersRound saves the info about the player's played round 
     * and tells the player which cards where played.
     */
    private void saveInfoAboutPlayersRound() {
    	playerPoints = pointsPlayed;
        playedCardsPlayer=playedCards;
        view.printnl("You played: " + playedCardsPlayer);
    }
    
    /**
     * turnOutcome changes the result from the method checkPoints into an integer
     * and returns this.
     * @param playerType - the type of player
     * @return Returns 
     * <li> 0 if the player has less than number where s/he should stop
     * <li> 1 if the player has more than 21 points
     * <li> 2 if the player has exactly 21 points
     * <li> 3 if player has between the number where s/he should stop and 21
     */
    private int turnOutcome(String playerType){
        switch (checkPoints(playerType)){
            case "More than 21":
                return 1;
            case "Exactly 21":
                return 2;
            case "Less than max":
            	return 0;
            case "Between max and 21":
            	return 3;
            default: return 99;
         }
        
    }
}