package com.blackjack;

public class Card {
    protected String mSuit;
    protected String mFace;
    protected int mValue;

    Card(String suit, String face, int value) {
        mSuit = suit;
        mFace = face;
        mValue = value;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    public String getSuit() {
        return mSuit;
    }

    public String getFace() {
        return mFace;
    }

    public int add(Card card) {
        return mValue + card.getValue();
    }

    public boolean faceEquals(Card card) {
        return mFace.equals(card.getFace());
    }

    public boolean greaterThan(Card card) {
        return mValue > card.getValue();
    }

    public boolean lessThan(Card card) {
        return mValue < card.getValue();
    }

    public String asString() {
        if (mFace.toString().length() == 2) return "* [" + mFace + " " + mSuit + " ]";
        return "* [ " + mFace + " " + mSuit + " ]";
    }
}
