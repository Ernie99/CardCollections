package com.ernietech.cardgames;

/**
 * Created by Ernie on 3/15/2016.
 */

import com.ernietech.cardcollections.Card;
import com.ernietech.cardcollections.Card.Face;
import com.ernietech.cardcollections.CardCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * War, a simple card game that children play
 * Just using this as a test for decks and cards, so all code is static, for simplicity
 */
public class War {

    public static void main(String [] args){
        //main deck that we start with
        War game; // we can probably build these in
        for(int i = 0; i < 100; i++){
            gameNumber++;
            game = new War();
            game.playGame();

        }
        System.exit(0);
    }

    public War(){
        // deck is not a global because we consume all cards by dealing to hands
        CardCollection.Deck<Card.FrenchCard> deck = new CardCollection.Deck<>();
        playerOneHand = new CardCollection.Hand<>();
        playerTwoHand = new CardCollection.Hand<>();
        while(deck.getSize() !=0 ){ // shouldn't throw because we have 2 players and even deck size
            playerOneHand.addCard(deck.drawRandom());
            playerTwoHand.addCard(deck.drawRandom());
        }
    }

    public int playGame(){
        List<Card.FrenchCard> playerOneWinPile= new ArrayList<>();
        List<Card.FrenchCard> playerTwoWinPile= new ArrayList<>();
        List<Card.FrenchCard> tempPile= null;
        while(playerOneHand.getSize() != 0) { // really we should check both hands for empty, but since both players are drawing at
            int handWinner = 0;
            tempPile = new ArrayList<>();
            handWinner = buildWinPile(playerOneHand, playerTwoHand, tempPile);
            if(handWinner == 1){
                playerOneWinPile.addAll(tempPile);
            } else if(handWinner == 2){
                playerTwoWinPile.addAll(tempPile);
            }
        }
        int playerOneFinal = playerOneWinPile.size();
        int playerTwoFinal = playerTwoWinPile.size();
        int winner;
        if(playerOneFinal > playerTwoFinal){
            winner = 1;
        }else if(playerOneFinal < playerTwoFinal){
            winner = 2;
        } else{
            winner = 0; // tie
        }
        System.out.println("GAME: " + gameNumber +"**************game winner************** is: " + winner);
        return winner;
    }

    // main logic for game, plays one hand, but is recursive when there is a tie (war)
    // builds win pile and returns winner
    // Always call with new EMPTY list, note: recursive calls uses non empty list
    //returns winner, 0 for a tie, but this never happens since we keep on drawing
    //todo remove playerOneHand and playerTwoHand params, they are private globals
    private int buildWinPile(CardCollection.Hand<Card.FrenchCard> playerOneHand, CardCollection.Hand<Card.FrenchCard> playerTwoHand, List<Card.FrenchCard> pile){
        Card.FrenchCard playerOneLastCard = playerOneHand.drawRandom();
        Card.FrenchCard playerTwoLastCard = playerTwoHand.drawRandom();
        int weight1 = getCardWeight(playerOneLastCard);
        int weight2 = getCardWeight(playerTwoLastCard);
        pile.add(playerOneLastCard); // two lines MODIFY the pile
        pile.add(playerTwoLastCard);

        System.out.println("CARDS: " + "player 1- " + playerOneLastCard + " player 2- " + playerTwoLastCard);

        if(weight1 > weight2){
            System.out.println("player 1 wins");
            return 1;
        } else if(weight1 < weight2){
            System.out.println("player 2 wins");
            return 2;
        } else{
            System.out.println("tie");
            int cardsToWaste = 0; // default is 2
            int cardsInHandOne = playerOneHand.getSize();
            if(cardsInHandOne >= 3){ // if we are short on cards while in a war
                cardsToWaste = 2;
            }else if(cardsInHandOne == 2){
                cardsToWaste = 1;
            }else if(cardsInHandOne == 1){
                cardsToWaste = 0;
            }else if(cardsInHandOne == 0){
                return 0;
            }

            for(int i=0; i < cardsToWaste; i++){
                playerOneLastCard = playerOneHand.drawRandom();
                playerTwoLastCard = playerTwoHand.drawRandom();
                pile.add(playerOneLastCard);
                pile.add(playerTwoLastCard);
            }
            return buildWinPile(playerOneHand, playerTwoHand, pile);
        }
    }

    static int repeatedWarCounter = 0; // our only public
    static int gameNumber = 0;

    CardCollection.Hand<Card.FrenchCard> playerOneHand;
    CardCollection.Hand<Card.FrenchCard> playerTwoHand;
    List<Card.FrenchCard> playerOneWinPile= new ArrayList<>();
    List<Card.FrenchCard> playerTwoWinPile= new ArrayList<>();

    //super small helper method
    private static void addToPile(CardCollection.Hand<Card.FrenchCard> playerPile, List<Card.FrenchCard> warPile){
        //todo, make a method that takes collection in Deck so we don't need to use a for loop
        for(Card.FrenchCard card : warPile){
            playerPile.addCard(card);
        }

    }

    // perhaps we should use comparable, but this is for testing
    private static Card.FrenchCard getGreaterCard(Card.FrenchCard c1, Card.FrenchCard c2){
        return null;
    }

    private static int getCardWeight(Card.FrenchCard c){
        int weight = 0;
        switch (c.getFace()){
            case ACE:
                weight=1;
                break;
            case TWO:
                weight=2;
                break;
            case THREE:
                weight=3;
                break;
            case FOUR:
                weight=4;
                break;
            case FIVE:
                weight=5;
                break;
            case SIX:
                weight=6;
                break;
            case SEVEN:
                weight=7;
                break;
            case EIGHT:
                weight=8;
                break;
            case NINE:
                weight=9;
                break;
            case TEN:
                weight=10;
                break;
            case JACK:
                weight=11;
                break;
            case QUEEN:
                weight=12;
                break;
            case KING:
                weight=13;
                break;
        }
        return weight;
    }
}
