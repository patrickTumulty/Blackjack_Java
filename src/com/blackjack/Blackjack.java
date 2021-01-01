package com.blackjack;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private BlackjackDeck mGameDeck;
    private Player mPlayer;
    private Player mDealer;
    private final Scanner mScanner;
    private final String[] mDealerNames = { "Basher", "Danny", "Reuben", "Saul", "Linus", "Rusty" };


    Blackjack() {

        mScanner = new Scanner(System.in);
    }

    public void gameLoop() {
        String input = "";
        while (true) {
            handLoop();
            System.out.print("[P]lay again? or [L]eave the table?");
            input = mScanner.nextLine();
            if (input.equalsIgnoreCase("p")) {
                mGameDeck.resetDeck();
                mPlayer.clearHand();
                mDealer.clearHand();
            } else if (input.equalsIgnoreCase("l")) {
                break;
            }
        }
        printHeader();
        System.out.println(mPlayer.getName() + "(" + mPlayer.getHandsWon() + ") " + mDealer.getName() + "(" + mDealer.getHandsWon() + ")" );
    }

    private void handLoop() {
        initialDeal();
        while (mPlayer.getScore() < 21) {

            printDealerAndPlayerHands();
            System.out.print("[H]it or [S]tay? : ");

            String decision = mScanner.nextLine();
            if (decision.equalsIgnoreCase("h")) {
                dealCards(1, 0);
            } else if (decision.equalsIgnoreCase("s")) {
                while (mDealer.getScore() < 17) {
                    dealCards(0, 1);
                }
                if (mDealer.getScore() >= 17) {
                    System.out.println(mDealer.getName() + ": 'Stay'");
                    break;
                }
            }
        }

        if (mDealer.getHand().size() == 1) {
            dealCards(0, 1);
        }

        printDealerAndPlayerHands();
        printResults();

    }



    private void printResults() {
        System.out.println("[*]    HAND OVER    [*]");
        System.out.println("[*] === ♠ ♥ ♦ ♣ === [*]");
        System.out.println(mPlayer.getName() + "(" + mPlayer.getScore() + ")" + " " + mDealer.getName() + "(" + mDealer.getScore() + ")");

        if (mPlayer.getScore() == 21) {
            System.out.println("!BLACKJACK! " + mPlayer.getName() + " Wins");

            mPlayer.wonHand();

        } else if (mDealer.getScore() == 21) {
            System.out.println("!BLACKJACK! " + mDealer.getName() + " Wins");
            System.out.println("\nYou Lose.");

            mDealer.wonHand();

        } else if (mPlayer.getScore() > mDealer.getScore() && mPlayer.getScore() < 21 && mDealer.getScore() < 21) {
            System.out.println(mPlayer.getName() + " Wins");

            mPlayer.wonHand();

        } else if (mPlayer.getScore() < mDealer.getScore() && mPlayer.getScore() < 21 && mDealer.getScore() < 21){
            System.out.println(mDealer.getName() + " Wins");

            mDealer.wonHand();

        } else if (mPlayer.getScore() > 21) {
            System.out.println(mDealer.getName() + " Wins");

            mDealer.wonHand();

        } else if (mPlayer.getScore() == mDealer.getScore()){
            System.out.println("It's a tie!");

        } else {
            System.out.println(mPlayer.getName() + " Wins");

            mPlayer.wonHand();
        }
    }

    public void newGame() {
        printHeader();
        mGameDeck = new BlackjackDeck(1);

        System.out.print("Enter Name: ");
        mPlayer = new Player(mScanner.nextLine());
        System.out.println("Hello " + mPlayer.getName() + ".");

        sleep(1);

        mDealer = new Player(mDealerNames[new Random().nextInt(mDealerNames.length)]);
        System.out.println("The dealers name is " + mDealer.getName());

        sleep(1);
    }

    private void printHeader() {
        System.out.println("[*] =============== [*]");
        System.out.println("  ~~ { Blackjack } ~~  ");
        System.out.println("[*] === ♠ ♥ ♦ ♣ === [*]");

    }

    private void clearScreen() {
        try
        {
            // This seems to not work. Stackoverflow says "might not work in IDE"
            String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception e)
        {

        }
    }

    private void hackyClearScreen() {
        for (int i = 0; i < 50; i++) System.out.print("\n");
    }

    public void printDealerAndPlayerHands() {
        System.out.println("\n[*] === ♠ ♥ ♦ ♣ === [*]");
        printDealersHand();
        System.out.print("\n");
        printPlayersHand();
    }

    public void printDealersHand() {
        System.out.println(mDealer.getName() + "'s hand");
        if (mDealer.getHand().size() == 1) {
            System.out.println(mDealer.getHand().get(0).asString());
            System.out.println("* [?????]");
            System.out.println("Score : " + mDealer.getScore());
        } else {
            for (Card card : mDealer.getHand()) {
                System.out.println(card.asString());
            }
            System.out.println("Score : " + mDealer.getScore());
        }
    }

    public void printPlayersHand() {
        System.out.println("Your hand");
        for (Card card : mPlayer.getHand()) {
            System.out.println(card.asString());
        }
        System.out.println("Score : " + mPlayer.getScore());
    }
    public void initialDeal() {
        mPlayer.addCardToHand(mGameDeck.drawTopCard());
        mDealer.addCardToHand(mGameDeck.drawTopCard());
        mPlayer.addCardToHand(mGameDeck.drawTopCard());

    }

    private void dealCards(int cards2player, int cards2dealer) {
        for (int i = 0; i < cards2player; i++) mPlayer.addCardToHand(mGameDeck.drawTopCard());
        for (int i = 0; i < cards2dealer; i++) mDealer.addCardToHand(mGameDeck.drawTopCard());
    }

    private void sleep(double seconds) {
        try {
            Thread.sleep((long)(1000 * seconds));
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
