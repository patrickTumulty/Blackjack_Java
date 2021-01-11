package com.blackjack;

public class Card {
    private final Suit suit;
    private final Face face;
    private int value;

    Card(Suit suit, Face face, int value) {
        this.suit = suit;
        this.face = face;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    public String getColor() {
        if (suit.equals(Suit.SPADES) || suit.equals(Suit.CLUBS)) {
            return "Black";
        }
        return "Red";
    }

    @Override
    public String toString() {
        return String.format("[%2s %s ]", face, suit);
    }
}
