package com.pt;

import com.pt.game.Blackjack;

public class Main {

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.newGame();
        game.gameLoop();


    }
}
