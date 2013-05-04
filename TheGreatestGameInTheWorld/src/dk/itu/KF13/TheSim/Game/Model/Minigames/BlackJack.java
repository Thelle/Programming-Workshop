package dk.itu.KF13.TheSim.Game.Model.Minigames;
import java.util.Random;

import javax.swing.JOptionPane;

import dk.itu.KF13.TheSim.Game.Model.Physical.HumanPlayer;
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
    
    /**
     * playBlackJack runs the game.
     * 
     * @return
     */
    public void playBlackJack(HumanPlayer player){
        
    	switch (playRound("Player")){
        	case 0:
        		view.print("You chose to hold. Let's see how the dealer plays.");
        		break;
        	case 1:
        		view.print("You got more than 21 points. You lost. \nYou played: " + playedCards);
        		player.removeBeersFromBackpack(2);
        		break;
        	case 2:
        		view.print("Great! You got exactly 21 points. Let's see how the dealer plays.");
        		break;
        }    	
        playerPoints = pointsPlayed;
        playedCardsPlayer=playedCards;
        view.print("You played: " + playedCardsPlayer);
        
        resetGlobalVariables();
        playRound("Dealer");
        
        dealerPoints = pointsPlayed;
        playedCardsDealer = playedCards.replace(playedCardsPlayer,"");
        view.print("Dealer played: " + playedCardsDealer);
        
                
        if(playerPoints > dealerPoints || dealerPoints > 21){
        	view.print("Congratulations. You won with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	player.addBeersToBackpack(2);
        }
        else {
        	view.print("Loser! You lost with with " + playerPoints + " points against the dealer's " + dealerPoints + " points.");
        	player.removeBeersFromBackpack(2);
        }
    }
       
    private void resetGlobalVariables(){
        pointsPlayed = 0;
        numberOfAces = 0;
        numberOfAcesWorth11 = 0;
    }
    
    /**
     * playRound until the player/dealer holds, has exactly 21 points or has more than 21 points
     * @param playerType the type of player. Only accepts "Dealer" and "Player"
     * @return 0 if playertype chooses to hold. 1 if playertype gets more than 21 points. 
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
    
    private boolean doesPlayerWantToContinue(String drawnCard) {
    	int intContinue = JOptionPane.showConfirmDialog(null, "You have drawn this card: " + 
                drawnCard + "\nYou currently have " + pointsPlayed + " points" + 
                "\nDo you want to continue?", "Draw another card?", JOptionPane.YES_NO_OPTION);
                //JOptionPane returns 1 for no and 0 for yes.
    	if (intContinue == 1){
    		return false;
    	}
    	else{
    		return true;
    	}
	}
    
	private String playOneTurn(){
        String drawnCard;
        do{ //A unique card is drawn
                drawnCard = drawCard();
            } while (isAlreadyDrawn(drawnCard) == true);
            playedCards = playedCards + drawnCard;
            addPoints(drawnCard);
            return drawnCard;
    }
    
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

    private String drawCard(){
        Random rand = new Random();
        int cardSuitNumber = rand.nextInt(3) + 1; //Adding 1 to match values in a card deck
        int cardNumber = rand.nextInt(13) + 1;
        String suitName = changeSuitNumberIntoText(cardSuitNumber);
        return "(" + suitName + " " + cardNumber + ")";
    }
    
    private String changeSuitNumberIntoText(int suitNumber){
    	switch (suitNumber){
    	case 1: return "Hearts";
    	case 2: return "Spades";
    	case 3: return "Diamonds";
    	case 4: return "Clubs";
    	default: return null;
    	}
    }
    
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