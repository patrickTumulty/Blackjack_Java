package com.blackjack;

import java.util.LinkedList;
import java.util.Queue;

class BlackjackDeck extends CardDeck {
    BlackjackDeck(int numberOfDecks) {
        mNumberOfDecks = numberOfDecks;
        makeDeck(mNumberOfDecks);
        shuffleDeck();
    }

    protected void makeDeck(int numberOfDecks) {
        super.makeDeck();

        // Input Checking
        if (numberOfDecks > 8) {
            numberOfDecks = 8;
        } else if (numberOfDecks < 1) {
            numberOfDecks = 1;
        }

        Queue<Card> temp = new LinkedList<Card>(mDeck);
        for (int i = 0; i < (numberOfDecks - 1); i++) {
            mDeck.addAll(temp);
        }
        mNumberOfCards = mDeck.size();
    }

    public void resetDeck() {
        makeDeck(mNumberOfDecks);
        shuffleDeck();

    }

    protected int mNumberOfDecks;
}
