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
    static int repeatedWarCounter = 0; // our only public

    public static void main(String [] args){
        //main deck that we start with
        for(int i = 0; i < 100; i++){
            playGame();
        }
        System.exit(0);
    }

    //todo list loop invariants in comments
    private static int drawWar(CardCollection.Hand<Card.FrenchCard> playerOneHand, CardCollection.Hand<Card.FrenchCard> playerTwoHand, List<Card.FrenchCard> pile){
//        List<Card.FrenchCard> warPile = new ArrayList<>();
        // we are using a *regular* java collection to hold cards // todo, think about this as it relates to design decisions
        repeatedWarCounter ++;
        int cardsToWar = 3;
        if(playerOneHand.getSize() < 2){ // if we are short on cards while in a war
            cardsToWar = playerOneHand.getSize() - 2;
        }
        for(int i=0; i < cardsToWar; i++){
            pile.add(playerOneHand.drawRandom());
            pile.add(playerTwoHand.drawRandom());
        }
        Card.FrenchCard playerOneWarCard = playerOneHand.drawRandom(); // todo, clean this we can use last indexed in loop
        Card.FrenchCard playerTwoWarCard = playerTwoHand.drawRandom();
        pile.add(playerOneWarCard); // todo, we can also do this in fewer steps, clear enough for now
        pile.add(playerTwoWarCard);

        int weight1 = getCardWeight(playerOneWarCard);
        int weight2 = getCardWeight(playerTwoWarCard);

        System.out.println("war cards: " + "player 1 " + playerOneWarCard + " player 2 " + playerTwoWarCard);
        if(weight1 > weight2){
            System.out.println("player 1 wins this WAR");
            return 1;
        } else if(weight1 < weight2){
            System.out.println("player 2 wins this WAR");
            return 2;
        } else{
//            result = "another war!";
            //todo not the best println, also repeats code
            System.out.println("another war #" + repeatedWarCounter + " tie on " + playerOneWarCard + " " + playerTwoWarCard);// todo use more sensible logic println ordering
            System.out.println("WEIGHTS= " + weight1 + " " + weight2);
            return drawWar(playerOneHand, playerTwoHand, pile);
        }
    }

    private static void playGame(){
        CardCollection.Deck<Card.FrenchCard> deck = new CardCollection.Deck<Card.FrenchCard>();

        //player hands
        CardCollection.Hand<Card.FrenchCard> playerOneHand = new CardCollection.Hand<>();
        CardCollection.Hand<Card.FrenchCard> playerTwoHand = new CardCollection.Hand<>();

        //player point piles
        CardCollection.Hand<Card.FrenchCard> playerOnePile = new CardCollection.Hand<>();
        CardCollection.Hand<Card.FrenchCard> playerTwoPile = new CardCollection.Hand<>();

        //deal two hands
        while(deck.getSize() !=0 ){ // shouldn't throw because we have 2 players and even deck size
            playerOneHand.addCard(deck.drawRandom());
            playerTwoHand.addCard(deck.drawRandom());
        }
//        System.out.println(playerOneHand);
//        System.out.println(playerTwoHand);



        //todo BIG todo, we are repeating the logic in draw war, fix code duplication!
        while(playerOneHand.getSize() != 0){ // really we should check both hands for empty, but since both players are drawing at the same time, this is ok
            Card.FrenchCard c1 = playerOneHand.drawRandom(); //// TODO again, draw random may not be best
            Card.FrenchCard c2 = playerTwoHand.drawRandom();

            String played = "player 1: " + c1 + " player 2: " + c2;
            System.out.println(played);
            String result = "";

            int weight1 = getCardWeight(c1);
            int weight2 = getCardWeight(c2);

            if(weight1>weight2){
                result = "player 1 wins";
                playerOnePile.addCard(c1); // put both cards in player 1 pile
                playerOnePile.addCard(c2);
                repeatedWarCounter = 0;
            } else if(weight2>weight1){
                result= "player 2 wins";
                playerTwoPile.addCard(c1); // put both cards in player 2 pile
                playerTwoPile.addCard(c2);
                repeatedWarCounter = 0;
            } else{
//                result = "WAR";
                System.out.println("WAR");
                List<Card.FrenchCard> warPile = new ArrayList<>();
                int warWinner = drawWar(playerOneHand, playerTwoHand, warPile);
                if(warWinner==1){
                    addToPile(playerOnePile, warPile);
                }else if(warWinner==2){
                    addToPile(playerTwoPile, warPile);
                }
            }
            System.out.println(result);
        }

    }

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
            case JACK:
                weight=10;
                break;
            case QUEEN:
                weight=11;
                break;
            case KING:
                weight=12;
                break;
        }
        return weight;
    }
}
