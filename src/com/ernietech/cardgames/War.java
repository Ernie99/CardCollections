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

        while(playerOneHand.getSize()!=0){ // really we should check both hands for empty, but since both players are drawing at the same time, this is ok
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
            } else if(weight2>weight1){
                result= "player 2 wins";
                playerTwoPile.addCard(c1); // put both cards in player 2 pile
                playerTwoPile.addCard(c2);
            } else{
//                result = "WAR";
                System.out.println("WAR");
                drawWar(playerOneHand, playerTwoHand, playerOnePile, playerTwoPile);
            }
            System.out.println(result);
        }
    }

    //todo list loop invariants in comments
    private static void drawWar(CardCollection.Hand<Card.FrenchCard> playerOneHand, CardCollection.Hand<Card.FrenchCard> playerTwoHand,
                                CardCollection.Hand<Card.FrenchCard> playerOnePile, CardCollection.Hand<Card.FrenchCard> playerTwoPile){
        List<Card.FrenchCard> warPile = new ArrayList<>();
        // we are using a *regular* java collection to hold cards // todo, think about this as it relates to design decisions
        for(int i=0; i<3; i++){
            warPile.add(playerOneHand.drawRandom());
            warPile.add(playerTwoHand.drawRandom());
        }
        Card.FrenchCard playerOneWarCard = playerOneHand.drawRandom();
        Card.FrenchCard playerTwoWarCard = playerTwoHand.drawRandom();

        int weight1 = getCardWeight(playerOneWarCard);
        int weight2 = getCardWeight(playerOneWarCard);

        String result = "";
        if(weight1>weight2){
            result = "player 1 wins this WAR";
            addToPile(playerOnePile, warPile);
            playerOnePile.addCard(playerOneWarCard);
            playerOnePile.addCard(playerTwoWarCard);
        } else if(weight2>weight1){
            result= "player 2 this WAR";
            addToPile(playerTwoPile, warPile);
            playerTwoPile.addCard(playerOneWarCard);
            playerTwoPile.addCard(playerTwoWarCard);
        } else{
//            result = "another war!";
            System.out.println("another war");// todo use more sensible logic for this println ordering
            drawWar(playerOneHand, playerTwoHand, playerOnePile, playerTwoPile);
        }
        System.out.println(result);

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
                weight=10;
                break;
            case KING:
                weight=10;
                break;
        }
        return weight;
    }
}
