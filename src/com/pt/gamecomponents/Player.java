package com.pt.gamecomponents;

import java.util.ArrayList;

public class Player {
    private String name;
    private int score = 0;
    private int handsWon = 0;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getHandsWon() { return handsWon; }

    public void addToScore(int points) {
        score += points;
    }

    public void addCardToHand(Card card) {
        addToScore(card.getValue());
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
        score = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void wonHand() {
        handsWon++;
    }



}
