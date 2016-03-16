package com.ernietech.cardcollections;

/**
 * Created by Ernie on 3/13/2016.
 */
public abstract class CardCollectionFactory {

    protected abstract CardCollection getShoe(int decks); // shoe has multiple decks
    protected abstract CardCollection getDeck();
    protected abstract CardCollection getHand();

    static class stdFiftyTwoFactory extends CardCollectionFactory{
        @Override
        protected CardCollection getHand() {
            return null;
        }

        @Override
        protected CardCollection getDeck() {
            return null;
        }

        @Override
        protected CardCollection getShoe(int decks) {
            return null;
        }
    }
}
