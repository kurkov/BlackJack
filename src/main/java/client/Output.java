package client;

import model.User;

public class Output {

    public void isConnected() {
        System.out.println("Connected to server");
    }

    public void isNotConnected() {
        System.out.println("Connection problems");
    }

    public void startGame() {
        System.out.println("Started new game");
    }

    public void askBetAmount() {
        System.out.println("What is your bet amount?");
    }

    public void cantMakeBet() {
        System.out.println("You don't have enough money to make such bet. Please make smaller bet");
    }

    public void showUserMoney(User user) {
        System.out.println("You have: $" + user.getMoney());
    }

    public void showUserMoneyAfterBet(User user) {
        System.out.println("You made bet. Money left: $" + user.getMoney());
    }

    public void askUserAction() {
        System.out.println("What would you like to do? (s = STAND, h = HIT, d = DOUBLE)");
    }

    public void showUserHand(User user) {
        System.out.println("Your hand is (" + user.getHandPoints() + " points):");
    }

    public void incorrectAction() {
        System.out.println("Incorrect action!");
    }
}
