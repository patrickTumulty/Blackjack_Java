package com.pt.gamecomponents;

import java.util.LinkedList;
import java.util.Queue;

public class BlackjackDeck extends CardDeck {
    private int numberOfDecks;

    public BlackjackDeck(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        initialize();
    }

    @Override
    protected void initialize() {
        makeDeck();
        addDecks(numberOfDecks);
        numberOfCards = deck.size();
        shuffleDeck();
    }

    @Override
    public void resetDeck() {
        makeDeck();
        addDecks(numberOfDecks);
        numberOfCards = deck.size();
        shuffleDeck();
    }

    protected void addDecks(int numberOfDecks) {

        // Input Checking
        if (numberOfDecks > 8) {
            numberOfDecks = 8;
        } else if (numberOfDecks < 1) {
            numberOfDecks = 1;
        }

        Queue<Card> temp = new LinkedList<Card>(deck);
        for (int i = 0; i < (numberOfDecks - 1); i++) {
            deck.addAll(temp);
        }
    }
}
