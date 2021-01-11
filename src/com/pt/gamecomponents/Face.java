package com.pt.gamecomponents;

public enum Face {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A"),
    JOKER("Joker");

    private String face;

    Face(String face) {
        this.face = face;
    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getValue() {
        if (isInt(face))
            return Integer.parseInt(face);
        else if (this == Face.JOKER)
            return 0;
        return 10;
    }

    @Override
    public String toString() {
        return face;
    }
}
