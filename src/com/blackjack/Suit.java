package com.blackjack;
//♠ ♥ ♦ ♣

public enum Suit {
    SPADES("♠"), CLUBS("♣"), DIAMONDS("♦"), HEARTS("♥"), JOKER("Joker");

    private String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}
