package com.ernietech.cardcollections;

/**
 * Created by Ernie on 3/13/2016.
 */
public abstract class Card {

    public static void main(String [] args){ // main just for testing! junit is better but this is quick
        System.out.println("testing card class and subclass");
        Card c1 = new FrenchCard(Suit.SPADE, Face.ACE); // ace of spades
        Card c2 = new FrenchCard(Suit.SPADE, Face.ACE);
        Card c3 = new FrenchCard(Suit.HEART, Face.TWO); // duce of hearts
        System.out.println(c1);
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
    }


    public static class FrenchCard extends Card{ // "French" card because wiki says the French invented the "modern" playing card
        private final Suit suit;
        private final Face face;

        FrenchCard(Suit s, Face f){
            suit = s;
            face = f;
        }

        public Suit getSuit() {
            return suit;
        }

        public Face getFace() {
            return face;
        }

        @Override
        public boolean equals(Object obj) { // maybe throws class cast exception?
//            return super.equals(obj);
            if((((FrenchCard)obj).getFace()==getFace())  && (((FrenchCard)obj).getSuit()==getSuit())){
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            String s = " Face: " + getFace().name() + ", Suit: " + getSuit().name();
            return s;
        }
    }

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;
    }
    public enum Face{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, JACK, QUEEN, KING
    }
}
