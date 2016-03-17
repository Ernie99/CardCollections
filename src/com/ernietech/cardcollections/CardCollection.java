package com.ernietech.cardcollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static java.util.Collections.replaceAll;
import static java.util.Collections.shuffle;

/**
 * Created by Ernie on 3/13/2016.
 */

/**
 * abstract parent for all card collections (standard decks, hands, multi decks, etc)
 * @param <T> type of card used
 *
 * NOTE: we are using "T extends Card" for future renovation
 */
public abstract class CardCollection <T extends Card> implements Iterable<T> {

    public static void main(String [] args){ // main just for testing!
        Deck<Card.FrenchCard> testDeck = new Deck<Card.FrenchCard>();
        System.out.println(testDeck);
        System.out.println(testDeck.drawRandom());
        System.out.println(testDeck);
    }

    private List<T> cards = new ArrayList<>();

    @Override
    public Iterator<T> iterator() { // TODO think about this, api designed not to "peek" at cards without drawing, This gives us a back door to
        return new Iterator<T>() {
            private final Iterator<T> iter = cards.iterator();
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed, use the Deck api instead");
            }
            //TODO override forEachRemaining, not commonly used
        };
    }

    /**
     * draws random card and removes from deck
     * @return card from deck
     * MODIFIES super.cards
     */
    public T drawRandom(){ // make generator static and use const seed for repeatability, good for testing, bad for cheating
        //TODO change to drawTop() and shuffle deck on creation. That would make this more like a "real life" deck
        List<T> cards = getCards();
        T pickedCard = cards.get(0);
        cards.remove(0);
        return pickedCard;
    }

    // or call methods with our own "convenience" api
    public int getSize(){
        return cards.size();
    }

    @Override
    public String toString() {
//        return super.toString();
        String s = ""; // yes a string builder is better
        for(T card : cards){
            s += "[" + card.toString() + "]";
        }
        return s;
    }

    protected List<T> getCards(){ // not part of the public api!
        return cards;
    }

    // we only want to draw from a deck, so no method to add cards!
    // no method to see what cards are in the deck
    // Deck is initialized to 52 cards

    /**
     * base class that represents deck of 52 cards
     *
     * API designed to mimic actions a dealer would perform on a deck
     * No methods to "peek", or add cards back.
     * When deck is exhausted create a new one. Perhaps add more
     * functions to add card, reshuffle, etc.. maybe useful for unusual
     * card games that I have not played.
     *
     * @param <T> type of card used
     */
    public static class Deck <T extends Card>  extends CardCollection<T>{ // single deck
        /**
         * initialized to a FULL DECK
         */
        public Deck(){
            super();
            List<T> cards = super.getCards();
            buildDeck();
        }

        // helper to insert 52 unique cards
        // TODO this is bad, must change! we are making new FrenchCards and not T
        // TODO will use builder or factory pattern
        private void buildDeck(){
            List<T> cards = super.getCards();
            for(Card.Suit s : Card.Suit.values()){
                for(Card.Face f : Card.Face.values()){
                    //do we need this cast? alternative ? think this is  good way
                   cards.add((T) new Card.FrenchCard(s,f));
                }
            }
            shuffle(cards);
        }

        //do not use! used to make small deck for testing
        private void buildMiniDeck(){
            List<T> cards = super.getCards();
            cards.add((T) new Card.FrenchCard(Card.Suit.SPADE,Card.Face.TWO));
            cards.add((T) new Card.FrenchCard(Card.Suit.SPADE,Card.Face.THREE));
            cards.add((T) new Card.FrenchCard(Card.Suit.SPADE,Card.Face.FOUR));
            cards.add((T) new Card.FrenchCard(Card.Suit.SPADE,Card.Face.FIVE));
        }
    }

    /**
     *  base class that represents hand of cards, also used as a pool
     *
     * @param <T> type of card used
     */
    public static class Hand <T extends Card>  extends CardCollection<T>{
        /**
         * initialized to an EMPTY HAND
         */
        public Hand(){
            super();
        }

        /**
         * add card to hand
         * @param c card to add
         * MODIFIES super.cards
         */
        public void addCard(Card c){
            super.getCards().add((T) c);
        }

        /**
         * draw card from hand
         * @param s suit of card to draw
         * @param f face of card to draw
         * @return T card from hand
         * @throws UnsupportedOperationException when hand does not contain card, unchecked exception!
         * MODIFIES super.cards
         */
        public T drawCard(Card.Suit s, Card.Face f) throws UnsupportedOperationException{
            List<T> cards = super.getCards();
            //TODO this is bad, must change! we are making new FrenchCards and not T
            T testCard = (T) new Card.FrenchCard(s,f);
            int index = -1;
            index = cards.indexOf(testCard);
            if(index == -1){
                throw new UnsupportedOperationException("tried to use card that is not in hand");
            }
            cards.remove(index);
            return testCard; // we are returning test card, maybe safer to return actual card
        }
    }
}
