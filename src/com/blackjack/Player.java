package com.blackjack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
    Player(String name) {
        mName = name;
        mHand = new ArrayList<Card>();
    }

    public String getName() {
        return mName;
    }

    public int getScore() {
        return mScore;
    }

    public int getHandsWon() { return mHandsWon; }

    public void addToScore(int points) {
        mScore += points;
    }

    public void addCardToHand(Card card) {
        addToScore(card.getValue());
        mHand.add(card);
    }

    public void clearHand() {
        mHand.clear();
        mScore = 0;
    }

    public ArrayList<Card> getHand() {
        return mHand;
    }

    public void wonHand() {
        mHandsWon++;
    }

    private String mName;
    private int mScore = 0;
    private int mHandsWon = 0;
    private ArrayList<Card> mHand;

}
