package com.blackjack;


import java.util.*;

public class CardDeck {
    protected Queue<Card> deck;
    protected int numberOfCards;
    protected boolean jokersInDeck = false;

    CardDeck() {

    }

    protected void initialize() {
        makeDeck();
        numberOfCards = deck.size();
        shuffleDeck();
    }

    protected void makeDeck() {
        deck = new LinkedList<Card>();
        for (Suit suit: Suit.values()) {
            if (suit != Suit.JOKER) {
                for (Face face: Face.values()) {
                    if (face != Face.JOKER)
                        deck.add(new Card(suit, face, face.getValue()));
                }
            }
        }
    }

    protected void setAcesValue(int value) {
        for (Card card : deck) {
            if (card.getFace().equals(Face.ACE)) {
                card.setValue(value);
            }
        }
    }

    protected void addJokers() {
        deck.add(new Card(Suit.JOKER, Face.JOKER, Face.JOKER.getValue()));
        deck.add(new Card(Suit.JOKER, Face.JOKER, Face.JOKER.getValue()));
    }

    public void shuffleDeck() {
        Random rand = new Random();
        Card[] tempDeck = deck.toArray(new Card[numberOfCards]);
        for (int i = 0; i < numberOfCards; i++) {
            int randSwapIndex = rand.nextInt(numberOfCards);
            Card temp = tempDeck[randSwapIndex];
            tempDeck[randSwapIndex] = tempDeck[i];
            tempDeck[i] = temp;
        }
        deck = new LinkedList<Card>(Arrays.asList(tempDeck));
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    public int getDeckSize() {
        return numberOfCards;
    }

    public Card revealTopCard() {
        return deck.peek();
    }

    public Card drawTopCard() {
        return deck.poll();
    }

    public void resetDeck() {
        makeDeck();
        shuffleDeck();

    }
}


