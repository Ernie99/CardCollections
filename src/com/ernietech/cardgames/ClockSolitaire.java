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

    }

    ClockSolitaire(){
        // deck is not a global because we consume all cards by dealing to hands
        CardCollection.Deck<Card.FrenchCard> deck = new CardCollection.Deck<>();
        pilesFaceDown = new ArrayList<>(13); // 1 through 12 + center pile
        pilesFaceUp = new ArrayList<>(13); // 1 through 12 + center pile

        // NOTE: since arrays in java are zero-based clock values are one
        // less than array index

        // NOTE: array index of center pile is 12

        int clockIndex = FACE_INDEX_ACE_START;

        while(deck.getSize() != 0){
            // draw from deck and add to each face down pile
            pilesFaceUp.get(clockIndex).add(deck.drawRandom());
            if (clockIndex == FACE_INDEX_KING_CENTER){
                clockIndex = FACE_INDEX_ACE_START;
            } else {
                clockIndex++;
            }
        }
    }

    // prints list of piles, uses array index to print clock position
    static void printPiles(List<List<Card.FrenchCard>> toPrint){
        
        for(List<Card.FrenchCard> pile: toPrint){

        }
    }



    //// instance vars ////
    private List<List<Card.FrenchCard>> pilesFaceDown;
    private List<List<Card.FrenchCard>> pilesFaceUp;

    static final int FACE_INDEX_ACE_START = 0;
    static final int FACE_INDEX_KING_CENTER = 12;


}
