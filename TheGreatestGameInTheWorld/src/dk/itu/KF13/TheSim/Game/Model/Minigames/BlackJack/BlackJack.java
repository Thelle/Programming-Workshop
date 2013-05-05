package dk.itu.KF13.TheSim.Game.Model.Minigames.BlackJack;
import java.util.Random;


import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.View.GameView;

/**
 * BlackJack is responsible for running the game Black jack
 * @author Simon & Thelle
 */
public class BlackJack {
    String playedCards ="";
    String playedCardsPlayer ="";
    String playedCardsDealer ="";
    int pointsPlayed;
    int playerPoints;
    int dealerPoints;
    int numberOfAces = 0;
    int numberOfAcesWorth11 = 0;
    
    private BlackJackController controller;
    
    public BlackJack(){
    	controller = new BlackJackController();
    }
    
    /**
     * playBlackJack runs the Black Jack game.
     */
    public void playBlackJack(HumanPlayer player){
        boolean playerDidWin;
    	
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
    		GameView.printnl("You win two beers");
    		player.addBeersToBackpack(2);
    	} else {
    		GameView.printnl("You loose two beers");
    		player.removeBeersFromBackpack(2);
    	}        
    }
    
    /**
     * playPlayerRound plays the human players black jack round
     * @return false if player got more than 21 points. Returns true otherwise.     * 
     */
    private boolean playPlayerRound(){
    	int result = playRound("Player");
    	
    	switch (result){
    	case 0:
    		GameView.printnl("You chose to hold. Let's see how the dealer plays.");
    		return true;
    	case 1:
    		GameView.printnl("You got more than 21 points. You lost. \nYou played: " + playedCards);
    		return false;
    	case 2:
    		GameView.printnl("Great! You got exactly 21 points. Let's see how the dealer plays.");
    		return true;
    	default: return true;
    	}    	
    }
    
    /**
     * saveInfoAboutPlayersRound saves the info about the player's played round 
     * and tells the player which cards where played.
     */
    private void saveInfoAboutPlayersRound() {
    	playerPoints = pointsPlayed;
        playedCardsPlayer=playedCards;
        GameView.printnl("You played: " + playedCardsPlayer);
    }
    
    /**
     * saveInfoAboutDealerRound saves the info about the dealer's played round 
     * and tells the player which cards where played.
     */
    private void saveInfoAboutDealerRound() {
    	dealerPoints = pointsPlayed;
        playedCardsDealer = playedCards.replace(playedCardsPlayer,"");
        GameView.printnl("Dealer played: " + playedCardsDealer);
    }
    
    /**
     * Resets the variables where points for each playerround are stored
     */
    private void resetGlobalVariables(){
        pointsPlayed = 0;
        numberOfAces = 0;
        numberOfAcesWorth11 = 0;
    }
    
    /**
     * playerDidWin tests if the player won or lost
     * @return true if the player did win. False if not
     */
    private boolean playerDidWin(){
    	if(playerPoints > dealerPoints || dealerPoints > 21){
        	GameView.printnl("Congratulations. You won with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	return true;
    	}
        else {
        	GameView.printnl("Loser! You lost with with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	return false;
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
   
	/**
	 * doesPlayerWantToContinue asks the player if he wants to continue
	 * @param drawnCard - the card that has just been drawn.
	 * @return True if the player wants to continue. False otherwise.
	 */
	private boolean doesPlayerWantToContinue(String drawnCard) {
		 GameView.print("You have drawn this card: " + 
	            drawnCard + "\nYou currently have " + pointsPlayed + " points" + 
	            "\nDo you want to continue (Y/N)?");
		 String answer = controller.getYesNo();
		 switch (answer){
		 case "y": GameView.print(""); return true;
		 case "n": GameView.print(""); return false;
		 default: return false; //the method getYesNo only returns y or n
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
            //pointsPlayed is greater than maxNumber, but less than 21
        	return "Between max and 21";
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
            numberOfAces++;
            numberOfAcesWorth11++;
        }
        pointsPlayed = pointsPlayed + cardPoints;
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
}