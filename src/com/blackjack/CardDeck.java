package com.blackjack;


import java.util.*;

public class CardDeck {
    protected Queue<Card> mDeck;
    protected int mNumberOfCards;
    protected boolean mJokersInDeck = false;

    CardDeck() {
        makeDeck();
        shuffleDeck();
    }

    protected void makeDeck() {
        mNumberOfCards = 52;
        mDeck = new LinkedList<Card>();
//        String[] suits = { "Clubs", "Spades", "Diamonds", "Hearts" };
        String[] suits = { "♠", "♥", "♦", "♣" };
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 11; j++) {
                mDeck.add(new Card(suits[i], String.valueOf(j), j));
                counter++;
            }
        }
        String[] royalty = { "K", "Q", "J", "A" };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(royalty[j], "A")) {
                    mDeck.add(new Card(suits[i], royalty[j], 11));
                } else {
                    mDeck.add(new Card(suits[i], royalty[j], 10));
                }
                counter++;
            }
        }
    }

    protected void setAcesValue(int value) {
        for (Card card : mDeck) {
            if (card.getFace().equals("Ace")) {
                card.setValue(value);
            }
        }
    }

    protected void addJokers() {
            mDeck.add(new Card("Joker", "Joker", 0));
            mDeck.add(new Card("Joker", "Joker", 0));
    }

    public void shuffleDeck() {
        Random rand = new Random();
        Card[] tempDeck = mDeck.toArray(new Card[mNumberOfCards]);
        for (int i = 0; i < mNumberOfCards; i++) {
            int randSwapIndex = rand.nextInt(mNumberOfCards);
            Card temp = tempDeck[randSwapIndex];
            tempDeck[randSwapIndex] = tempDeck[i];
            tempDeck[i] = temp;
        }
        mDeck = new LinkedList<Card>(Arrays.asList(tempDeck));
    }

    public Queue<Card> getDeck() {
        return mDeck;
    }

    public int getDeckSize() {
        return mNumberOfCards;
    }

    public Card revealTopCard() {
        return mDeck.peek();
    }

    public Card drawTopCard() {
        return mDeck.poll();
    }

    public void resetDeck() {
        makeDeck();
        shuffleDeck();

    }



}


