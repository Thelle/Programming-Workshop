/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.KF13.TheSim.Game.Minigames;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author stcl
 */
public class BlackJack {
    static String allPlayedCards = "";
    static String playerPlayedCards = "";
    static String dealerPlayedCards = "";
    static int pointsPlayed;
    static int numberOfAces = 0;
    static int numberOfAcesWorth11 = 0;
    
    public void playBlackJack(){
        playerPlays();
        resetGlobalVariables();
        dealerPlays();
        
    }
    private static void dealerPlays() {
        int intContinue = 0;
        String drawnCard;
        do{ //A card is drawn until the number of points is equal to or higher than 17
                //or total points played is equal to or higher than 21
            drawnCard = playOneTurn("Dealer");
       } while (!dealerStops());
        System.out.println("Dealer played: "+dealerPlayedCards);
        System.out.println("All played cards are: "+allPlayedCards);
    }   
    
    private static void resetGlobalVariables(){
        pointsPlayed = 0;
        numberOfAces = 0;
        numberOfAcesWorth11 = 0;
    }
    
    private static void playerPlays(){
        int intContinue = 0;
        String drawnCard;
        do{ //A card is drawn until the player holds, or total points played is equal to or higher than 21
            drawnCard = playOneTurn("Player");
       } while (!playerStops(drawnCard));
        System.out.println("You played: "+allPlayedCards);
    }
    
    private static String playOneTurn(String playerType){
        String drawnCard;
        do{ //A unique card is drawn
                drawnCard = drawCard();
            } while (isAlreadyDrawn(drawnCard) == true);
            allPlayedCards = allPlayedCards + drawnCard;
            switch (playerType){
            	case "Player":
            		playerPlayedCards = playerPlayedCards + drawnCard;
            		break;
            	case "Dealer":
            		dealerPlayedCards = dealerPlayedCards + drawnCard;
            		break;
            }
            addPoints(drawnCard);
            return drawnCard;
    }
    
    private static boolean playerStops(String drawnCard){
        int intContinue = 0;
        switch (checkPoints("Player")){
            case "More than 21":
                intContinue = 1;
                System.out.println("You got more than 21 points - you lost");
                break;
            case "Exactly 21":
                intContinue = 1;
                System.out.println("You got 21 - nice going!");
                break;
            case "Less than max":
                intContinue = JOptionPane.showConfirmDialog(null, "You have drawn this card: " + 
                    drawnCard + "\nYou currently have " + pointsPlayed + " points" + 
                    "\nDo you want to continue?", "Draw another card?", JOptionPane.YES_NO_OPTION);
                    //JOptionPane returns 0 for no and 1 for yes.
         }
         if (intContinue == 1) {
             return true;
         }
         else {
             return false;
         }   
        
}
    
    private static boolean dealerStops(){
        int intContinue = 0;
        switch (checkPoints("Dealer")){
            case "More than 21":
                intContinue = 1;
                System.out.println("Dealer got more than 21 points - you win");
                break;
            case "Exactly 21":
                intContinue = 1;
                System.out.println("Dealer got 21 points - you loose");
                break;
            case "Less than max":
                intContinue = 0;
                break;
            case "More than or equal to max":
            	intContinue = 1;
            	System.out.println("Dealer stops");
         }
         if (intContinue == 1) {
             return true;
         }
         else {
             return false;
         }   
        
}
    
    private static String checkPoints (String playerType ){
        int maxNumber = 0;
        if (playerType.equals("Dealer")){
            maxNumber = 17; //Dealer does only continue if it has less than 17 points.
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
        else if  (pointsPlayed >= maxNumber){
        	return "More than or equal to max";
        }
        else{
            return null;
        }
    }
    
    private static void addPoints(String drawnCard){
        String numbersOnly = drawnCard.replaceAll("[()]","");
        numbersOnly = numbersOnly.replaceFirst("[1-4] ","");
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

    private static String drawCard(){
        Random rand = new Random();
        int cardSuit = rand.nextInt(3) + 1; //Adding 1 to match values in a card deck
        int cardNumber = rand.nextInt(13) + 1;
        return "(" + cardSuit + " " + cardNumber + ")";
    }
    
    private static boolean isAlreadyDrawn (String drawnCard) {
        int subStringIndex = allPlayedCards.lastIndexOf(drawnCard);
        if (subStringIndex == -1){ //Card is not already drawn
            return false;
        }
        else{
            return true;
        }
    }
}