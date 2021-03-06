package com.pt.game;

import com.pt.gamecomponents.BlackjackDeck;
import com.pt.gamecomponents.Card;
import com.pt.gamecomponents.Player;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private BlackjackDeck gameDeck;
    private Player player;
    private Player dealer;
    private final Scanner scanner;
    private final String[] dealerNames = { "Basher", "Danny", "Reuben", "Saul", "Linus", "Rusty" };


    public Blackjack() {
        scanner = new Scanner(System.in);
    }

    public void gameLoop() {
        do {
            handLoop();
        } while (promptPlayAgain());
        printHeader();
        printDealerPlayerHandsWon();
    }

    private boolean promptPlayAgain() {
        System.out.print("[P]lay again? or [L]eave the table? : ");
        if (scanner.nextLine().equalsIgnoreCase("p")) {
            gameDeck.resetDeck();
            player.clearHand();
            dealer.clearHand();
        } else {
            return false;
        }
        return true;
    }

    private void printDealerPlayerHandsWon() {
        System.out.println("[*] == HANDS WON == [*]");
        System.out.printf("%s: %d\n", player.getName(), player.getHandsWon());
        System.out.printf("%s: %d\n", dealer.getName(), dealer.getHandsWon());
    }

    private void printDealerPlayerScore() {
        System.out.println("[*] ===  SCORE  === [*]");
        System.out.printf("%s: %d\n", player.getName(), player.getScore());
        System.out.printf("%s: %d\n", dealer.getName(), dealer.getScore());
    }

    private void handLoop() {
        initialDeal();
        hackyClearScreen();
        while (player.getScore() < 21) {
            printDealerAndPlayerHands();
            System.out.print("[H]it or [S]tay? : ");
            String decision = scanner.nextLine();
            hackyClearScreen();
            if (decision.equalsIgnoreCase("h")) {
                dealCards(1, 0);
            } else if (decision.equalsIgnoreCase("s")) {
                if (dealersTurn()) break;
            }
        }

        if (dealer.getHand().size() == 1) {
            dealCards(0, 1);
        }

        printDealerAndPlayerHands();
        printResults();

    }

    private boolean dealersTurn() {
        while (dealer.getScore() < 17) {
            dealCards(0, 1);
        }
        return true;
    }


    private void printResults() {
        printDealerPlayerScore();

        int playerWins = player.winsAgainst(dealer);
        switch (playerWins) {
            case 1 -> {
                System.out.println(player.getName() + " Wins!");
                player.wonHand();
            }
            case 0 -> System.out.println("It's a tie...");
            case -1 -> {
                System.out.println(dealer.getName() + " Wins!");
                dealer.wonHand();
            }
        }

    }

    private void printBanner() {
        System.out.println("\n[*] === ♠ ♥ ♦ ♣ === [*]\n");
    }

    public void newGame() {
        printHeader();
        gameDeck = new BlackjackDeck(1);

        promptForPlayerName();
        sleep(1);
        getAndPrintDealerName();
        sleep(1);
    }

    private void getAndPrintDealerName() {
        dealer = new Player(dealerNames[new Random().nextInt(dealerNames.length)]);
        System.out.println("The dealers name is " + dealer.getName());
    }

    private void promptForPlayerName() {
        System.out.print("Enter Name: ");
        player = new Player(scanner.nextLine());
        System.out.println("Hello " + player.getName() + ".");
    }

    private void printHeader() {
        System.out.println("[*] =============== [*]");
        System.out.println("  ~~ { Blackjack } ~~  ");
        System.out.println("[*] === ♠ ♥ ♦ ♣ === [*]");

    }

    public void printDealerAndPlayerHands() {
        printBanner();
        printDealersHand();
        System.out.print("\n");
        printPlayersHand();
    }

    public void printDealersHand() {
        System.out.printf("[*] = %s's HAND = [*]\n\n", dealer.getName());
        if (dealer.getHand().size() == 1) {
            System.out.printf("%s [?????]\nScore : %d\n", dealer.getHand().get(0), dealer.getScore());
        } else {
            printCardsDoubleColumn(this.dealer);
        }
    }

    public void printPlayersHand() {
        System.out.println("[*] == YOUR HAND == [*]\n");
        printCardsDoubleColumn(this.player);
    }

    private void printCardsDoubleColumn(Player player) {
        int columnCounter = 0;
        for (Card card : player.getHand()) {
            columnCounter = (columnCounter + 1) % 3;
            if (columnCounter == 0) System.out.print("\n");
            System.out.printf("%s ", card);
        }
        System.out.println("\nScore : " + player.getScore());
    }

    public void initialDeal() {
        player.addCardToHand(gameDeck.drawTopCard());
        dealer.addCardToHand(gameDeck.drawTopCard());
        player.addCardToHand(gameDeck.drawTopCard());

    }

    private void dealCards(int cards2player, int cards2dealer) {
        for (int i = 0; i < cards2player; i++) player.addCardToHand(gameDeck.drawTopCard());
        for (int i = 0; i < cards2dealer; i++) dealer.addCardToHand(gameDeck.drawTopCard());
    }

    private void sleep(double seconds) {
        try {
            Thread.sleep((long)(1000 * seconds));
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void hackyClearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
