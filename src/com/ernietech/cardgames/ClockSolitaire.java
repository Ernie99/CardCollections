package com.ernietech.cardgames;

/**
 * Created by Ernie on 5/1/2016.
 */

import com.ernietech.cardcollections.Card;
import com.ernietech.cardcollections.Card.Face;
import com.ernietech.cardcollections.CardCollection;

import java.util.ArrayList;
import java.util.List;

public class ClockSolitaire {

    public static void main(String [] args){
        ClockSolitaire theGame = new ClockSolitaire();
        theGame.printPiles(true);

    }

    ClockSolitaire(){
        // deck is not a global because we consume all cards by dealing to hands
        CardCollection.Deck<Card.FrenchCard> deck = new CardCollection.Deck<>();
        pilesFaceDown = new ArrayList<>(13); // 1 through 12 + center pile
        pilesFaceUp = new ArrayList<>(13); // 1 through 12 + center pile

        //initialize EMPTY piles
        for(int i = FACE_INDEX_ACE_START; i <= FACE_INDEX_KING_CENTER; i++){
            pilesFaceDown.add(new ArrayList<Card.FrenchCard>());
            pilesFaceUp.add(new ArrayList<Card.FrenchCard>());
        }

        // NOTE: since arrays in java are zero-based clock values are one
        // less than array index

        // NOTE: array index of center pile is 12

        int clockIndex = FACE_INDEX_ACE_START;

        while(deck.getSize() != 0){
            // draw from deck and add to each face down pile
            pilesFaceDown.get(clockIndex).add(deck.drawRandom());
            if (clockIndex == FACE_INDEX_KING_CENTER){
                clockIndex = FACE_INDEX_ACE_START;
            } else {
                clockIndex++;
            }
        }
    }

    // prints list of piles, uses array index to print clock position
    void printPiles(boolean showFaceDown){

        for(int i = 0; i < FACE_INDEX_KING_CENTER; i
                ++){
            System.out.println("CLOCK NUMBER: " + (i + 1)); // add 1 because zero indexed

            System.out.println("face down pile...");
            for(Card.FrenchCard c: pilesFaceUp.get(i)){
                System.out.println(c);
            }

            if(showFaceDown){
                System.out.println("face up pile...");
                for(Card.FrenchCard c: pilesFaceDown.get(i)){
                    System.out.println(c);
                }
            }


        }
    }



    //// instance vars ////
    private List<List<Card.FrenchCard>> pilesFaceDown;
    private List<List<Card.FrenchCard>> pilesFaceUp;

    static final int FACE_INDEX_ACE_START = 0;
    static final int FACE_INDEX_KING_CENTER = 12; // also the LAST index


}
